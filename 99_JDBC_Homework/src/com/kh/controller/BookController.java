package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.BookDao;
import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;
import com.kh.view.BookMenu;

public class BookController {

	public ArrayList<Book> selectBook() {
		ArrayList<Book> blist = new BookDao().selectBookList();
		
		if(blist.isEmpty()) {
			new BookMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new BookMenu().displayBookList(blist);
		}
		return blist;
	}
	
	public ArrayList<Magazine> selectMagazine() {
		ArrayList<Magazine> mlist = new BookDao().selectMagazineList();
		
		if(mlist.isEmpty()) {
			new BookMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new BookMenu().displayMagazine(mlist);
		}
		return mlist;
	}
	
	public int deletebyMember(String userId) {
		
		Member m = new Member();
	}
	
}
