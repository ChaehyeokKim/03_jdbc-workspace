package com.kh.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.kh.controller.BookController;
import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;

public class BookMenu {

	// Scanner 객체 생성 (전역변수로 다 쓸 수 있도록)
		private Scanner sc = new Scanner(System.in);
		
		// MemberController 객체 생성 (전역에서 바로 요청할 수 있게끔)
		private BookController bc = new BookController();	

		public void mainMenu() {

			while (true) {
				System.out.println("\n-- 도서 관리/조회 프로그램 --");
				System.out.println("1. 조회하기");
				System.out.println("2. 추가하기");
				System.out.println("3. 책 찾기");
				System.out.println("4. 전체책 가격 합계 및 평균 조회");
				System.out.println("5. 프로그램 종료");
				System.out.println("===============================");
				
				System.out.print("메뉴번호를 입력해주세요 : ");
				int menu = sc.nextInt();
				
				switch (menu) {
				case 1 : // 도서조회
					System.out.println("===============================");
					System.out.println("어떤 방법으로 책을 조회하겠습니까?");
					System.out.println("1. 도서 전체 조회하기");
					System.out.println("2. 일반도서만 조회하기(잡지 제외)");
					System.out.println("3. 잡지만 조회하기(일반도서 제외)");
					System.out.println("4. 이전으로");
					System.out.println("===============================");
					
					sc.nextLine();
					
					System.out.print("메뉴 번호를 입력해 주세요 : ");
					int search = sc.nextInt();
					
					if (search == 1) {
						// ArrayList<Book> list = bc.getAllBook();
						// System.out.println(list);
					}
					else if (search == 2) {
						ArrayList<Book> blist = bc.selectBook();
					}
					else if (search == 3) {
						ArrayList<Magazine> mlist = bc.selectMagazine();
					}
					else if (search == 4) {	
					}
					break;
					
				case 2 :	 // 2. 추가하기
					System.out.print("bNo을 입력하세요 : ");
					String bNo = sc.nextLine();
					sc.nextLine();
					
					System.out.print("책 제목을 입력하세요 : ");
					String title = sc.nextLine();
					
					
					System.out.print("작가를 입력하세요 : ");
					String author = sc.nextLine();
					
					
					System.out.print("출판사를 입력하세요 : ");
					String publisher = sc.nextLine();
							
					
					System.out.print("가격을 입력하세요 : ");
					int price = sc.nextInt();
					
					sc.nextLine();
					
					System.out.print("간단한 설명을 입력하세요 : ");
					String description = sc.nextLine();
					
					System.out.print("일반도서이면 true, 잡지이면 false를 입력하세요 : ");
					Boolean bkOrMaga = sc.nextBoolean();
					
					if (bkOrMaga.equals(true)) {
						
					} else if (bkOrMaga.equals(false)) {
						System.out.print("출간날짜를 입력하세요 (ex) yyyy/mm) : ");
						String pub_Date = sc.next();
					}
					break;
				case 3 :	
					/**/
					break;
				case 4 :	
					/**/
					break;
				case 5 : 
					System.out.println("프로그램을 종료합니다.");
					return;
				}
			}
			}
			public void displayBook(Book b) {
				System.out.println("\n 조회된 데이터는 다음과 같습니다. \n");
				System.out.println(b);
		}
			public void displayMagazine(ArrayList<Magazine> mlist) {
				System.out.println("\n 조회된 데이터는 다음과 같습니다. \n");
				for(Magazine m : mlist) {
					System.out.println(m);					
				}
		}
			
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		public void displayNoData(String message) {
			System.out.println("\n" + message);
		}	
			
		public void displayBookList(ArrayList<Book> blist) {
			System.out.println("\n조회된 데이터는 다음과 같습니다.");
			for(Book b : blist) {
				System.out.println(b);
			}
		}
			
	}