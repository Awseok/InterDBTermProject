package pknu.it;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Inspection
 */
@WebServlet("/Inspection")
public class Inspection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer eno;
	private String date;
	private String result;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inspection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201512109", "201512109");
			PreparedStatement st = conn.prepareStatement("select * from INSPECTION_RESULT");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
			eno=rs.getInt("EMPLOYEE_NUMBER");
			date=rs.getString("INSPECTION_DATE");
			result=rs.getString("RESULT");								
			}
			
			request.setAttribute("eno", eno);
			request.setAttribute("date", date);
			request.setAttribute("result", result);
			
	
			
			RequestDispatcher dispatcher= request.getRequestDispatcher("/Inspection.jsp");
			dispatcher.forward(request,response); 
			 
			rs.close();
			st.close();
			conn.close();
			
			
			
	}catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
