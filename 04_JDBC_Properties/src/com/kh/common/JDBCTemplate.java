package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// 공통으로 사용할 템플릿 (매번 반복적으로 작성될 코드를 메소드로 정의해둘 거임)
public class JDBCTemplate {
	
	// 모든 메소드는 싹 다 static 메소드로 작성
	// 싱글톤 패턴 : 메모리 영역에 단 한번만 올려두고 매번 재사용 하는 개념
	
	// 1. Connection 객체 생성 (DB와 접속) 한 후 해당 Connection 반환해주는 메소드
	public static Connection getConnection() {
		
		/*
		 * 기존의 방식 : jdbc driver 구문, 접속할 db의 url, 접속할 계정명 / 비밀번호들을 자바 소스코드 내에 명시적으로 작성함. 
		 * => 정적 코딩 방식 (하드코딩)
		 * > 문제점 : dbms가 변경 되었을 경우, 접속할 db의 url 또는 계정명, 비밀번호가 변경 될 경우 => 자바의 소스코드를 수정해야됨
		 * 			=> 수정된 내용을 반영시키고자 한다면, 프로그램 재구동 해야됨 (프로그램이 비정상적으로 종료됐다가 다시 구동)
		 * 			=> 유지보수에 불편하다 ;;
		 * 
		 * > 해결 방식 : db 관련된 정보들을 별도로 관리의 외부파일(.properties)로 만들어서 관리
		 * 			   외부 파일로부터 읽어들여서 반영 시키면 된다. -> 동적코딩 방식
		 * 			
		 */
		
		Connection conn = null;
		Properties prop = new Properties();
		
		
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("username"),
											   prop.getProperty("password"));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 2. commit 처리해주는 메소드 (Connection 전달 받아서)
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. rollback 처리 해주는 메소드 (Connection 전달 받아서)
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// JDBC용 객체들 전달받아서 반납처리 해주는 메소드
	// 4. Statement 관련 객체 전달받아서 반납 시켜주는 메소드
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 5. Connection 객체 전달 받아서 반납 시켜주는 메소드
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 6. ResultSet 객체 전달 받아서 반납 시켜주는 메소드
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}