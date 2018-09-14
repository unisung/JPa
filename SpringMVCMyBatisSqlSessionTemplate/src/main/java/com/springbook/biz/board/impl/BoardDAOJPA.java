package com.springbook.biz.board.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import com.springbook.biz.board.BoardVO;

@Repository("boardDAO")//Dao용도 어노테이션
public class BoardDAOJPA{
 @PersistenceContext
 private EntityManager em;

  	
  //글등록
	public void insertBoard(BoardVO vo) {
		
		System.out.println("===> JPA로 insertBoard() 기능 처리");
		em.persist(vo);
	}
	/*   vo.getSeq() = 1
	   vo.setTitle="테스트"
	   
	   vo.getSeq() = 1
	   vo.setTitle="테스트2"
			   */
	//내용수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JPA로 updateBoard() 기능 처리");
		em.merge(vo);
	}
		
	//삭제 처리
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JPA로 deleteBoard() 기능 처리");
		em.remove(getBoard(vo));
	}
	
	//내용 보기	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JPA로 getBoard() 기능 처리");
		return (BoardVO)em.find(BoardVO.class, vo.getSeq());
	}	
	
	//리스트 보기
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JPA로 getBoardList() 기능 처리");
		System.out.println("searchCondition="+vo.getSearchCondition());
		System.out.println("searchKeyword="+vo.getSearchKeyword());
		String jpql="from BoardVO b order by b.seq desc";
		return em.createQuery(jpql).getResultList();
	}
	
		
	 //조회수 증가
	public void updateBoardViewCount(BoardVO vo) {
		System.out.println("===> JPA로 updateViewCount() 기능 처리");
		System.out.println("vo.seq="+vo.getSeq());
		System.out.println("vo.title="+vo.getTitle());
		System.out.println("vo.content="+vo.getContent());
		vo = getBoard(vo);
		int cnt = vo.getCnt()+1;
		vo.setCnt(cnt);
		em.merge(vo);
	}
	
	/*//글등록
	public void insertBoard(BoardVO vo) {
		String sql = "insert into board(seq,title,writer,content,filename) "
				       + " values((select nvl(max(seq),0)+1 from board),?,?,?,?)";
		try{
			  conn = JDBCUtil.getConnection();
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setString(1,vo.getTitle());
			  pstmt.setString(2, vo.getWriter());
			  pstmt.setString(3, vo.getContent());
			  pstmt.setString(4, vo.getFilename());
			  pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	//내용수정
	public void updateBoard(BoardVO vo) {
		String sql = "update board set title=?,content=?";
		if(!(vo.getFilename()==null|| vo.getFilename().equals(""))) {
			sql = sql+", filename=? ";
		 }
		sql = sql+" where seq=?";
		
	try{
		  conn = JDBCUtil.getConnection();
		  pstmt = conn.prepareStatement(sql);
		  pstmt.setString(1,vo.getTitle());
		  pstmt.setString(2, vo.getContent());
		
		  if(!(vo.getFilename()==null|| vo.getFilename().equals(""))) {
			  pstmt.setString(3, vo.getFilename());
			  pstmt.setInt(4, vo.getSeq());
		  }else {
			  pstmt.setInt(3, vo.getSeq());
		  }
		  
		  pstmt.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		JDBCUtil.close(pstmt, conn);
	}
		
	}
	//삭제 처리
	public void deleteBoard(BoardVO vo) {
		String sql = "delete from board where seq=?";
		try{
			  conn = JDBCUtil.getConnection();
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setInt(1, vo.getSeq());
			  pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	//내용보기
	public BoardVO getBoard(BoardVO vo) {
	 try {
		 	//조회수 증가
		    updateBoardViewCount(vo);
		    //내용보기 처리
		    String sql = "select * from board where seq=?";
		     conn=JDBCUtil.getConnection();
		     pstmt = conn.prepareStatement(sql);
		     pstmt.setInt(1, vo.getSeq());
		     rs = pstmt.executeQuery();
		     if(rs.next()) {
		    	 vo.setTitle(rs.getString(2));
		    	 vo.setWriter(rs.getString(3));
		    	 vo.setContent(rs.getString(4));
		    	 vo.setRegdate(rs.getDate(5));
		    	 vo.setCnt(rs.getInt(6));
		    	 vo.setFilename(rs.getString(7));
		     }
	 }catch(Exception e) {
		 e.printStackTrace();
	 }finally {
		 JDBCUtil.close(rs,pstmt,conn);
	 }
		return vo;
	}
	
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		List<BoardVO> list =new ArrayList<>();
		System.out.println("searchCondition="+vo.getSearchCondition());
		try {
			    String sql = "select * from board ";
			  
			   if(vo.getSearchCondition().equals("TITLE")) {
			       sql = sql + " where title like ? ";	
			    }else if(vo.getSearchCondition().equals("content")) {
			      sql = sql + " where content like ?";
			    }
			    
			     sql = sql+" order by seq";
			     
			     System.out.println("sql="+sql);
			     
			     conn = JDBCUtil.getConnection();
			     pstmt = conn.prepareStatement(sql);
			     
			    if(vo.getSearchCondition().equals("TITLE")) {
				      pstmt.setString(1, "%"+vo.getSearchKeyword()+"%");
				    }else if(vo.getSearchCondition().equals("content")) {
				      pstmt.setString(1, "%"+vo.getSearchKeyword()+"%");
				    }
			     
			     rs = pstmt.executeQuery();
			     
			     while(rs.next()) {
			    	 BoardVO vo1 = new BoardVO();
			    	 
			    	 vo1.setSeq(rs.getInt(1));
			    	 vo1.setTitle(rs.getString(2));
			    	 vo1.setWriter(rs.getString(3));
			    	 vo1.setContent(rs.getString(4));
			    	 vo1.setRegdate(rs.getDate(5));
			    	 vo1.setCnt(rs.getInt(6));
			    	 
			    	 list.add(vo1);
			     }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,pstmt,conn);
		}
		return list;
	}
	
	  //조회수 증가
		public void updateBoardViewCount(BoardVO vo) {
			String sql = "update board set cnt = cnt+1 where seq=?";
		try{
			  conn = JDBCUtil.getConnection();
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setInt(1, vo.getSeq());
			  pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
			
		}//updateBoardViewCount()메소드 끝.
*/
  
}
