package employeeRun_240619;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeRun {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;

		while (true) {
			System.out.println("1. 사원추가");
			System.out.println("2. 사원전체조회");
			System.out.println("3. 사원수정");
			System.out.println("4. 사원삭제");
			System.out.println("0. 프로그램 종료");

			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();

			sc.nextLine();

			switch (menu) {
			case 1:
				System.out.print("직원명 : ");
				String name = sc.nextLine();

				System.out.print("주민등록번호 : ");
				String ssn = sc.nextLine();

				System.out.print("E-Mail : ");
				String email = sc.nextLine();

				System.out.print("연락처(- 제외) : ");
				String phone = sc.nextLine();

				System.out.print("부서코드 : ");
				String dCode = sc.nextLine();

				System.out.print("직급코드 : ");
				String jCode = sc.nextLine();

				System.out.print("급여등급 : ");
				String sLvl = sc.nextLine();

				System.out.print("급여 : ");
				int sal = sc.nextInt();

				System.out.print("보너스율 : ");
				double bns = sc.nextDouble();

				sc.nextLine();

				System.out.print("관리자 사번 : ");
				int aNum = sc.nextInt();

				String sql = "INSERT INTO COPY_EMP VALUES(SEQ_EMP_ID.NEXTVAL," 
				                                           + "'" + name + "', " 
						                                   + "'" + ssn + "', " 
				                                           + "'" + email + "', "
				                                           + "'" + phone + "', " 
				                                           + "'" + dCode + "', "
				                                           + "'" + jCode + "', "
				                                           + "'" + sLvl + "', " 
				                                           		 + sal + ", " 
				                                           		 + bns + ", "
				                                           		 + aNum + ", "
				                                           		 + "null" + ", "
				                                           		 + "null" + ", "
				                                           		 + "null" + ")";

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
					stmt = conn.createStatement();
					result = stmt.executeUpdate(sql);

					if (result > 0) {
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

				if (result > 0) {
					System.out.println("성공적으로 삽입 되었습니다.");
				} else {
					System.out.println("삽입이 실패 되었습니다.");
				}

			case 2: /**/;
				break;
			case 3: /**/;
			 	break;
			case 4:
				/**/;
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("메뉴 번호를 다시 입력해 주세요");
				break;
			}
		}
	}
}
