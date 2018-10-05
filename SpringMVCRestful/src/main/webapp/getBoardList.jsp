<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title><spring:message code="message.board.list.mainTitle"/></title>
</head>
<body>
  <h1><spring:message code="message.board.list.mainTitle"/></h1>
  <h3>
  	 <c:if test="${not empty user}">
  	    ${user.name}
  	 </c:if>
  	 <c:if test="${empty user}">
  	   guest   
  	 </c:if>
  	 <spring:message  code="message.board.list.welcomeMsg"/>
  	 <c:if test="${not empty user}">
  	 	<a href="logout.do">Log-out</a>
  	 </c:if>
  	 <c:if test="${empty user}">
  	 	<a href="login.jsp">Log-In</a>
  	 </c:if>
  </h3>
  <!-- 검색 시작 -->
  <form action="getBoardList.do" method="post">
    <table border="1" cellpadding="0" cellspacing="0" width="700">
    <tr>
    	<td align="right">
    	<select name="searchCondition">
    	  <c:forEach items="${conditionMap}" var="option">
    	     <option value="${option.value}">${option.key}
    	  </c:forEach>
      	</select>
    	<input type="text" name="searchKeyword">
    	<input type="submit" value="<spring:message code="message.board.list.search.condition.btn"/>">
    	</td>
    </tr>
    </table>
  </form>
  <!-- 검색 종료 -->
  <table board="1" cellpadding="0" cellspacing="0" width="700">
  <tr>
  	<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.seq"/></th>
  	<th bgcolor="orange" width="200"><spring:message code="message.board.list.table.head.title"/></th>
  	<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.writer"/></th>
  	<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.regDate"/></th>
  	<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.cnt"/></th>
  </tr>
  <c:forEach items="${boardList}" var="board">
  	<tr>
    <td>${board.seq }</td>
    <td align="left"><a href="getBoard.do?seq=${board.seq}">${board.title }</td>
    <td>${board.writer}</td>
    <td>${board.regdate}</td>
    <td>${board.cnt}</td>
    </tr>
  </c:forEach>
  </table>
   <br>
   <a href="insertBoard.jsp"><spring:message code="message.board.list.link.insertBoard"/></a>
</body>
</html>