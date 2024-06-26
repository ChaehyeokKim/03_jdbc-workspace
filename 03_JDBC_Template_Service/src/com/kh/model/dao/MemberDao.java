package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.w3c.dom.ls.LSException;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Member;

// DAO(Date Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과받기(JDBC)
//                           결과를 Controller로 다시 리턴 
public class MemberDao {
	
	public int insertMember(Connection conn, Member m) {
		// insert문 => 처리된 행수 => 트랜잭션 처리
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		// 3) PreparedStatement 객체 생성
		try {
			pstmt = conn.prepareStatement(sql); // 미완성된 sql문이 들어있다.
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
		
		// 4, 5) sql문 실행 및 결과 받기
		result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/*JDBCTemplate.*/close(pstmt);
			// conn은 아직 반납하면 안 됨!!
		}
		
		return result;
	}

	public ArrayList<Member> selectList(Connection conn) {
		// select문(여러행) => ResultSet 객체 => ArrayList<Memeber>

		ArrayList<Member> list = new ArrayList<Member>(); // []

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER ORDER BY USERNAME";

		try {
			pstmt = conn.prepareStatement(sql); // 완성된 sql문
			rset = pstmt.executeQuery();

			while (rset.next()) {
				// 한행 => Member 객체 => list 추가
				list.add(new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("usErNAme"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* JDBCTemplate. */close(rset);
			/* JDBCTemplate. */close(pstmt);
		}
		return list;

	}

	public Member selectByUserId(Connection conn, String userId) {
		// select 문 (한행) => ResultSet 객체 => Member 객체
		Member m = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID = ?"; // 미완성된 SQL문

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("usErNAme"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate")); // 이 때 객체 생성하는 것
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return m;

	}

	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
		// select문(여러행) => ResultSet => ArrayList<Member>
		ArrayList<Member> list = new ArrayList<Member>(); // []

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%'|| ? || '%'";

		try {
			pstmt = conn.prepareStatement(sql); // 미완성

			pstmt.setString(1, keyword);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("usErNAme"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);

		}
		return list;

	}

	public int updateMember(Connection conn, Member m) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";

		try {
			pstmt = conn.prepareStatement(sql); // 미완성 sql문

			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		close(conn);

		return result;
	}
	
	public int deleteMember(String userId, Member m) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
}


