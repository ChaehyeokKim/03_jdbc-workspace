package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

import static com.kh.common.JDBCTemplate.*;

public class ProductService {

	public ArrayList<Product> selectProduct() {
		
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().selectProduct(conn);
		
		close(conn);
		
		return list;
	}
	
	
}
