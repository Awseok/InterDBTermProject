<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.sql.*,javax.sql.*,javax.naming.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% 
request.setCharacterEncoding("UTF-8");

String e_name = request.getParameter("e_name");
Integer eno = Integer.parseInt(request.getParameter("e_no"));
String task = request.getParameter("task");

String query = "Insert into Employee values  ";
String result_query = query + "(" + eno + ","+ "'" + e_name + "'," + "'" + task +"')";


Class.forName("oracle.jdbc.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512109", "201512109");
Statement st = conn.prepareStatement(result_query);
ResultSet rs = st.executeQuery(result_query);
rs.close();
st.close();
conn.close();
%>

��Ͽ� �����߽��ϴ�!
</body>
</html>