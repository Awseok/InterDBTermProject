<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 정보</title>
</head>
<body>
<h1> 레시피 정보 </h1>

<table>
<tr> <th> 요리명 </th> <th> 레시피 번호 </th> <th> 재료명 </th> <th> 수량 </th> </tr>

<c:forEach var="dishList" items="${dishList}">
<tr> 
<td> <c:out value="${dishList.dishName}"/> </td>
<c:forEach var="recipeArray" items="${dishList.recipeArray}">
<td> <c:out value="${recipeArray.recipeNumber}" /> </td>
<td> <c:out value="${recipeArray.gredientName}" /> </td>
<td> <c:out value="${recipeArray.amount}" /> </td>

<c:url value="/DishController" var="delReci_url">
  <c:param name="recipeNumber" value="${recipeArray.recipeNumber}" />
  <c:param name="opcode" value="delRecipe" />
</c:url>
</c:forEach>
<td> <a href="${delReci_url}" > 삭제 </a> </td>
</tr>
</c:forEach>
<tr> <td> <a href="recipe_modify.html"> 레시피 등록 수정 </a> </td> </tr>

</table>
</body>
</html>