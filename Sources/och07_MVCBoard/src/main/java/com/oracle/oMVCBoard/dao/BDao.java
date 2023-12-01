package com.oracle.oMVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oracle.oMVCBoard.dto.BDto;

public class BDao {
	DataSource dataSource;
	
	// jdbc 이용해서 접근하는 방법
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		}catch (NamingException e) {
			System.out.println("생성자 dataSource " + e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<BDto> boardList() {
		ArrayList<BDto> bList = new ArrayList<BDto>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		System.out.println("BDao boardList Start...");
		
		try {
			String query = "select bId, bName, bTitle, bContent, bDate, BHit, bGroup, bStep, bIndent "
						 + "from   mvc_board "
						 + "order  by bGroup desc, bStep asc ";
			System.out.println("DAO boardList query-> " + query);
			
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			// 조건이 pk 아니면 멀티 로우이므로  while문 사용
			while(resultSet.next()) {  
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");  
				
				// 두 번째 생성자 사용해서 dto에 저장 / setter 또는 생성자 이용해서 dto에 저장 가능
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				
				bList.add(dto);
			}
		}catch (Exception e) {
			System.out.println("DAO boardList Exception-> " + e.getMessage());
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bList;
	}
	

	public BDto contentView(String strId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		System.out.println("Dao content_view Start...");
		System.out.println("Dao content_view strId-> " + strId);
	
		upHit(strId);  
		BDto dto = null;
		
		try {
			String query = "select * from mvc_board where bId =?";
			
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strId));   // BId가 String으로 넘어와서 int로 변경, 리네임 안돼서 strId로 변경
			resultSet = preparedStatement.executeQuery();
			
			// 조건이 pk이므로 하나의 로우가 나와서 while문 사용 안함
			if(resultSet.next()) {
				int bId = (resultSet.getInt("bId"));
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);	
			}
		}catch (SQLException e) {
			System.out.println("DAO contentView SQLException-> " + e.getMessage());
		}finally {
				try {
					if(resultSet != null) resultSet.close();
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		}
		return dto;
	}
	
	// return값 받아서 성공인지 실패인지 알 필요 없어서 void로 선언
	private void upHit(String bId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String query = "update mvc_board set bHit = bHit+1 where bId = ?";
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bId);
			int result = preparedStatement.executeUpdate();  // return값 받게 되면 사용하려고 적어만 두었음
					
		} catch (SQLException e) {
			System.out.println("DAO upHit Exception-> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		System.out.println("BDao modify Start...");
		
		try {
			String sql = "update mvc_board set bName=?, bTitle=?, bContent=? where bId=?";
			
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId));
			int result = preparedStatement.executeUpdate();    //int result = void 있어서 필요 없음, 안 써도 문제 없지만 받아주는게 좋음
			
		}catch (SQLException e) {
			System.out.println("DAO modify SQLException-> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}	
		}
	}

//	 DAO
//	 1) mvc_board_seq(bId, bGroup)  -->  bId와  bGroup의 값이 같다고 설정
//	 2) bStep, bIndent, bHit(처음이니까 조회수 0) --> 0 
//	 3) bDate -> sysdate
//	 4) bName, bTitle, bContent -> parameter
	
	public void write(String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		System.out.println("Dao write Start...");
		
		try {
//			선생님이 하신 거 -> 오라클의 컬럼 순서와 다르게 함	  9:35		
//			"Insert Into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bDate) "
//					+ "Values (mvc_board_seq.nextval,?, ?, ?, 0, mvc_board_seq.currval,  0, 0 , sysdate)";
			
			String sql = "insert into mvc_board values(mvc_board_seq.nextval, "
					   + "?,?,?,sysdate,0,mvc_board_seq.nextval,0,0)";
			System.out.println("DAO write sql" + sql);
			
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
		    int result = preparedStatement.executeUpdate(); 
			
		}catch (SQLException e) {
			System.out.println("DAO write SQLException-> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e2) {
				System.out.println("DAO write SQLException-> " + e2.getMessage());
			}	
		}
	}

	
	public BDto reply_view(String strId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		System.out.println("reply_view Start...");
		BDto dto = null;
		
		try {
			String sql = "select * from mvc_board where bId =?";
			System.out.println("DAO reply_view sql" + sql);	
			
			connection = dataSource.getConnection();	
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(strId));
			rs = preparedStatement.executeQuery();
			
			// 조건이 pk이므로 if문
			if (rs.next()) {
				int bId = (rs.getInt("bId"));
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				// 생성자로 하든 setter로 하든 상관 x
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		}catch (SQLException e) {
			System.out.println("reply_view SQLException-> " + e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		}
		return dto;
	}

// ** 홍해의 기적으로 bGroup과 bStep의 값을 update하고, 그 값을 포함한 전체 컬럼을 insert한다 **
	
//	   dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
//	   1) 홍해 기적  --> replyShape(bGroup, bStep);
//	      bGroup = ? and bStep > ? ---> bStep 하나씩 증가 
//	   2) insert into mvc_board
//	      [1] bId --> mvc_board_seq
//	      [2] bGroup --> Hidden Group
//	      [3] bStep / bIndent -->  Hidden + 1
//	      [4] bName / bTitle / bContent --> 입력값
	      
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent) {
		
		// 홍해의 기적
		replyShape(bGroup, bStep);  
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		System.out.println("DAO reply Start...");

		BDto dto = null;
		try {
			String sql = "insert into mvc_board(bId, bName, bTitle, bContent, bGroup, bStep, bIndent)"
					+ "values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			System.out.println("Dao reply sql-> " + sql);
			
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);   // 댓글달 정보의 +1
			preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
		    int result = preparedStatement.executeUpdate(); 
		    System.out.println("DAO reply result-> "+result);
		    
		}catch (SQLException e) {
			System.out.println("DAO reply SQLException-> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	// 내부에서 쓰는 메소드라서 private, public으로 해도 괜찮음
	private void replyShape(String strGroup, String strStep) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String sql = "update mvc_board set bStep = bStep+1 where bGroup=? and bStep > ?"; // 홍해의 기적, indent??
			
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(strGroup));
			preparedStatement.setInt(2, Integer.parseInt(strStep));
			int result = preparedStatement.executeUpdate();
		
		}catch (SQLException e) {
			System.out.println("DAO replyShape SQLException-> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void delete(String bId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String sql = "delete from mvc_board where bId=?";  // 조건이 pk이므로 한개의 row만 삭제(댓글 안지워짐)
			System.out.println("DAO delete sql" + sql);
			
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(bId));
			int result = preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("DAO delete SQLException-> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	
	

	
	
	

}
