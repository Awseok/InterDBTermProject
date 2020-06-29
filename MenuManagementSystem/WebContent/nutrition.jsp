<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>영양 정보</title>
</head>
<body>
<h1> 영양 정보  </h1>
<table>
<tr> <th> 식재료명 </th> <th> 열량(kcal) </th> <th> 단백질 </th> <th> 비타민 </th> <th> 염분 </th> <th> 기타 </th> </tr>
<tr> <td> ${nutrition.name} </td> <td> ${nutrition.cal} </td> <td> ${nutrition.prt} </td> <td> ${nutrition.vit} </td> <td> ${nutrition.salt} </td> <td> ${nutrition.ETC} </td></tr>

<c:url value="/FoodMatController" var="delNut_url">
  <c:param name="gredientName" value="${nutrition.name}" />
  <c:param name="opcode" value="delNutrition" />
</c:url>
</table>
<a href="${delNut_url}"> 영양분 삭제 </a>
</body>
</html>