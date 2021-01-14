package com.member.model;

import java.util.List;

public class MemberService {
	
private MemberDAO_Interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO addMEM(String user_id, String user_pwd, String name, String phone, String nation, String email, String sexual, String note, java.sql.Date birthday, String personal_id, int status, String payment) {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setUser_id(user_id);
		memberVO.setUser_pwd(user_pwd);
		memberVO.setName(name);
		memberVO.setPhone(phone);
		memberVO.setNation(nation);
		memberVO.setEmail(email);
		memberVO.setSexual(sexual);
		memberVO.setNote(note);
		memberVO.setBirthday(birthday);
		memberVO.setPersonal_id(personal_id);
		memberVO.setStatus(status);
		memberVO.setPayment(payment);
		dao.addMember(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateMEM(String user_id, String user_pwd, String name, String phone, String nation, String email, String sexual, String note, java.sql.Date birthday, String personal_id, int status, String payment, String mem_id) {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setUser_id(user_id);
		memberVO.setUser_pwd(user_pwd);
		memberVO.setName(name);
		memberVO.setPhone(phone);
		memberVO.setNation(nation);
		memberVO.setEmail(email);
		memberVO.setNote(note);
		memberVO.setSexual(sexual);
		memberVO.setBirthday(birthday);
		memberVO.setPersonal_id(personal_id);
		memberVO.setStatus(status);
		memberVO.setPayment(payment);
		memberVO.setMem_id(mem_id);
		
		dao.updateMember(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateFrontMEM(String user_id, String name, String phone, java.sql.Date birthday, String personal_id, String nation, String sexual, String mem_id) {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setUser_id(user_id);
		memberVO.setName(name);
		memberVO.setPhone(phone);
		memberVO.setNation(nation);
		memberVO.setSexual(sexual);
		memberVO.setBirthday(birthday);
		memberVO.setPersonal_id(personal_id);
		memberVO.setMem_id(mem_id);
		
		dao.updateFrontMember(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateMemPwd(String user_pwd, String mem_id) {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setUser_pwd(user_pwd);
		memberVO.setMem_id(mem_id);
		
		dao.updatePwd(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateMemCredit(String payment, String mem_id) {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setPayment(payment);
		memberVO.setMem_id(mem_id);
		
		dao.updateCredit(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateMemStatus(int status, String mem_id) {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setStatus(status);
		memberVO.setMem_id(mem_id);
		
		dao.updateStatus(memberVO);
		
		return memberVO;
	}
	
	public MemberVO getOneMEM(String mem_id) {
		return dao.findByMemberId(mem_id);
	}
	
	public MemberVO getOneMEMByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	public List<MemberVO> getAllMEM(){
		return dao.getAllMember();
	}
	
	public MemberVO isUser(String email, String user_pwd) {
		return dao.isUser(email, user_pwd);
	}

}
