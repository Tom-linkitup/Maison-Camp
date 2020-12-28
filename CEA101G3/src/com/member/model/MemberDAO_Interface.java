package com.member.model;

import java.util.List;

public interface MemberDAO_Interface {
	public void addMember(MemberVO memberVO);
	public void updateMember(MemberVO memberVO);
	public void updateFrontMember(MemberVO memberVO);
	public void updatePwd(MemberVO memberVO);
	public void updateCredit(MemberVO memberVO);
	public void updateStatus(MemberVO memberVO);
	public MemberVO findByEmail(String email);
	public MemberVO findByMemberId(String mem_id);
	public List<MemberVO> getAllMember();
	public MemberVO isUser(String email, String user_pwd);
}
