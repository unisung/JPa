package com.springbook.biz.board;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="BOARD4")//Board클래스와 접속한 DB의 BOARD2테이블과 매칭
public class Board {//JPA가 해당 접속 session(user) 에서 BOARD테이블을 찾아서 매칭
	@Id//클래스가 @Entity로 설정되면 반드시 클래스내의 필드중 하나는 @Id 가 존재해야함.
	@GeneratedValue(strategy=GenerationType.SEQUENCE) //자동발번되는 값
	@Column(name="seqNo", unique=true, 
	                insertable=true, updatable=true )
	private int seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(TemporalType.DATE)//필드 중 타입이 Date인 필드의 속성 지정시 사용
	private Date regdate = new Date();
	private int cnt;
	@Transient
	private String searchCondition;
	@Transient
	private String searchKeyWord;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + "]";
	}

}
