<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>



<%
String sname = (String)request.getAttribute("sname");
Integer bnum = (Integer)request.getAttribute("bnum");
String cnum = (String)request.getAttribute("cnum");
%>
<li> sname : <%=sname %>
<li> bnum : ${bnum}
<li> cnum : <%=cnum %>

</body>
</html>