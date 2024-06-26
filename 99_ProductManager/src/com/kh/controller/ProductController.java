package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.service.MemberService;
import com.kh.model.service.ProductService;
import com.kh.model.vo.Member;
import com.kh.model.vo.Product;
import com.kh.view.MemberMenu;
import com.kh.view.ProductMenu;

public class ProductController {

	public void selectProduct() {
		ArrayList<Product> list = new ProductService().selectProduct();
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData("전체 조회 결과가 없습니다.");
		} else {
			new ProductMenu().displayProductList(list);
		}
		
		public void insertProduct(String pId, String pName, int price, String descript, int stock) {
			
			Product p = new Product(pId, pName, price, descript, stock);
			 
			int result = new ProductService().insertProduct(p);
			
			if(result > 0) {
				new ProductMenu().displaySuccess("성공적으로 추가 되었습니다.");
			} else {
				new ProductMenu().displayFail("추가 실패 했습니다.");
			}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
