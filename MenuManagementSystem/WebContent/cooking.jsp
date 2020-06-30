<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조리</title>
</head>
<body>
<h1> 조리 </h1>
<table>
<tr> <th> key </th> <th> 요리명 </th> <th> 조리 직원 번호 </th> <th> 일시 </th> </tr>
<c:forEach var="cookingList" items="${cookingArray}">
<tr> <td> ${cookingList.cookingKey} </td> <td> ${coolkingList.dish.name} </td> <td> ${cookingList.employeeNumber} </td> <td> ${cookingList.cookingDate} </td> </tr>
</c:forEach> 
</table>
<a href="cooking_modify.html"> 조리 등록 및 수정 </a>
</body>
</html>