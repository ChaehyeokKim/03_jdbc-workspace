package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;

public class BookDao {

	public ArrayList<Book> selectBookList() {
		ArrayList<Book> blist = new ArrayList<Book>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM BOOK";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ch", "ch");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Book b = new Book();

				b.setbNo(rset.getString("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setAuthor(rset.getString("AUTHOR"));
				b.setPublisher(rset.getString("PUBLISHER"));
				b.setPrice(rset.getInt("PRICE"));
				b.setDescription(rset.getString("DESCRIPTION"));

				blist.add(b);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return blist;
	}

	public ArrayList<Magazine> selectMagazineList() {
		ArrayList<Magazine> mlist = new ArrayList<Magazine>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MAGAZINE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ch", "ch");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Magazine m = new Magazine();

				m.setbNo(rset.getString("BNO"));
				m.setTitle(rset.getString("TITLE"));
				m.setAuthor(rset.getString("AUTHOR"));
				m.setPublisher(rset.getString("PUBLISHER"));
				m.setPrice(rset.getInt("PRICE"));
				m.setDescription(rset.getString("DESCRIPTION"));
				m.setPub_date(rset.getString("PUB_DATE"));

				mlist.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mlist;
	}
		
}


