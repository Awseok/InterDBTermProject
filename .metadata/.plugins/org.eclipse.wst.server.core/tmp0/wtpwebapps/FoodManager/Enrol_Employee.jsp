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
<form action="enrol.jsp" method="get" accept-charset="utf-8">
직원 이름:
<input type="text" name="e_name">
<br>

직원 번호:
<input type="text" name="e_no">

<br><br>

직원 담당 업무:
<input type="text" name="task">


<input type="submit" value="등록"/>
</form>

</body>
</html>