package com.springbook.biz.user;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {
	public static void main(String[] args) {
	 BeanFactory factory
	   = new GenericXmlApplicationContext("applicationContext.xml");
	 UserService userService 
	       = (UserService)factory.getBean("userService");
	 
	 //회원등록
	 UserVO vo = new UserVO();
	 Scanner scanner = new Scanner(System.in);
	/* System.out.println("id:");
	 String id = scanner.next();
	 vo.setId(id);
	 System.out.println("name:");
	 String name = scanner.next();
	 vo.setName(name);
	 System.out.println("password:");
	 String password = scanner.next();
	 vo.setPassword(password);
	 System.out.println("role:");
	 String role = scanner.next();
	 vo.setRole(role);
	 
	 userService.insertUser(vo);
	 System.out.println("회원가입 완료");*/
	 
	 //회원리스트
	 List<UserVO> list = userService.getUserList(vo);
	 for(UserVO l:list) {
		 System.out.println(l.toString());
	 }
	 
	 //회원정보
	 //vo.setId(list.get(list.size()-1).getId());
	 vo = userService.getUser(vo);
	 System.out.println();
	 System.out.println("회원정보:");
	 System.out.println(vo.toString());
	 	 
	 //회원정보 수정
	/* vo.setId(list.get(0).getId());
	 System.out.println("수정할 패스워드:");
	 String password = scanner.next();
	 vo.setPassword(password);
	 System.out.println("수정할 권한:");
	 String role = scanner.next();
	 vo.setRole(role);
	 //수정 처리
	 userService.updateUser(vo);
	 System.out.println("회원정보수정완료");
	 
	 //수정된 회원정보 보기
	 vo = userService.getUser(vo);
	 System.out.println(vo.toString());*/

	 
	 //회원탈퇴
	 System.out.println("탈퇴 처리회원 아이디:");
	 String outId = scanner.next();
	 System.out.println("정말?");
	 char y = scanner.next().charAt(0);
	 if(y=='y' || y=='Y') {
	  //  vo.setId(outId);
	    userService.deleteUser(vo);
	    System.out.println("탈퇴 완료");
	 }
	 //탈퇴후 리스트
	 list = userService.getUserList(null);
	 for(UserVO u:list) {
		 System.out.println(u.toString());
	 }
	}

}
