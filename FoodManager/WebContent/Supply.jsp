<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8" import="java.net.URLEncoder"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>납품 목록</title>
</head>
<body>

<h1> 납품 관리 시스템</h1>

<table>
<tr> <th> 납품 번호</th> <th> 납품 일자 </th> <th> 수량 </th> <th> 재료 이름</th> <th> 납품업자 </th> <th> 유통기한 </th> </tr>

<c:forEach var="supplyList" items="${supplyList}">

<c:url value="/SupplyController" var="sup_url">
  <c:param name="form" value="delete" />
  <c:param name="sno" value="${supplyList.supply.sno}"/>
</c:url>

<tr> 
<td> <c:out value="${supplyList.supply.sno}"/>  </td>
<td> <c:out value="${supplyList.supply.sdate}" /> </td>
<td> <c:out value="${supplyList.supply.amount}" /> </td>
<td> <c:out value="${supplyList.supply.gname}"/> </td>
<td> <c:out value="${supplyList.supply.sname}" /> </td>
<td> <c:out value="${supplyList.supply.edate}" /> </td>


<td>

<a href="Supply_modify.html"> 수정 </a> <a href="${sup_url}" > 삭제 </a>
</tr>
</c:forEach>


</table>

<a href="Supply_modify.html"> 추가 </a>         
<br><br>



</body>