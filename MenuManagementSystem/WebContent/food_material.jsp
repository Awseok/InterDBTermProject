<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
<tr> <th> 재료명 </th> <th> 수량 </th> </tr>

<c:forEach var="foodList" items="${foodMaterial}">
<tr> 
<td> <c:out value="${foodList.nut.name}" /> </td>
<td> <c:out value="${foodList.amount}" /> </td>
</tr>
</c:forEach>


</table>
</body>
</html>