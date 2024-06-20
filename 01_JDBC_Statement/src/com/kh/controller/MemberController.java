package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

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
		
		int result = new MemberDao().insertMember(m);
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 추가 되었습니다.");
		} else {
			new MemberMenu().displayFail("회원 추가를 실패했습니다.");
		}
	}

	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		
		// 조회결과가 있는 지 없는 지 판단한 후 사용자가 보게 될 응답화면 지정
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("전체 조회 결과가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	/**
	 * 사용자의 아이디로 회원 검색 요청 처리해주는 메소드
	 * @param userId 사용자가 입력한 검색하고자 하는 회원 아이디값
	 */
	public void selectByUserId(String userId) {
		Member m = new MemberDao().selectByUserId(userId);
		if(m == null) { // 검색한 결과가 없을 경우
			new MemberMenu().displayNoData(userId + "에 해당하는 검색결과가 없습니다.");
		} else { // 검색한 결과가 있는 경우(조회된 데이터 한 행 있음)
			new MemberMenu().displayMember(m);
		}
	}
	
	public void searchNameKeyWord(String userName) {
		ArrayList<Member> ls = new MemberDao().selectByUserName(userName);
			if(ls.isEmpty()) {
				new MemberMenu().displayNoData(userName + "에 해당하는 검색 결과가 없습니다.");
			} else {
				new MemberMenu().displayMemberName(ls);
			}
		}
	}
	
