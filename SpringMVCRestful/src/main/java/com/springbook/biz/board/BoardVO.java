package com.springbook.biz.board;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="BOARD")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardVO {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="my_seq")
	@SequenceGenerator(name="my_seq",sequenceName="board_seq1")
	@XmlAttribute
	private int seq;
	@Column(name="title")
	private String title;
	@Column(name="writer")
	private String writer;
	@Column(name="content")
	private String content;
	@Temporal(TemporalType.DATE)
	private Date regdate = new Date();
	private int cnt;
	@Transient
	private String searchCondition;
	@Transient
	private String searchKeyword;
	@Transient
	@XmlTransient
	private MultipartFile uploadFile;
	
	private String filename;
	

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@JsonIgnore
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	@JsonIgnore
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	@JsonIgnore
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public int getSeq() {	return seq;	}
	public void setSeq(int seq) {	this.seq = seq;	}
	public String getTitle() {		return title;	}
	public void setTitle(String title) {		this.title = title;	}
	public String getWriter() {		return writer;	}
	public void setWriter(String writer) { this.writer = writer;	}
	public String getContent() {		return content;	}
	public void setContent(String content) {	this.content = content;	}
	public Date getRegdate() {		return regdate;	}
	public void setRegdate(Date regdate) {		this.regdate = regdate;	}
	public int getCnt() {		return cnt;	}
	public void setCnt(int cnt) {		this.cnt = cnt;	}
	
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title 
				   + ", writer=" + writer + ", content=" + content 
				   + ", regdate="
				   + regdate + ", cnt=" + cnt + "]";
	}
	
	
	
}
