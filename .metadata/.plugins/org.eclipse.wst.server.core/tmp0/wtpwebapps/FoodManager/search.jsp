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
Integer e_no =Integer.parseInt(request.getParameter("e_no"));
String query = "select * from Employee ";
String result_query = query + "where EMPLOYEE_NAME =" + "'" + e_name + "'" + "and EMPLOYEE_NUMBER =" + "'" + e_no +"'" ;

Class.forName("oracle.jdbc.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512109", "201512109");
PreparedStatement st = conn.prepareStatement(result_query);
ResultSet rs = st.executeQuery();

while (rs.next()) {
Integer eno=rs.getInt("EMPLOYEE_NUMBER");
String name=rs.getString("EMPLOYEE_NAME");
String task=rs.getString("TASK");
%>

������ȣ : <%=eno %>
�̸�: <%=name %>
����: <%=task %><%
}

rs.close();
st.close();
conn.close();
%>


</body>
</html>