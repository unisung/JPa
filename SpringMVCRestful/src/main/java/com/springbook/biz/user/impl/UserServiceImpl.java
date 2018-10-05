package com.springbook.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Component("userService")
public class UserServiceImpl implements UserService {
   @Autowired
	private UserDAOJPA userDao;
	@Override
	public UserVO getUser(UserVO vo) { return userDao.getUser(vo);	}
	@Override
	public void insertUser(UserVO vo) {userDao.insertUser(vo);	}
	@Override
	public List<UserVO> getUserList(UserVO vo) {
		return userDao.getUserList(vo);
	}
	@Override
	public void updateUser(UserVO vo) {	userDao.updateUser(vo);	}
	@Override
	public void deleteUser(UserVO vo) {	userDao.deleteUser(vo);	}

}
