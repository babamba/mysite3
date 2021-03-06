package com.bit2016.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public void write(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			if (vo.getNo() == null) {

				String sql = "insert into board values(board_seq.nextval, ? , ?, sysdate, 0, "
						+ "nvl((select max(group_no) from board),0) + 1, 1, 0, ?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setLong(3, vo.getUserNo());

			} else {

				String sql = "insert into board values(board_seq.nextval, ? , ?, sysdate, 0, "
						+ "nvl((select max(group_no) from board),0) + ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setInt(3, vo.getGroupNo());
				pstmt.setInt(4, vo.getOrderNo());
				pstmt.setInt(5, vo.getDepth());
				pstmt.setLong(6, vo.getUserNo());
			}
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	
	public void replyupdate(int gruopNo, int orderNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		BoardVo vo = null;
		
		
		try {
			conn = getConnection();

				String sql = "update board set order_no = ? + 1 where group_no = ? " + 
						     " and order_no > 1";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, vo.getOrderNo());
				pstmt.setLong(2, vo.getNo());
				pstmt.setLong(3, vo.getUserNo());
				
				pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
	
	
	
	public void update(){
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try {
			conn = getConnection();
			String sql = "";
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public void delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
	}
	
	public BoardVo view(Long bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			conn = getConnection();
			String sql = "select no, title, content, group_no, order_no, depth, users_no from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				int groupNo = rs.getInt(4);
				int orderNo = rs.getInt(5);
				int depth = rs.getInt(6);
				Long userNo = rs.getLong(7);

				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				System.out.println("들어갔니");
			}
			

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
		return vo;
	}

/*
	  public void updateHit(){ 
		  
		 Connection conn = null; PreparedStatement pstmt
	  = null; ResultSet rs = null;
	 
	 conn = getConnection(); 
	 String sql = "update board set hit = hit + 1 where no= ?"; }
	 */
	  

	public int pageCount() {
		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select count(*) from board;";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count % 5 + 1;
	}

	public List<BoardVo> getList(Integer page) {

		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = " select * "
					+ " from ( select no, title, hit, reg_date, depth, name, users_no, rownum as rn"
					+ " from(  select a.no, a.title, a.hit, to_char(a.reg_date, 'yyyy-mm-dd hh24:mi:ss')"
					+ " as reg_date, a.depth, b.name, a.users_no"
					+ " from board a, users b" + " where a.users_no = b.no"
					+ " order by group_no desc, order_no asc ))"
					+ " where ( 1 - 1 )* 5 +1 <= rn and rn <= 1 * 5";
			pstmt = conn.prepareStatement(sql);
			// pstmt.setLong(1, page);
			// pstmt.setLong(2, page);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				int hit = rs.getInt(3);
				String regDate = rs.getString(4);
				int depth = rs.getInt(5);
				String userName = rs.getString(6);
				Long userNo = rs.getLong(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setDepth(depth);
				vo.setUserName(userName);
				vo.setUserNo(userNo);

				list.add(vo);

			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return list;

	}

}
