package com.springbook.biz.user.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.springbook.biz.user.UserVO;

@Repository("userDao")
public class UserDAOJPA{
@PersistenceContext
private EntityManager em;
	
	 //회원정보
	 public UserVO getUser(UserVO vo) {
		 System.out.println("===> em로 getUser() 기능 처리");
			
		 try {
		   vo = em.find(UserVO.class, vo.getUserId());
		  System.out.println("mapper:"+vo.getName());

		 }catch(Exception e) {
			 e.printStackTrace();
		 }		
		 return vo;
	 }
	 
	//회원등록
		public void insertUser(UserVO vo) {
			 System.out.println("===> em로 insertUser() 기능 처리");
			 em.persist(vo);
			
		}
		
		public List<UserVO> getUserList(UserVO vo) {
			 System.out.println("===> em로 getUserList() 기능 처리");
			  String jpql = "select u from UserVO u order by userid desc";
			  List<UserVO> list = em.createQuery(jpql).getResultList();
			  System.out.println("결과:"+list.size());
			   return  list;
				/*return  em.createQuery(jpql).getResultList();*/	
		}
		
		public void updateUser(UserVO vo) {
			System.out.println("===> em로 updateUser() 기능 처리");
			 em.merge(vo);
		}
		
		public void deleteUser(UserVO vo) {
			System.out.println("===> em로 deleteUser() 기능 처리");
			 em.remove(vo);
		}
		
		
		
	/*
	public UserVO getUser(UserVO vo) {
		 String sql = "select * from users where id=?";
			try {
	    	     conn = JDBCUtil.getConnection();
	    	     pstmt = conn.prepareStatement(sql);
	    	     pstmt.setString(1, vo.getId());
	    	     rs =pstmt.executeQuery();
	    	     if(rs.next()) {	 
	    	    	 vo.setId(rs.getString(1));
	    	    	 vo.setName(rs.getString(3));
	    	    	 vo.setPassword(rs.getString(2));
	    	    	 vo.setRole(rs.getString(4));
	    	     }
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }finally {
	    	 JDBCUtil.close(rs,pstmt, conn);
	     }
		return vo;
	}
	//회원등록
	public void insertUser(UserVO vo) {
      String sql = "insert into users(id,name,password,role) values(?,?,?,?)";
		try {
    	     conn = JDBCUtil.getConnection();
    	     pstmt = conn.prepareStatement(sql);
    	     pstmt.setString(1, vo.getId());
    	     pstmt.setString(2, vo.getName());
    	     pstmt.setString(3, vo.getPassword());
    	     pstmt.setString(4, vo.getRole());
    	     pstmt.executeUpdate();
     }catch(Exception e) {
    	 e.printStackTrace();
     }finally {
    	 JDBCUtil.close(pstmt, conn);
     }
		
	}
	public List<UserVO> getUserList(UserVO vo) {
		List<UserVO> list= new ArrayList<>();
		  String sql = "select * from users order by id";
			try {
	    	     conn = JDBCUtil.getConnection();
	    	     pstmt = conn.prepareStatement(sql);
	    	     rs =pstmt.executeQuery();
	    	     while(rs.next()) {
	                 vo = new UserVO();
	    	    	 
	                 vo.setId(rs.getString(1));
	    	    	 vo.setName(rs.getString(3));
	    	    	 vo.setPassword(rs.getString(2));
	    	    	 vo.setRole(rs.getString(4));
	    	    	 
	    	    	 list.add(vo);
	    	     }
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }finally {
	    	 JDBCUtil.close(rs,pstmt, conn);
	     }
		return list;
	}
	
	public void updateUser(UserVO vo) {
		String sql = "update users set password=?,role=? where id=?";
		try {
    	     conn = JDBCUtil.getConnection();
    	     pstmt = conn.prepareStatement(sql);
    	     pstmt.setString(3, vo.getId());
    	     pstmt.setString(1, vo.getPassword());
    	     pstmt.setString(2, vo.getRole());
    	     pstmt.executeUpdate();
     }catch(Exception e) {
    	 e.printStackTrace();
     }finally {
    	 JDBCUtil.close(pstmt, conn);
     }
	}//updateUser()메소드 끝.
	
	
public void deleteUser(UserVO vo) {
	String sql = "delete from users where id=?";
		try {
    	     conn = JDBCUtil.getConnection();
    	     conn.setAutoCommit(false);
    	     pstmt = conn.prepareStatement(sql);
    	     pstmt.setString(1, vo.getId());
    	     pstmt.executeUpdate();
    	     conn.commit();
     }catch(Exception e) {
    	 try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	 e.printStackTrace();
     }finally {
    	  try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {	e.printStackTrace();
		}
    	 JDBCUtil.close(pstmt, conn);
     }		
}*/
	
	

}
