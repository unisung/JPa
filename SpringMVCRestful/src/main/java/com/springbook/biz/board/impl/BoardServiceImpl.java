package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Service("boardService")
/*@Component("boardService")*/
public class BoardServiceImpl implements BoardService {
    //bean중에서 boardDAO로 된 객체를 찾아서 자동으로 주입 어노테이션
	@Autowired
	private BoardDAOJPA boardDAO;
	 
	//게시글 등록
	@Override
	public void insertBoard(BoardVO vo) {
/*		System.out.println("vo.seq="+vo.getSeq());
		System.out.println("vo.title="+vo.getTitle());
		System.out.println("vo.content="+vo.getContent());*/
     boardDAO.insertBoard(vo);
     //commit, rollback
	}

	//게시글 내용 수정
	@Override
	public void updateBoard(BoardVO vo) {
    boardDAO.updateBoard(vo);

	}

	//게시글 삭제
	@Override
	public void deleteBoard(BoardVO vo) throws Exception {
/*		if(vo.getSeq()==1) {
		 throw new Exception("1번글이 존재하지 않습니다.");
		}*/
		 boardDAO.deleteBoard(vo);

	}

	//게시글 내용보기
	@Override
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}

	//게시글 리스트
	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		return boardDAO.getBoardList(vo);
	}

	 //조회수 증가
	@Override
	public void updateBoardViewCount(BoardVO vo) {
		boardDAO.updateBoardViewCount(vo);
	}

}
