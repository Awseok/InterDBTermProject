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
 * Servlet implementation class DishController
 */
@WebServlet("/DishController")
public class DishController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;
	private Dish dish;
	private Recipe recipe;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishController() {
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
		if(opcode == null)
			return ;
		if("Search".contentEquals(opcode))
			getFoodList(request, response);
		else if("ModifyDish".contentEquals(opcode))
			modifyDish(request, response);
		else if("GetRecipe".contentEquals(opcode))
			getRecipe(request, response);
		else if("ModifyRecipe".contentEquals(opcode))
			modifyRecipe(request, response);
		else if("delRecipe".contentEquals(opcode))
			deleteRecipe(request, response);
		else if("delDish".contentEquals(opcode))
			delDish(request, response);
		
		getFoodList(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void delDish(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			PreparedStatement st = conn.prepareStatement("delete from dish where dish_name=?");
			String dishName = request.getParameter("dishName");
			st.setString(1, dishName);
			st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteRecipe(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			PreparedStatement st = conn.prepareStatement("delete from recipe where recipe_number=?");
			String recipeNumber = request.getParameter("recipeNumber");
			st.setString(1, recipeNumber);
			st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void modifyRecipe(HttpServletRequest request, HttpServletResponse response)
	{
		String recipeKey = request.getParameter("recipeKey");
		String dishName = request.getParameter("dishName");
		String gredientName = request.getParameter("gredientName");
		String amount = request.getParameter("amount");
		String recipeNumber = request.getParameter("recipeNumber");
		
		
		try {
			PreparedStatement pst = conn.prepareStatement(
					"merge into recipe r using dual on(r.recipe_key=?) "
					+ "when matched then update set r.dish_name=?, r.gredient_name=?, r.amount=?, r.recipe_number=? "
					+ "when not matched then insert (r.recipe_key, r.dish_name, r.gredient_name, r.amount, r.recipe_number) values (?, ?, ?, ?, ?)");
			pst.setString(1, recipeKey);
			pst.setString(2, dishName);
			pst.setString(3, gredientName);
			pst.setInt(4,  Integer.parseInt(amount));
			pst.setString(5,  recipeNumber);
			pst.setString(6, recipeKey);
			pst.setString(7, dishName);
			pst.setString(8, gredientName);
			pst.setInt(9,  Integer.parseInt(amount));
			pst.setString(10,  recipeNumber);
			
			pst.executeUpdate();
			pst.close();
			conn.commit();
			
			getRecipe(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getRecipe(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			PreparedStatement pst = conn.prepareStatement("select * from recipe");
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Dish> dishList = new ArrayList<Dish>();
			
			String oldDishName = null;
			dish = new Dish();
			while(rs.next())
			{
				String dishName = rs.getString("dish_name");
				
				if(!dishName.equals(oldDishName))
				{
					dishList.add(dish);
					dish = new Dish(dishName);
				}
			
				recipe = new Recipe();
				String recipeNumber = rs.getString("recipe_number");
				String gredientName = rs.getString("gredient_name");
				int amount = rs.getInt("amount");
				recipe.setRecipeNumber(recipeNumber);
				recipe.setGredientName(gredientName);
				recipe.setAmount(amount);
				dish.setRecipeArray(recipe);
				
				oldDishName = dishName;
			}
			dishList.add(dish);
			
			pst.close();
			
			request.setAttribute("dishList", dishList);
			dispatcher = request.getRequestDispatcher("recipe.jsp");
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

	private void modifyDish(HttpServletRequest request, HttpServletResponse response)
	{
		String dishName = request.getParameter("dishName");
		String recipeKey = request.getParameter("recipeKey");
		
		try {
			PreparedStatement pst = conn.prepareStatement(
					"merge into dish d using dual on(d.dish_name=?) "
					+ "when matched then update set d.recipe_key=? "
					+ "when not matched then insert (d.dish_name, d.recipe_key) values (?, ?)");
			pst.setString(1, dishName);
			pst.setInt(2,  Integer.parseInt(recipeKey));
			pst.setString(3, dishName);
			pst.setInt(4,  Integer.parseInt(recipeKey));
			
			pst.executeUpdate();
			pst.close();
			conn.commit();
			
			getFoodList(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				response.sendRedirect("dish_modify.html");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	private void getFoodList(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			
			PreparedStatement pst = conn.prepareStatement("select * from dish");
			ResultSet rs = pst.executeQuery();
			ArrayList<Dish> dishList = new ArrayList<Dish>();
			
			while(rs.next())
			{
				int recipeKey = rs.getInt("recipe_key");
				
				PreparedStatement pst2 = conn.prepareStatement("select * from recipe where recipe_number = ?");
				pst2.setInt(1, recipeKey);
				ResultSet rs2 = pst2.executeQuery();
				
				dish = new Dish(rs.getString("dish_name"));
				
				while(rs2.next())
				{
					
					String name = rs2.getString("dish_name");
					String gredientName = rs2.getString("gredient_name");
					int amount = rs2.getInt("amount");
					recipe = new Recipe(recipeKey, name, gredientName, amount);
					dish.setRecipeArray(recipe);
				}
				
				dishList.add(dish);
			}
			
			request.setAttribute("dishList", dishList);
			dispatcher = request.getRequestDispatcher("dish.jsp");
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
