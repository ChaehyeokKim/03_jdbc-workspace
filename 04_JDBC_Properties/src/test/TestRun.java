package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class TestRun {

	public static void main(String[] args) {

		// Properties 복습
		
		/* 
		 * * Properties 특징
		 * - Map 계열 컬렉션 (key + value 세트로 담는 특징)
		 * - key, value 모두 String (문자열) 으로 담기
		 * 	 setProperty(String key, String value);
		 * 	 getProperty(String key) : String(value)
		 * 
		 * - 주로 외부 파일(.properties / .xml)로 입/출력할 때 사용
		 */
		
		// CRUD : DB에서 사용하는 것들.
		
		/* 파일로 출력하는 거 연습
		Properties prop = new Properties();
		prop.setProperty("C", "INSERT");
		prop.setProperty("R", "SELECT");
		prop.setProperty("U", "UPDATE");
		prop.setProperty("D", "DELETE");
		
		try {
			prop.store(new FileOutputStream("resources/test.properties"), "properties Test");
			prop.storeToXML(new FileOutputStream("resources/test.xml"), "properties Test");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	*/
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 존재하지 않는 key값을 제시하면 null로 반환
		System.out.println(prop.getProperty("driver"));
		System.out.println(prop.getProperty("url"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password")); 
	}
}
