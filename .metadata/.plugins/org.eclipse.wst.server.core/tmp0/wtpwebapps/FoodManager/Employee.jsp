<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ���</title>
</head>
<body>


<table>
<tr> <th> ���� ��ȣ</th> <th> ���� �̸� </th> <th> ��� ���� </th> </tr>

<c:forEach var="empList" items="${empList}">
<tr> 
<td> <c:out value="${empList.emp.eno}" /> </td>
<td> <c:out value="${empList.emp.name}" /> </td>
<td> <c:out value="${empList.emp.task}" /> </td>
</tr>
</c:forEach>


</table>
</body>