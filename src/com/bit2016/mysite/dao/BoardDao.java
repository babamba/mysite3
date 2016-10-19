package com.bit2016.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.digester.SetRootRule;

import com.bit2016.mysite.vo.BoardVo;

public class BoardDao {



	private Connection getConnection() throws SQLException {
		System.out.println("BoardDao");
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
	
	public int getTotalCount(){
		int totalcount = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "select count(*) from board;";

			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
		return totalcount;
	}
	
	public void write(BoardVo vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into board values(board_seq.nextval, ? , ?, sysdate, ?, "
					 	+ "nvl((select max(group_no) from board),0) + 1, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString( 1, vo.getTitle());
			pstmt.setString( 2, vo.getContent());
			pstmt.setInt( 3, vo.getHit());
			pstmt.setInt( 4, vo.getOrderNo());
			pstmt.setInt( 5, vo.getDepth());
			pstmt.setLong( 6, vo.getUserNo());
			
			
			
			pstmt.executeUpdate();
			
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
	}

	
	public void view(BoardVo vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "select * from (select ?, ?, ?, ?, ?, ?, rownum as rn from + "
					+ "( select a.no, a.title, a.hit, to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date, b.name, a.users_no, a.depth"
					+ "from board a, users b where a.users_no = b.no order by group_no desc, order_no asc))"
					+ "where (1-1)*5+1 <=  and rn <= 1*5";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong( 1, vo.getNo() );
			pstmt.setString( 2, vo.getTitle());
			pstmt.setInt( 3, vo.getHit());
			pstmt.setString(4, vo.getRegDate());
			pstmt.setString(5, vo.getUserName());
			pstmt.setLong(6, vo.getUserNo());
			
			pstmt.executeUpdate();
			
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
	}
	
	
	
	public List<BoardVo> getList(Integer page, Integer size) {
		
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select * from(select no, title, hit, depth, reg_date, name, users_no, rownum as rn from"
					+ "( select a.no, a.title, a.hit, to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss')"
					+ " as reg_date, b.name, a.users_no, a.depth from board a, users b where a.users_no = "
					+ "b.no order by group_no desc, order_no asc)) where (?-1)*?+1 <= rn and rn <= ?*?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, size);
			pstmt.setInt(3, page);
			pstmt.setInt(4, size);
			
			System.out.println( "들어옴? ");
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				int hit = rs.getInt(3);
				int depth = rs.getInt(4);
				String regDate = rs.getString(5);
				String name = rs.getString(6);
				Long userNo = rs.getLong(7);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setHit(hit);
				vo.setDepth(depth);
				vo.setRegDate(regDate);
				vo.setUserName(name);
				vo.setUserNo(userNo);
				
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