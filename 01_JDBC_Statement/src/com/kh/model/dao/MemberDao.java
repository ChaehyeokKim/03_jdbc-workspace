package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.w3c.dom.ls.LSException;

import com.kh.model.vo.Member;

// DAO(Date Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과받기(JDBC)
//                           결과를 Controller로 다시 리턴 

public class MemberDao {

	/*
	 * * JDBC 용 객체
	 * - Connection : DB의 연결 정보를 담고 있는 객체
	 * - [Prepared]Statement : 연결된 DB에 SQL문을 전달해서 실행하고 그 결과를 받아내는 객체
	 * - ResultSet : SELECT문 실행 후 조회된 결과물들이 담겨있는 객체
	 * 
	 *  * JDBC 과정 (순서 중요)
	 *  1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 것을 클래스 파일로 등록
	 *  2) Connection 생성 : 연결하고자 하는 DB 정보를 입력해서 해당 DB와 연결하면서 생성
	 *  3) Statement 생성 : Connection 객체를 이용해서 생성(SQL문 실행 및 결과받는 객체)
	 *  4) SQL문 전달하면서 실행 : Statement 객체를 이용해서 SQL문 실행
	 *  5) 결과 받기
	 *     5_1.	SELECT문 실행 => ResultSet 객체 (조회된 데이터들이 담겨있음) => 6_1)
	 *     5_2.    DML문 실행 => int (처리된 행 수)						=> 6_2)
	 *     
	 *  6_1) ResultSet에 담겨있는 데이터들을 하나하나 씩 뽑아서 vo 객체에 옮겨담기 [+ ArrayList]   
	 *  6_2) 트랜잭션 처리 (성공적으로 수행했으면 commit, 실패했으면 rollback)
	 *  
	 *  7) 다 사용한 JDBC용 객체들 반드시 자원 반납(close) => 생성된 역순으로
	 */ 
	
	public int insertMember(Member m) {
		// insert문 => 처리된 행수 (int) => 트랜잭션 처리
		
		// 필요한 변수들 셋팅
		int result = 0; // 처리된 결과(처리된 행수)를 받아줄 변수
		Connection conn = null; // 연결된 DB의 연결 정보를 담는 객체
		Statement stmt = null; // "완성된 sql문(실제값이 다 채워진 상태)" 로 전달해서 곧바로 실행 후 결과 받는 객체
		
		// 실행할 sql문 (완성된 형태로 만들기)
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X',
		//							 00, 'XXX@XASXA.COM', 'XXXXXXXXXXX', 'XXXXX', 'X', SYSDATE);
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL,"
					+ "'" + m.getUserId() + "', "
				    + "'" + m.getUserPwd() + "', "
					+ "'" + m.getUserName() + "', " 
					+ "'" + m.getGender() + "', " 
						  +  m.getAge() + ", "
					+ "'" + m.getEmail() + "', "
					+ "'" + m.getPhone() + "', "
					+ "'" + m.getAddress() + "', " 
					+ "'" + m.getHobby() + "', sysdate)";  
	
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성 == DB에 연결(URL, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4, 5) SQL문을 전달하면서 실행 후 결과(처리된 행수)받기
			result = stmt.executeUpdate(sql);

			// 6) 트랜잭션 처리
			if (result > 0) { // 성공 => commit
				conn.commit();
			} else { // 실패 => rollback
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try { // 7) 다 쓴 JDBC용 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result; // 1 아니면 0
	}

	/**
	 * 사용자가 요청한 회원 전체를 조회처리 해주는 메소드
	 * 조회된 결과가 있으면 조회된 결과값이 담겨있는 list, 없으면 결과값이 없는 텅빈 list
	 * @return
	 */
	public ArrayList<Member> selectList() {
		// select문 (여러 행 조회) resultSet 객체로 생성 => 하나하나를 Member 객체로 생성 => ArrayList 공간에
		// 차곡차곡 담기

		// 필요한 변수들 셋팅
		ArrayList<Member> list = new ArrayList<Member>(); // [] : 현재는 텅 비어있는 상태

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // select문 실행 시 조회된 결과값들이 최초로 담기는 객체

		String sql = "SELECT * FROM MEMBER";

		// 1) JDBC DRIVER 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4,5) sql문 실행 및 결과 받기
			rset = stmt.executeQuery(sql);

			// 6) ResultSet으로부터 데이터 하나씩 뽑아서 Member(vo) 객체에 담고 + list에 추가
			while (rset.next()) {

				// 현재 rset 의 커서가 가리키고 있는 한 행의 데이터들을 싹 다 뽑아서 Member 객체에 담기
				Member m = new Member();

				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("userId"));
				m.setUserPwd(rset.getString("userPwd"));
				m.setUserName(rset.getString("userName"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				// 현재 참조하고 있는 행에 대한 모든 컬럼에 대한 데이터들을 다 뽑았다
				// 하나의 Member 객체 m 여기에 다 담았다

				list.add(m); // 리스트에 해당 회원 객체 차곡차곡 담기
			}

			// 반복문이 다 끝난 시점에
			// 만약 조회된 데이터가 없었다면 list는 텅 비어있는 상태일 것임.
			// 그러나 조회된 데이터가 있었다면 list에 뭐라도 담겨 있을 것임.

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 다 쓴 jdbc용 객체 반납
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	/**
	 * 사용자의 아이디로 회원 검색 요청 처리해주는 메소드
	 * @param userId 사용자가 입력한 검색하고자 하는 회원 아이디값
	 * @return 검색된 결과가 있으면 생성된 Member 객체 | 검색된 결과가 없으면 null
	 */
	public Member selectByUserId(String userId) {
		// select문(한 행) => ResultSet => Member 객체
		
		Member m = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문 (완성된 형태로)
		// SELECT * FROM MEMBER WHERE USERID = 'XXX';
		String sql = "SELECT * FROM MEMBER WHERE USERID = '" + userId + "'";
		
		// 1) jdbc driver 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2) Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
		// 3) Statement 생성
			stmt = conn.createStatement();
			
		// 4,5)
			rset = stmt.executeQuery(sql);
			
		// 6)
		// 여기서는 커서를 한 번만 이동하면 되기 때문에 반복문을 사용할 필요가 없음
			if(rset.next()) {
				// 만약 조회가 된다면 해당 조회된 컬럼 값들을 뽑아서 하나의 Member 객체의 각 필드에 담기
				m = new Member(rset.getInt("userno"),
							   rset.getString("userid"),
							   rset.getString("userpwd"),
							   rset.getString("username"),
							   rset.getString("gender"),
							   rset.getInt("age"),
							   rset.getString("email"),
							   rset.getString("phone"),
							   rset.getString("address"),
							   rset.getString("hobby"),
							   rset.getDate("enrolldate"));	
			}
			
			
			// 위의 조건문 다 끝난 시점에
			// 만약 조회된 데이터가 없었을 경우 => m은 null 상태
			// 만약 조회된 데이터가 있었을 경우 => m은 생성된 객체
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 다 쓴 것들 다 닫기
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return m; // null | 생성된 Member 객체
	}
	
	public ArrayList<Member> selectByUserName(String userName) {
		ArrayList<Member> ls = new ArrayList<Member>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '" + "%" + userName + "%" + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

			while (rset.next()) {

				Member kw = new Member();

				kw.setUserNo(rset.getInt("USERNO"));
				kw.setUserId(rset.getString("userId"));
				kw.setUserPwd(rset.getString("userPwd"));
				kw.setUserName(rset.getString("userName"));
				kw.setGender(rset.getString("gender"));
				kw.setAge(rset.getInt("age"));
				kw.setEmail(rset.getString("email"));
				kw.setPhone(rset.getString("phone"));
				kw.setAddress(rset.getString("address"));
				kw.setHobby(rset.getString("hobby"));
				kw.setEnrollDate(rset.getDate("enrolldate"));

				ls.add(kw);
			}
			
			/*
			 * ls.add(new Member(rset.getInt("userno")),
			 * 					 ... 쭉 써서 추가도 가능
			 */

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return ls;
	}

	public int updateMember(Member m) {
		// update문 -> 처리된 행수 (int) -> 트랜잭션 처리
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		// 실행할 sql문(완성형태)
		/*
		 * UPDATE MEMBER
   				SET USERPWD = 'XXXX',
       				EMAIL = 'XXXXX',
       				PHONE = 'XXXXX',
       				ADDRESS = 'XXXXXXX'
 			WHERE USERID = 'XXXX';
		 */
		
		String sql = "UPDATE MEMBER "
					+ "SET USERPWD = '" + m.getUserPwd() + "'" 
					+ ", EMAIL = '" 	 + m.getEmail() + "'"
					+ ", PHONE = '" 	 + m.getPhone() + "'"
					+ ", ADDRESS = '" 	 + m.getAddress() + "'"
					+ "WHERE USERID = '" + m.getUserId() + "'";
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
				stmt = conn.createStatement();
				
				result = stmt.executeUpdate(sql);
				
				if(result > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			return result;
	}
	
	public int deleteMember(String userId) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = '"
						+ userId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

}


