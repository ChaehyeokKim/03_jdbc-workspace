package com.kh.model.service;

import java.sql.Connection;

import com.kh.common.BookTemplate;
import com.kh.model.dao.BookDao;
import com.kh.model.vo.Book;

public class BookService {

	public int insertBook(Book b) {
		
		Connection conn = BookTemplate.BookConnection();
		int result = new BookDao().insertMember(conn, b);
				
		if (result > 0) {
			BookTemplate.commit(conn);
		} else {
			BookTemplate.rollback(conn);
		}
		
		BookTemplate.close(conn);
		
		return result;
	}
}
