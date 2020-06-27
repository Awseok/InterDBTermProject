<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.*"%>
<%    request.setCharacterEncoding("UTF-8"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="search.jsp" method="post" accept-charset="utf-8">
직원 이름:
<input type="text" name="e_name">
<br>

직원 번호:
<input type="text" name="e_no">

<br><br>
<input type="submit" value="검색"/>
</form>

</body>
</html>