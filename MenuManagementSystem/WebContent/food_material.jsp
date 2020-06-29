<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.URLEncoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식자재 시스템</title>
</head>
<body>
<h1> 식자재 시스템 </h1>
<table>
<tr> <th> 재료명 </th> <th> 수량 </th> </tr>

<c:forEach var="foodList" items="${foodMaterial}">



<c:url value="/NutritionController" var="nut_url">
  <c:param name="gredientName" value="${foodList.nut.name}" />
</c:url>

<tr> 
<td> <a href="${nut_url}"> <c:out value="${foodList.nut.name}"/>  </a></td>
<td> <c:out value="${foodList.amount}" /> </td>
<td>

<c:url value="/FoodMatController" var="food_url">
  <c:param name="gredientName" value="${foodList.nut.name}" />
  <c:param name="opcode" value="modify" />
</c:url>

<a href="${food_url}" > 수정 </a>
</td>
<td>
<c:url value="/FoodMatController" var="deleteGre">
	<c:param name="opcode" value="deleteGredient" />
	<c:param name="gredientName" value="${foodList.nut.name}" />
</c:url>
<a href="${deleteGre}"> 삭제 </a>
</td>
</tr>
</c:forEach>
</table>

<a href="gredient_modify.html"> 추가 </a>


</body>
</html>