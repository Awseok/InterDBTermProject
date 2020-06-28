<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>납품업자 목록</title>
</head>
<body>


<table>
<tr> <th> 이름 </th> <th> 전화번호 </th> <th> 사업자 번호 </th> </tr>

<c:forEach var="supList" items="${supList}">
<tr> 
<td> <c:out value="${supList.sup.name}" /> </td>
<td> <c:out value="${supList.sup.cno}" /> </td>
<td> <c:out value="${supList.sup.bno}" /> </td>
</tr>
</c:forEach>


</table>
</body>