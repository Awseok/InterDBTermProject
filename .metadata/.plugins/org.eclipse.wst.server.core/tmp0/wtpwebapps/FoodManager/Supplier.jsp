<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ���� ���</title>
</head>
<body>


<table>
<tr> <th> �̸� </th> <th> ��ȭ��ȣ </th> <th> ����� ��ȣ </th> </tr>

<c:forEach var="supList" items="${supList}">
<tr> 
<td> <c:out value="${supList.sup.name}" /> </td>
<td> <c:out value="${supList.sup.cno}" /> </td>
<td> <c:out value="${supList.sup.bno}" /> </td>
</tr>
</c:forEach>


</table>
</body>