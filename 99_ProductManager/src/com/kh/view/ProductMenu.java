package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {

	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();


	
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n== 제품 관리 프로그램 ==");
			System.out.println("1. 전체 조회하기");
			System.out.println("2. 상품 추가하기");
			System.out.println("3. 상품 수정하기 (제품 Id로 검색)");
			System.out.println("4. 상품 삭제하기 (제품 Id로 검색)");
			System.out.println("5. 상품 검색하기 (키워드 입력)");
			System.out.println("0. 프로그램 종료");
			
			System.out.print(">> 메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			
			sc.nextLine();
			
			switch(menu) {
			case 1 :
				pc.selectProduct();
				break;
			case 2 :
				inputProduct();
				break;
			case 3 :
				//updateProduct();
				break;
			case 4 :
				//deleteProduct();
				break;
			case 5 :
				
			case 0 :
				System.out.println("프로그램을 종료 합니다");
				return;
			default :
				System.out.println("메뉴 번호를 다시 입력해 주세요");
				break;
			}	
		}
	}

	public void inputProduct() {
		
		System.out.println("\n=== 상품 추가 ===");
		System.out.print("상품 아이디 : ");
		String pId = sc.nextLine();

		System.out.print("상품명  : ");
		String pName = sc.nextLine();

		System.out.print("가격 : ");
		int price = sc.nextInt();

		sc.nextLine();
		
		System.out.print("상품 정보 : ");
		String descript = sc.nextLine();

		System.out.print("재고 : ");
		int stock = sc.nextInt();

		sc.nextLine();
		
		pc.insertMember(pId, pName, price, descript, stock);
	}




// ------------------------------------------------------------------------------

public void displaySuccess(String message) {
	System.out.println("\n서비스 요청 성공 : " + message);
}

public void displayFail(String message) {
	System.out.println("\n서비스 요청 실패 : " + message);
}

public void displayNoData(String message) {
	System.out.println("\n" + message);
}

public void displayProductList(ArrayList<Product> list) {
	System.out.println("\n조회된 데이터는 다음과 같습니다. \n");

	for (Product p : list) {
		System.out.println(p);
	}
}

}

