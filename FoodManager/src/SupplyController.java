import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class NutritionController
 */
@WebServlet("/NutritionController")
public class SupplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;
	private Supply Sup;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xe");
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");

		String gname = request.getParameter("gname");
		
		String snoString = request.getParameter("sno");
		int sno = Integer.parseInt(snoString);
		System.out.println(snoString);

		String sdate = request.getParameter("sdate");
		String amount = request.getParameter("amount");
		String sname = request.getParameter("sname");
		String edate =request.getParameter("edate");
		
		if(sdate != null || amount != null || sname != null || edate != null || gname != null)
		{
			System.out.println("2��°");
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("merge into Supply s using dual on(s.supply_key=?) "
						+ "when matched then update set s.supply_Date=?, s.amount=?, s.gredient_name=?, s.supplier_name=?, s.expiration_date=? "
						+ "when not matched then insert (s.supply_key,s.supply_date,s.amount,s.gredient_name,s.supplier_name,s.expiration_date) "
						+ "values (?, ?, ?, ?, ?, ?)");
				ps.setInt(1, sno);
				ps.setString(2, sdate);
				ps.setInt(3, Integer.parseInt(amount));
				ps.setString(4, gname);
				ps.setString(5, sname);
				ps.setString(6, edate);
				ps.setInt(7, sno);
				ps.setString(8, sdate);
				ps.setInt(9,  Integer.parseInt(amount));
				ps.setString(10, gname);
				ps.setString(11, sdate);
				ps.setString(12, edate);
				
				ps.executeUpdate();
				ps.close();
				conn.commit();
				System.out.println("3��°");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("����");
				return ;
			}

		}
		
		
		if(snoString == null)
			return;	
		try {
			PreparedStatement ps = conn.prepareStatement("select * from supply where supply_key=?");
			ps.setInt(1, sno);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				int Sno = rs.getInt("supply_key");
				String Sdate = rs.getString("Supply_Date");
				int Amount = rs.getInt("amount");
				String Gname = rs.getString("gredient_name");
				String Sname = rs.getString("SUPPLIER_NAME");
				String Edate = rs.getString("expiration_date");
				
				Sup = new Supply(Sno,Sdate,Amount,Gname,Sname,Edate);
				
				request.setAttribute("sup", Sup);
				
				dispatcher = request.getRequestDispatcher("Supply.jsp");
				dispatcher.forward(request, response);
			}
			else
				response.sendRedirect("Supply.modify.html");
			
		} catch (SQLException e) {
			
			response.sendRedirect("Supply.modify.html");
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