

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
public class NutritionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;
	private Nutrition nutrition;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NutritionController() {
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

		String gredientName = request.getParameter("gredientName");
		System.out.println(gredientName);
		
		String kcal = request.getParameter("kcal");
		String prt = request.getParameter("prt");
		String vit = request.getParameter("vit");
		String sal = request.getParameter("salt");
		String ETC =request.getParameter("ETC");
		
		if(kcal != null || prt != null || vit != null || sal != null || ETC != null)
		{
			System.out.println("2번째");
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("merge into nutrition n using dual on(n.gredient_name=?) "
						+ "when matched then update set n.calorie=?, n.protein=?, n.vitamin=?, n.salt=?, n.ETC=? "
						+ "when not matched then insert (n.gredient_name, n.calorie, n.protein, n.vitamin, n.salt, n.ETC) "
						+ "values (?, ?, ?, ?, ?, ?)");
				ps.setString(1, gredientName);
				ps.setInt(2, Integer.parseInt(kcal));
				ps.setInt(3,  Integer.parseInt(prt));
				ps.setInt(4,  Integer.parseInt(vit));
				ps.setInt(5, Integer.parseInt(sal));
				ps.setString(6, ETC);
				ps.setString(7, gredientName);
				ps.setInt(8, Integer.parseInt(kcal));
				ps.setInt(9,  Integer.parseInt(prt));
				ps.setInt(10,  Integer.parseInt(vit));
				ps.setInt(11, Integer.parseInt(sal));
				ps.setString(12, ETC);
				
				ps.executeUpdate();
				ps.close();
				conn.commit();
				System.out.println("3번째");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("실패");
				return ;
			}

		}
		
		
		if(gredientName == null)
			return;	
		try {
			PreparedStatement ps = conn.prepareStatement("select * from nutrition where gredient_name=?");
			ps.setString(1, gredientName);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				String gredient_name = rs.getString("gredient_name");
				int calorie = rs.getInt("calorie");
				int protein = rs.getInt("protein");
				int vitamin = rs.getInt("vitamin");
				int salt = rs.getInt("salt");
				String etc = rs.getString("ETC");
				
				nutrition = new Nutrition(gredient_name, calorie, protein, vitamin, salt, etc);
				
				request.setAttribute("nutrition", nutrition);
				
				dispatcher = request.getRequestDispatcher("nutrition.jsp");
				dispatcher.forward(request, response);
			}
			else
				response.sendRedirect("nutrition_modify.html");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//sql 구문이 틀렸거나 값이 반환되지 않은 경우
			//값이 반환되지 않은 경우, 등록창으로 재설정

			response.sendRedirect("nutrition_modify.html");
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
