import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
@WebServlet("/SupplyController")
public class SupplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;
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

		String sdate = request.getParameter("sdate");
		String amount = request.getParameter("amount");
		String sname = request.getParameter("sname");
		String edate =request.getParameter("edate");
		String form= request.getParameter("form");
		
		if (snoString != null && form != null)
		{
			System.out.println("Å×½ºÆ®1");
			deleteMember(Integer.parseInt(snoString));
		}
		
		if(sdate != null || amount != null || sname != null || edate != null || gname != null)
		{
			try {
				PreparedStatement ps = conn.prepareStatement(
						"merge into Supply s using dual on(s.supply_key=?) "
						+ "when matched then update set s.supply_date=?,s.amount=?,s.gredient_name=?,s.supplier_name=?,s.expiration_date=? "
						+ "when not matched then insert (s.supply_key,s.supply_date,s.amount,s.gredient_name,s.supplier_name,s.expiration_date) values (?,?,?,?,?,?)");
				ps.setInt(1,Integer.parseInt(snoString));
				ps.setString(2,sdate);
				ps.setInt(3, Integer.parseInt(amount));
				ps.setString(4,gname);
				ps.setString(5,sname);
				ps.setString(6,edate);
				ps.setInt(7,Integer.parseInt(snoString));
				ps.setString(8,sdate);
				ps.setInt(9,Integer.parseInt(amount));
				ps.setString(10,gname);
				ps.setString(11,sname);
				ps.setString(12,edate);
				System.out.println(sname);
				ps.executeUpdate();
				ps.close();
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ;
			}

		}
		
	
		
		
	
		try {
			PreparedStatement ps = conn.prepareStatement("select * from supply");
			ArrayList<Supply> supplyList = new ArrayList<Supply>();	

			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int Sno = rs.getInt("SUPPLY_KEY");
				String Sdate = rs.getString("SUPPLY_DATE");
				int Amount = rs.getInt("amount");
				String Gname = rs.getString("gredient_name");
				String Sname = rs.getString("supplier_name");
				String Edate = rs.getString("expiration_date");
				
				Supply supply = new Supply(Sno,Sdate,Amount,Gname,Sname,Edate);
				supplyList.add(supply);

				request.setAttribute("supplyList", supplyList);
			}
				dispatcher = request.getRequestDispatcher("Supply.jsp");
				dispatcher.forward(request, response);
				
				rs.close();
				ps.close();
			
			
			
			
		} catch (SQLException e) {
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
	
	public void deleteMember(int sno) 
    { 
        try {         
            PreparedStatement pstmt = conn.prepareStatement("select * from supply where supply_key=?");
            pstmt.setInt(1, sno);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) 
            {                               
                    pstmt = conn.prepareStatement("delete from supply where supply_key=?");
                    pstmt.setInt(1, sno);
                    ;
                    pstmt.executeUpdate();
                    conn.commit(); 
                                                 
            }            
 
        } catch (Exception sqle) {
            try {
                conn.rollback(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException(sqle.getMessage());
        } 
    } 
}
