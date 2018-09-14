package com.springbook.biz.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAClient {
	public static void main(String[] args) {
		//persistence.xml파일에서 정보를 읽어서
		//EntityMangeFactory 생성
	  EntityManagerFactory emf
	    = Persistence.createEntityManagerFactory("JPAProject02");
	  //EntityManager생성
	  EntityManager em = emf.createEntityManager();
	  //Transaction Manager생성
	  EntityTransaction tx = em.getTransaction();
	  
	  try{
		      //JPA transaction시작
		  	   tx.begin();
		  	   
		  	   Board board = new Board();
		  	   board.setTitle("JPA02 제목1");
		  	   board.setWriter("JPA관리자1");
		  	   board.setContent("JPA02 글 등록 잘된나요???");
		  	   
		  	   Board board2 = new Board();
		  	   board2.setTitle("JPA02 제목2");
		  	   board2.setWriter("JPA관리자2");
		  	   board2.setContent("JPA02 글 등록 잘된나요???");
		  	   
		  	   //글 등록
		  	   em.persist(board);
		  	   em.persist(board2);
		  	   
		  	   //글 목록 조회
		  	   String jpql = "select  b from Board b order by b.seq desc";
		  	   List<Board> list = 
		  			            em.createQuery(jpql,Board.class).getResultList();
		  	   
		  	   for(Board b:list){
		  		   System.out.println(b.toString());
		  	   }
		  	   
		    //커밋처리--jpa는 트랜잭션 작업을 commit전까지 
		  	//저장했다가,커밋시 dbms에 한번에 전달처리-네트워크 트래픽을 줄여줌
		    tx.commit();
	  }catch(Exception e){
		    tx.rollback();
		  e.printStackTrace();
	  }finally{
		  em.close();
	  }
		emf.close();
	}

}
