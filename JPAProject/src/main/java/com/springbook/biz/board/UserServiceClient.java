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
		 = Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em = emf.createEntityManager();
		//Transaction 생성
		EntityTransaction tx = em.getTransaction();
		try{
			   //Transaction시작
			   tx.begin();
			   
			   Board board = new Board();
			   board.setTitle("JPA 제목");
			   board.setWriter("관리자");
			   board.setContent("JPA 글 등록 잘 되나요?");
			   
			   //글 등록
			   em.persist(board);
			   
			   //글 목록 조회
			   String jpql = "select b from Board b order by b.seq desc";
			   List<Board> boardList =
					            em.createQuery(jpql,Board.class).getResultList();
			   for(Board b:boardList){
				    System.out.println("----> " + b.toString());
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
