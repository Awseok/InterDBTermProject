<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>납품 업체 목록</title>
</head>
<body>

<h1> 납품업자 관리 시스템</h1>

<table>
<tr> <th> 납품업체 이름</th> <th> 전화 번호 </th> <th> 사업자 번호 </th> </tr>

<c:forEach var="supList" items="${supList}">

<c:url value="/SupplierController" var="del_url">
  <c:param name="form" value="delete" />
  <c:param name="name" value="${supList.sup.name}"/>
</c:url>


<tr> 
<td> <c:out value="${supList.sup.name}"/>  </td>
<td> <c:out value="${supList.sup.cno}" /> </td>
<td> <c:out value="${supList.sup.bno}" /> </td>

<td>

<c:url value="/SupplierController" var="sup_url">
  <c:param name="name" value="${supList.sup.name}" />
</c:url>

<a href="${sup_url}" > 수정 </a>   <a href="${del_url}" > 삭제 </a>
</tr>
</c:forEach>


</table>

<a href="Supplier_modify.html"> 추가 </a>


</body>