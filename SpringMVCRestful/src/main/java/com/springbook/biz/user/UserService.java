package com.springbook.biz.user;

import java.util.List;

public interface UserService {
	//회원정보
	public UserVO getUser(UserVO vo);
	//회원등록
	public void insertUser(UserVO vo);
	//회원리스트
	public List<UserVO> getUserList(UserVO vo);
	//회원수정
	public void updateUser(UserVO vo);
	//회원탈퇴
	public void deleteUser(UserVO vo);

}
