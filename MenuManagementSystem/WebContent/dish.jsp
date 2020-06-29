<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요리</title>
</head>
<body>
<h1> 요리 </h1>

<table>
<tr> <th> 요리명 </th> <th> 재료명 </th> <th> 수량 </th> </tr>

<c:forEach var="dishList" items="${dishList}">
<tr> 
<td> <c:out value="${dishList.dishName}"/> </td>
<c:forEach var="recipeArray" items="${dishList.recipeArray}">
<td> <c:out value="${recipeArray.gredientName}" /> </td>
<td> <c:out value="${recipeArray.amount}" /> </td>
</c:forEach>
<c:url value="/DishController" var="delDish_url">
  <c:param name="dishName" value="${dishList.dishName}" />
  <c:param name="opcode" value="delDish" />
</c:url>
<td> <a href="${delDish_url}"> 요리 삭제 </a> </td>
</tr>
</c:forEach>
<tr> <td> <a href="dish_modify.html" > 요리 등록 및 수정 </a> </td> </tr>
<tr> <td> <a href="DishController?opcode=GetRecipe"> 레시피 조회</a>
</table>

</body>
</html>