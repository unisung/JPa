package com.springbook.biz.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserServiceClient {

	public static void main(String[] args) {
		//EntityManager생성
		EntityManagerFactory emf
		 = Persistence.createEntityManagerFactory("JPAProject02");
		EntityManager em = emf.createEntityManager();
		//Transaction 생성
		EntityTransaction tx = em.getTransaction();
		try{
			   //Transaction시작
			   tx.begin();
			   
			    User user = new User();
			    user.setId("hong");
			    user.setName("홍길동");
			    user.setPassword("1234");
			    
			   //회원 등록
			   em.persist(user);
			   
			   //회원 리스트  조회
			   String jpql = "select b from User b order by b.id desc";
			   List<User> userList =
					            em.createQuery(jpql,User.class).getResultList();
			   for(User u:userList){
				    System.out.println("----> " + u.toString());
			   }
			   
			   //Transaction commit;
			   tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			em.close();
		}
		
		emf.close();
	}

}
