<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8" import="java.net.URLEncoder"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>검사 결과</title>
</head>
<body>

<h1> 위생 관리 시스템</h1>

<table>
<tr> <th> 검사 번호</th> <th>직원 이름 </th>  <th> 직원 번호 </th><th> 검사 일시 </th> <th> 검사 결과 </th> </tr>

<c:forEach var="insList" items="${insList}">

<c:url value="/InspectionController" var="ins_url">
  <c:param name="ino" value="${insList.ins.ino}" />
</c:url>

<c:url value="/InspectionController" var="del_url">
  <c:param name="form" value="delete" />
  <c:param name="ino" value="${insList.ins.ino}"/>
</c:url>


<tr> 
<td> <c:out value="${insList.ins.ino}"/>  </td>
<td> <c:out value="${insList.ins.name }"/>
<td> <c:out value="${insList.ins.eno}"/>  </td>
<td> <c:out value="${insList.ins.date}" /> </td>
<td> <c:out value="${insList.ins.result}" /> </td>

<td>

<a href="${ins_url}" > 수정 </a> <a href="${del_url}" > 삭제 </a>
</tr>
</c:forEach>


</table>

<a href="Inspection_modify.html"> 추가 </a>
<br><br>


</body>