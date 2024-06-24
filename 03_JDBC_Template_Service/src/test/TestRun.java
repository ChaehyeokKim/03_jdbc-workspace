package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestRun {

	public static void main(String[] args) {

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
		
		// 각자 pc(localhost)에 JDBC 계정에 연결한 후 TEST 테이블에 INSERT 해보기
		// insert문 => 처리된 행 수(int) => 트랜잭션 처리
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("번호 : ");
		int num = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("이름 : ");
		String name = sc.nextLine();

		// 필요한 변수들 먼저 셋팅
		int result = 0; // 결과(처리된 행 수)를 받아줄 변수
		Connection conn = null; // DB의 연결 정보를 보관할 객체
		Statement stmt = null; // SQL문 전달해서 실행 후 결과받는 객체

		// 앞으로 실행할 SQL문("완성형태"로 만들어 두기) ※ 맨 뒤에 세미콜론 없어야 된다..
		// String sql = "INSERT INTO TEST VALUES(1, '김채혁', SYSDATE)";
		String sql = "INSERT INTO TEST VALUES(" + num + ", '" + name + "', SYSDATE)";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ojdbc6.jar 파일 추가 안 했을 경우 | 추가는 했는데 오타가 있을 경우 =>
																// ClassNotFoundExeption 예외 발생
			System.out.println("jdbc driver 등록 성공 !");

			// 2) Connection 객체 생성 : DB에 연결(url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4, 5) sql문 전달하면서 실행 후 결과 받기 (처리된 행수)
			result = stmt.executeUpdate(sql);
			// 내가 실행할 sql문이 dml(insert, update, delete)문일 경우 
			// -> stmt.executeUpdate(sql문); :반환형 int
			// 내가 실행할 sql문이 select문일 경우 -> stmt.executeQuery(select문 sql문); : ResultSet

			// 6) 트랜잭션 처리
			if (result > 0) { // insert 성공했을 경우 -> commit
				conn.commit();
			} else { // insert 실패했을 경우 -> rollback
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("OracleDriver 클래스를 찾지 못했소.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			// 7) 다 쓴 jdbc용 객체 자원 반납 (생성된 역순으로)
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		if (result > 0) {
			System.out.println("성공적으로 삽입 되었습니다.");
		} else {
			System.out.println("삽입이 실패 되었습니다.");
		}
		 */
		
		// 2. 내 PC DB 상에 JDBC 계정에 TEST 테이블의 모든 데이터 조회
		//    select문 => 결과 ResultSet(조회된 데이터들 담겨 있음) 받기 => ResultSet으로 부터 데이터 뽑기
		
		// 필요한 변수 셋팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // select문 실행 후 조회된 결과값들이 처음에 실질적으로 담길 객체

		// 실행할 sql문
		String sql = "SELECT * FROM TEST WHERE TNAME LIKE '호%'";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3) Statemnet 객체 생성
			stmt = conn.createStatement();

			// 4, 5) SQL문을 전달해서 실행 후 결과 받기 (ResultSet 객체)
			rset = stmt.executeQuery(sql);

			// 6)
			while (rset.next()) { // 행 커서 움직여주는 역할, 뿐만 아니라 해당 행이 있다면 true, 없으면 false

				// 현재 참조하는 rset으로부터 어떤 컬럼에 해당하는 값을 어떤 타입으로 뽑을 지를 제시해야됨
				// db의 컬럼명 제시 ! (대소문자 가리지 않음)
				int tno = rset.getInt("TNO");
				String tname = rset.getString("TNAME");
				Date tdate = rset.getDate("TDATE");

				System.out.println(tno + ", " + tname + ", " + tdate);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 다 쓴 jdbc용 객체 반납
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
