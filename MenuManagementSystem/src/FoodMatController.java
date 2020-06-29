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
 * Servlet implementation class FoodMatController
 */
@WebServlet("/FoodMatController")
public class FoodMatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FoodMatController() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String gredientName = request.getParameter("gredientName");
		String amountString = request.getParameter("amount");
		String opcode = request.getParameter("opcode");
		
		if(gredientName != null || opcode != null) {
			if ("modify".equals(opcode)) {
				response.sendRedirect("gredient_modify.html");
				return;
			}
			else if("deleteGredient".equals(opcode))
				delGredient(request, response);
			else if("modifyGredient".equals(opcode)) 
				gredientModify(gredientName, Integer.parseInt(amountString));
			else if("delNutrition".equals(opcode))
				delNutrition(request, response);	
		}

		try {
			PreparedStatement st = conn.prepareStatement("select * from food_marterials");
			ResultSet rs = st.executeQuery();

			ArrayList<FoodMaterial> foodMList = new ArrayList<FoodMaterial>();

			while (rs.next()) {
				String gradientName = rs.getString("gredient_name");
				int amount = rs.getInt("amount");

				FoodMaterial foodM = new FoodMaterial(gradientName, amount);

				foodMList.add(foodM);
			}
			
			rs.close();
			st.close();
			
			request.setAttribute("foodMaterial", foodMList);
			dispatcher = request.getRequestDispatcher("food_material.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void delNutrition(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			PreparedStatement st = conn.prepareStatement("delete from nutrition where gredient_name=?");
			String gredientName = request.getParameter("gredientName");
			st.setString(1, gredientName);
			st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void delGredient(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			PreparedStatement st = conn.prepareStatement("delete from food_marterials where gredient_name=?");
			String gredientName = request.getParameter("gredientName");
			st.setString(1, gredientName);
			st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void gredientModify(String name, int amount)
	{
		try {
			PreparedStatement st = conn.prepareStatement(
					"merge into food_marterials f using dual on(f.gredient_name=?) "
					+ "when matched then update set f.amount=? "
					+ "when not matched then insert (f.gredient_name, f.amount) values (?, ?)");
			st.setString(1, name);
			st.setInt(2, amount);
			st.setString(3,  name);
			st.setInt(4, amount);
			st.executeUpdate();
			st.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
