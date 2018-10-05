package com.springbook.biz.board;

import java.util.ArrayList;
import java.util.List;

public class ResBoard {
	int no;
	List<BoardVO> boards=new ArrayList<>();
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public List<BoardVO> getBoards() {
		return boards;
	}
	public void setBoards(List<BoardVO> boards) {
		this.boards = boards;
	}

}
