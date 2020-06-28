<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>직원 목록</title>
</head>
<body>


<table>
<tr> <th> 직원 번호</th> <th> 직원 이름 </th> <th> 담당 업무 </th> </tr>

<c:forEach var="empList" items="${empList}">
<tr> 
<td> <c:out value="${empList.emp.eno}" /> </td>
<td> <c:out value="${empList.emp.name}" /> </td>
<td> <c:out value="${empList.emp.task}" /> </td>
</tr>
</c:forEach>


</table>
</body>