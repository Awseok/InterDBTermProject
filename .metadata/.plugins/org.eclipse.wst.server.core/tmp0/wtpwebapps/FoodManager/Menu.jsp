<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8" import="java.net.URLEncoder"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>식단 </title>
</head>
<body>

<h1> 메뉴 관리 시스템</h1>

<table>
<tr> <th> 식단 번호</th> <th>조리 번호 </th>  <th> 일시 </th><th> 배식 </th>  </tr>

<c:forEach var="menuList" items="${menuList}">

<c:url value="/MenuController" var="menu_url">
  <c:param name="mno" value="${insList.menu.mno}" />
</c:url>

<c:url value="/MenuController" var="del_url">
  <c:param name="form" value="delete" />
  <c:param name="mno" value="${menuList.menu.mno}"/>
</c:url>


<tr> 
<td> <c:out value="${menuList.menu.mno}"/>  </td>
<td> <c:out value="${menuList.menu.cno }"/>
<td> <c:out value="${menuList.menu.date}"/>  </td>
<td> <c:out value="${menuList.menu.dist}" /> </td>


<td>

<a href="${menu_url}" > 수정 </a> <a href="${del_url}" > 삭제 </a>
</tr>
</c:forEach>


</table>

<a href="Menu_modify.html"> 추가 </a>
<br><br>


</body>