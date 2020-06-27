package pknu.it;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Employee
 */

@WebServlet("/Employee/*")

public class Employee extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Integer eno;
	private String name;
	private String task;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/*
	 * public Integer getEno() {return eno;} public String getName() {return name;}
	 * public String getTask() {return task;} public void setEno(Integer eno) {
	 * this.eno= eno; } public void setName(String name) { this.name=name; } public
	 * void settask(String task) { this.task=task; }
	 */
    
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512109", "201512109");
			PreparedStatement st = conn.prepareStatement("select * from Employee");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
			eno=rs.getInt("EMPLOYEE_NUMBER");
			name=rs.getString("EMPLOYEE_NAME");
			task=rs.getString("TASK");								
			}
			
			request.setAttribute("eno", eno);
			request.setAttribute("name", name);
			request.setAttribute("task", task);
			
	
			
			RequestDispatcher dispatcher= request.getRequestDispatcher("/Employee.jsp");
			dispatcher.forward(request,response); 
			 
			rs.close();
			st.close();
			conn.close();
			
			
			
	}catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		
		
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}