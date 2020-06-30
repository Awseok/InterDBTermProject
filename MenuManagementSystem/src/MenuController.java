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
 * Servlet implementation class MenuController
 */
@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;
	private Dish dish;
	private Cooking cooking;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuController() {
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
		
		String opcode = request.getParameter("opcode");
		if(opcode == null) return;
		if("getCooking".contentEquals(opcode))
			getCooking(request, response);
		else if("modifyCooking".contentEquals(opcode))
			modifyCooking(request, response);
		else if("deleteCooking".contentEquals(opcode))
			deleteCooking(request, response);
		getCooking(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void deleteCooking(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			PreparedStatement st = conn.prepareStatement("delete from cooking where cooking_key=?");
			String cookingKey = request.getParameter("cookingKey");
			st.setString(1, cookingKey);
			st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void modifyCooking(HttpServletRequest request, HttpServletResponse response)
	{
		String cookingKey = request.getParameter("cookingKey");
		String dishName = request.getParameter("dishName");
		String employeeNumber = request.getParameter("employeeNumber");
		String cookingDate = request.getParameter("cookingDate");
		try {
			PreparedStatement st = conn.prepareStatement(
					"merge into cooking c using dual on(c.cooking_key=?) "
					+ "when matched then update set c.dish_name=?,c.employee_number=?,c.cooking_date=? "
					+ "when not matched then insert (c.cooking_key, c.dish_name,c.employee_number,c.cooking_date) values (?, ?, ? ,?)");
			st.setInt(1, Integer.parseInt(cookingKey));
			st.setString(2, dishName);
			st.setInt(3,  Integer.parseInt(employeeNumber));
			st.setString(4, cookingDate);
			st.executeUpdate();
			st.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getCooking(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			PreparedStatement pst = conn.prepareStatement("select * from cooking");
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Cooking> cookingArray = new ArrayList<Cooking>();
			while(rs.next())
			{
				int cookingKey = rs.getInt("cooking_key");
				String dishName = rs.getString("dish_name");
				int employeeNumber = rs.getInt("employee_number");
				String cookingDate = rs.getString("cooking_date");
				
				dish.setDishName(dishName);
				
				cooking = new Cooking(cookingKey, employeeNumber, cookingDate);
				
				cookingArray.add(cooking);
			}	
			
			pst.close();
			
			request.setAttribute("cookingArray", cookingArray);
			dispatcher = request.getRequestDispatcher("cooking.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
