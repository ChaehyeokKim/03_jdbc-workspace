package com.kh.view;

import java.util.Scanner;

import com.kh.controller.MemberController;

// View : 사용자가 보게될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {

	// Scanner 객체 생성 (전역변수로 다 쓸 수 있도록)
	private Scanner sc = new Scanner(System.in);
	
	// MemberController 객체 생성 (전역에서 바로 요청할 수 있게끔)
	private MemberController mc = new MemberController();
	
	/**
	 * 사용자가 보게될 첫 화면 (메인 화면)
	 */
	public void mainMenu() {

		while (true) {

			System.out.println("\n== 회원 관리 프로그램 ==");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 정보 전체 조회");
			System.out.println("3. 회원 계정 검색");
			System.out.println("4. 회원 이름(키워드)으로 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");

			System.out.print("-- 메뉴 선택 -- : ");
			int menu = sc.nextInt();

			sc.nextLine();

			switch (menu) {
			case 1:
				inputMember();
				break;
			case 2: /**/
				break;
			case 3: /**/
				break;
			case 4: /**/
				break;
			case 5: /**/
				break;
			case 6: /**/
				break;
			case 0:
				System.out.println("이용해 주셔서 감사합니다.");
				return;
			default:
				System.out.println("메뉴 번호를 다시 입력해 주세요.");
				break;

			}
		}
	}
	
	/**
	 * 회원 추가 창 (서브화면)
	 * 즉, 추가하고자 하는 회원의 정보를 입력 받아서 회원 추가 요청하는 창
	 */
	public void inputMember() {

		System.out.println("\n=== 회원 추가 ===");
		// 아이디 ~ 취미 까지 입력 받기
		System.out.print("아이디 : ");
		String userId = sc.nextLine();

		System.out.print("비밀 번호 : ");
		String userPwd = sc.nextLine();

		System.out.print("이름 : ");
		String userName = sc.nextLine();

		System.out.print("성별(M/F) : ");
		String gender = sc.nextLine().toUpperCase();

		System.out.print("나이 : ");
		String age = sc.nextLine();

		System.out.print("E-mail : ");
		String email = sc.nextLine();

		System.out.print("연락처(- 제외) : ");
		String phone = sc.nextLine();

		System.out.print("주소 : ");
		String address = sc.nextLine();

		System.out.print("취미(,로 연이어서 작성) : ");
		String hobby = sc.nextLine();

		// 회원 추가 요청 == Controller 메소드 호출
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}

}