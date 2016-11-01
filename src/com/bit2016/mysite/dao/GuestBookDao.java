package com.bit2016.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2016.mysite.vo.GuestBookVo;


public class GuestBookDao {

	private Connection getConnection() throws SQLException {
		System.out.println("GuestBookDao");
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 :" + e);
		}
		return conn;
	}
	
	public Long insert(GuestBookVo vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		Long no = null;
		
		try {
			conn = getConnection();
			
			String sql =
				" insert into guestbook values(guestbook_seq.nextval, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString( 1, vo.getName() );
			pstmt.setString( 2, vo.getContent() );
			pstmt.setString( 3, vo.getPassword() );
			
			pstmt.executeUpdate();
			
			// primary key(guestbook_seq.currval) 받아오기
			stmt = conn.createStatement();
			sql = "select guestbook_seq.currval from guestbook";
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				no  = rs.getLong(1);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if( rs!= null){
					rs.close();
				}
				
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return no;
	}
	
	public GuestBookVo get(Long no){
		GuestBookVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(no);
			String sql = "select no, name, content, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss')from guestbook where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				vo = new GuestBookVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setDate(rs.getString(4));
			}
			
			System.out.println(vo.toString());
		

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally{
				try {
					if(rs != null){
					rs.close();
					}
					if(pstmt != null){
						pstmt.close();
					} 
					if( conn != null ) {
						conn.close();
					}
				}catch (SQLException e) {
					System.out.println("error :" + e);
				}
			}

		return vo;
	}
	
	
	public boolean delete(GuestBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql =
				" delete" +
				"   from guestbook" +
				"  where no = ?" +
				"    and password = ?";
			pstmt = conn.prepareStatement(sql);
			
		
			
			pstmt.setLong( 1, vo.getNo() );
			pstmt.setString( 2, vo.getPassword() );
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return (result==1);
	}
	
	
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			
			String sql =
				" select no, name, content, password, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss' )" +
				" from guestbook" +
				" order by reg_date desc";
			rs = stmt.executeQuery(sql);
			
			while( rs.next() ) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String password = rs.getString(4);
				String regDate = rs.getString(5);
				
				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setPassword(password);
				vo.setDate(regDate);
				
				list.add( vo );
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return list;
	}

public List<GuestBookVo> getList(int page) {
	List<GuestBookVo> list = new ArrayList<GuestBookVo>();

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		conn = getConnection();
		
		
		
		String sql = " select *" +
				"  from (select a.*, rownum rn" +
				"  from ( select no," +
				" name, content, password, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss' )" +
				" from guestbook" +
				" order by reg_date desc ) a )" +
				" where (?-1)*5+1 <= rn and rn <= ?*5";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, page);
		pstmt.setInt(2, page);
		
		rs = pstmt.executeQuery();
		
		while( rs.next() ) {
			Long no = rs.getLong(1);
			String name = rs.getString(2);
			String content = rs.getString(3);
			String password = rs.getString(4);
			String regDate = rs.getString(5);
			
			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setContent(content);
			vo.setPassword(password);
			vo.setDate(regDate);
			
			list.add( vo );
		}
	} catch (SQLException e) {
		System.out.println("error:" + e);
	} finally {
		try {
			if( rs != null ) {
				rs.close();
			}
			if( pstmt != null ) {
				pstmt.close();
			}
			if( conn != null ) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	return list;
	}
}
