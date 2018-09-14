package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	//동일한 요청에 전송방식이 get인경우와 post인 경우로 구분
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login(UserVO vo) {
    	 
		return "login.jsp";
	}
     @RequestMapping(value="/login.do",method=RequestMethod.POST)
     public String loginOk(UserVO vo, HttpSession session) {
    	 UserVO user = userService.getUser(vo);
    	 
    	 
    	 if(user!=null) {
    		 session.setAttribute("user", user);
    		 return "getBoardList.do";
    	 }else {
    		 return "login.jsp";
    	 }
     }
     
     @RequestMapping("/logout.do")
 	public String  logout(HttpSession session) {
 	
 		//브라우저와 연결된 session 객체 종료
 	     session.invalidate();
 	  
 	 return "login.jsp";
 		
 	}
}
