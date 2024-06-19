package com.kh.model.dao;

import java.sql.Connection;
import java.sql.Statement;

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
	
	public void insertMember(Member m) {
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
					+ "'" +m.getAddress() + 
					"', " + m.getHobby() + "', sysdate)";  
		
	}
	
}
