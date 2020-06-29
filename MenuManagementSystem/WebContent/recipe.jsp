<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>������ ����</title>
</head>
<body>
<h1> ������ ���� </h1>

<table>
<tr> <th> �丮�� </th> <th> ������ ��ȣ </th> <th> ���� </th> <th> ���� </th> </tr>

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
<td> <a href="${delReci_url}" > ���� </a> </td>
</tr>
</c:forEach>
<tr> <td> <a href="recipe_modify.html"> ������ ��� ���� </a> </td> </tr>

</table>
</body>
</html>