<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title>글 상세</title>
</head>
<body>
 <center>
   <h1>글 상세</h1>
   <a href="logout.do">Log-out</a>
  <hr>
  <form action="updateBoard.do" method="post" enctype="multipart/form-data">
  <input type="hidden" name="seq" value="${board.seq}">
  <table border=1 cellpadding=0 cellspacing=0>
  	<tr>
  	 <td bgcolor="orange" width="70"><spring:message code="message.board.list.table.head.title"/></td>
  	 <td align="left"><input type="text" name="title" value="${board.title}"></td>
  	</tr>
   <tr>
  	 <td bgcolor="orange" width="70"><spring:message code="message.board.list.table.head.writer"/></td>
  	 <td align="left"><input type="text" name="writer" value="${board.writer}"></td>
  	</tr>
  	<tr>
  	 <td bgcolor="orange" width="70"><spring:message code="message.board.list.search.condition.content"/></td>
  	 <td align="left"><textarea type="text" name="content"  cols="40" rows="10">${board.content}</textarea></td>
  	</tr>
    <tr>
  	 <td bgcolor="orange" width="70">등록일</td>
  	 <td align="left"><input type="text"  value="${board.regdate}"></td>
  	</tr>
  	<tr>
  	 <td bgcolor="orange" width="70">조회수</td>
  	 <td align="left"><input type="text" name="cnt" value="${board.cnt}"></td>
  	</tr>
  	<tr>
          <td bgcolor="orange" width="70">업로드</td>
          <td align="left">
          <img src="images/${board.filename}" width="200" height="200"/><br>
          <input type="file" name="uploadFile">
          </td>
        </tr>
   <tr>
     <td colspan=2 align="center">
       <input type="submit" value="글 수정">
       <input type="reset" value="다시수정">
     </td>
   </tr>
  </table>
  </form>
  <hr>
  <a href="insertBoard.jsp">글 등록</a>
  <a href="deleteBoard.do?seq=${board.seq}">글 삭제</a>
  <a href="getBoardList.do">글 목록</a>
 </center>
</body>
</html>