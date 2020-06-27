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
 * Servlet implementation class Supplier
 */
@WebServlet("/Supplier")
public class Supplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sname; // 사업자 이름
	private String cnum;  //  전화번호
	private Integer bnum; // 사업자번호
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Supplier() {
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
			PreparedStatement st = conn.prepareStatement("select * from SUPPLIER");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
			sname=rs.getString("SUPPLIER_NAME");
			cnum=rs.getString("Contact_Number");	
			bnum=rs.getInt("BUSINESS_LICENSE_NUMBER");
			}
			
			request.setAttribute("bnum", bnum);
			request.setAttribute("sname", sname);
			request.setAttribute("cnum", cnum);
			
	
			
			RequestDispatcher dispatcher= request.getRequestDispatcher("/Supplier.jsp");
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
