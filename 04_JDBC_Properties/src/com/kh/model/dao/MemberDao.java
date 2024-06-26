package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.w3c.dom.ls.LSException;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Member;

// DAO(Date Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과받기(JDBC)
//                           결과를 Controller로 다시 리턴 
public class MemberDao {
	
	/*
	 * 기존의 방식 : DAO 클래스에 사용자가 요청할 때 마다 실행해야되는 SQL문을 자바 코드 내에 명시적으로 작성 => 정적코딩방식(하드코딩)
	 * 
	 * 			> 문제점 : SQL문을 수정해야 될 경우 자바 소스코드를 수정해야 됨 -> 수정된 내용을 반영시키고자 한다면 프로그램을 재기동 해야 됨
	 * 
	 * 			> 해결 방법 : SQL문을 별도로 관리하는 외부 파일(.xml)로 만들어서 실시간으로 그 파일에 기록된 SQL문을 읽어들여서 실행
	 */
	
	// 필드부
	private Properties prop = new Properties();
	
	// 생성자부
	public MemberDao() { // 기본 생성자
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public int insertMember(Connection conn, Member m) {
		// insert문 => 처리된 행수 => 트랜잭션 처리
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
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

		String sql = prop.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(sql); // 완성된 sql문
			rset = pstmt.executeQuery();

			while (rset.next()) {
				// 한행 => Member 객체 => list 추가
				list.add(new Member(rset.getInt("userno")
						, rset.getString("userid")
						, rset.getString("userpwd")
						, rset.getString("userName")
						, rset.getString("gender")
						, rset.getInt("age")
						, rset.getString("email")
						, rset.getString("phone")
						, rset.getString("address")
						, rset.getString("hobby")
						, rset.getDate("enrolldate")));
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

		String sql = prop.getProperty("selectByUserId"); // 미완성된 SQL문

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

		String sql = prop.getProperty("selectByUserName");

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

		String sql = prop.getProperty("updateMember");

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
	
	public int deleteMember(Connection conn, String userId) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public String loginMember(Connection conn, String userId, String userPwd) {
		// select문(한 행) => ResultSet => Member 객체
		
		String userName = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				userName = rset.getString("username");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return userName;
		
	}
	
}


