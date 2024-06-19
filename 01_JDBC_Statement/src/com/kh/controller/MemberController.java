package com.kh.controller;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

// Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
// 				해당 매소드로 전달된 데이터[가공처리한 후]를 Dao로 전달하면서 호출
//  			Dao로 부터 반환 받은 결과에 따라 성공인 지 실패인 지 판단 후 응답화면 결정

public class MemberController {

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, 
							 String age, String email, String phone, String address, String hobby) {
		// view로 부터 전달받은 값을 바로 dao쪽으로 전달 x
		// 어딘가(Member 객체)에 주섬주섬 담아서 전달
		
		// 1. 방법 1) 기본 생성자로 생성한 뒤에 각 필드를 setter 메소드를 통해 일일히 담는 방법
		//    방법 2) 매개변수 생성자로 생성과 동시에 담는 방법
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		// System.out.println(m);
		
		new MemberDao().insertMember(m);
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
