package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * 
	 * @param userId ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email,
			String phone, String address, String hobby) {
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email,  phone, address, hobby);
		
		int result = new MemberDao().insertMember(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 추가 되었습니다.");
		} else {
			new MemberMenu().displayFail("회원 추가 실패 했습니다.");
		}
	}

	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		
		ArrayList<Member> list = new MemberDao().selectList();
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("전체 조회 결과가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}

	/**
	 * 사용자의 아이디로 회원 검색 요청 처리해주는 메소드
	 * 
	 * @param userId 사용자가 입력한 검색하고자 하는 회원 아이디값
	 */
	public void selectByUserId(String userId) {
		
		Member m = new MemberDao().selectByUserId(userId);
		
		if(m == null) {
			new MemberMenu().displayNoData(userId + "에 해당하는 결과가 없습니다.");
		} else {
			new MemberMenu().displayMember(m);
		}
	}

	/**
	 * 사용자의 이름으로 회원 검색 요청 처리해주는 메소드
	 * 
	 * @param userName 사용자가 입력한 검색 하고자 하는 회원 이름 값
	 */
	public void selectByUserName(String kw) {
		
		ArrayList<Member> list = new MemberDao().selectByUserName(kw);
		
		if (list.isEmpty()) {
			new MemberMenu().displayNoData(kw + "에 해당하는 결과가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
		
	}

	/**
	 * 정보 변경 요청 처리 해주는 메소드
	 * 
	 * @param userId  : 변경할 아이디
	 * @param userPwd : 변경할 비밀번호
	 * @param email   : 변경할 이메일
	 * @param phone   : 변경할 전화번호
	 * @param address : 변경할 주소
	 */
	public void updateMember(String userId, String userPwd, String email, String phone, String address) {
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		
		int result = new MemberDao().updateMember(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 변경 했습니다.");
		} else {
			new MemberMenu().displayFail("회원 정보 변경에 실패했습니다.");
		}
	}

	/**
	 * 회원 탈퇴 요청 처리 해주는 메소드
	 * 
	 * @param userId 사용자가 입력한 탈퇴시키고자 하는 회원 아이디 값
	 */
	public void deleteMember(String userId) {
		
		int result = new MemberDao().deleteMember(userId);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 탈퇴 되었습니다.");
		} else {
			new MemberMenu().displayFail("회원 탈퇴에 실패했습니다.");
		}
	}
}
	
