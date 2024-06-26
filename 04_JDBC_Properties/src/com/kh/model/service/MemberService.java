package com.kh.model.service;

import java.sql.Connection;
import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {
	// 1. jdbc driver 등록
	// 2. Connection 객체 생성
	
	 public int insertMember(Member m) {
	      
	      Connection conn = /*JDBCTemplate.*/getConnection();
	      int result = new MemberDao().insertMember(conn, m);
	      
	      // 6) 트랜잭션 처리
	      if(result > 0) {
	         /*JDBCTemplate.*/commit(conn);
	      }else {
	         /*JDBCTemplate.*/rollback(conn);
	      }
	      
	      
	      /*JDBCTemplate.*/close(conn); // 트랜잭션 처리 없어도 conn은 여기서 반납해
	      
	      return result;
	   
	   }
	   
	   public ArrayList<Member> selectList() {
	      
	      Connection conn = /*JDBCTemplate.*/getConnection();
	      
	      ArrayList<Member> list = new MemberDao().selectList(conn);
	      
	      /*JDBCTemplate.*/close(conn);
	      
	      return list;
	      
	   }
	   
	   public Member selectByUserId(String userId) {
	      
	      Connection conn = /*JDBCTemplate.*/getConnection();
	      Member m = new MemberDao().selectByUserId(conn, userId);
	      
	      close(conn);
	      return m;
	      
	   }
	   
	   public ArrayList<Member> selectByUserName(String keyword){
	      
	      Connection conn = getConnection();
	      ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
	      
	      close(conn);
	      return list;
	   }
	   
	   public int updateMember(Member m) {
	      
	      Connection conn = getConnection();
	      int result = new MemberDao().updateMember(conn, m);
	      
	      if(result > 0) {
	         commit(conn);
	      }else {
	         rollback(conn);
	      }
	      
	      close(conn);
	      return result;
	   }
	   
	 public int deleteMember(Connection conn, String userId) {
		 
		 conn = getConnection();
		 int result = new MemberDao().deleteMember(conn, userId);
		 
		 if(result > 0) {
			 commit(conn);
		 } else {
			 rollback(conn);
		 }
		 
		 close(conn);
		 return result;
	 }
	 
	 public String loginMember(String userId, String userPwd) {
		 
		 Connection conn = getConnection();
		 
		 String userName = new MemberDao().loginMember(conn, userId, userPwd);
		
		 close(conn);
		 
		 return userName;
	 }
}
