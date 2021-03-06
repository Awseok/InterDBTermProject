<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8" import="java.net.URLEncoder"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>직원 목록</title>
</head>
<body>

<h1> 직원 관리 시스템</h1>

<table>
<tr> <th> 직원 번호</th> <th> 직원 이름 </th> <th> 담당 업무 </th> </tr>

<c:forEach var="empList" items="${empList}">




<tr> 
<td> <c:out value="${empList.emp.eno}"/>  </td>
<td> <c:out value="${empList.emp.name}" /> </td>
<td> <c:out value="${empList.emp.task}" /> </td>

<td>
<c:url value="/EmployeeController" var="del_url">
  <c:param name="form" value="delete" />
  <c:param name="eno" value="${empList.emp.eno}"/>
</c:url>
<c:url value="/EmployeeController" var="emp_url">
  <c:param name="eno" value="${empList.emp.name}" />
</c:url>

<a href="${emp_url}" > 수정 </a> <a href="${del_url}" > 삭제 </a>
</tr>
</c:forEach>


</table>

<a href="Employee_modify.html"> 추가 </a>
<br><br>

<a href="InspectionController"> 위생 검사 </a>

</body>