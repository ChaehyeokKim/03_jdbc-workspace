package com.kh.model.vo;

public class Magazine extends Book {
	private String pub_date;
	
	
	public Magazine() {}

	public Magazine(String bNo, String title, String author, String publisher, int price, String description, String pub_date) {
		super(bNo, title, author, publisher, price, description);
		this.pub_date = pub_date;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + pub_date;
	}

}

