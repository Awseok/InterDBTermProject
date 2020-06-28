import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class FoodMatController
 */
@WebServlet("/SupplierController")
public class SupplierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private DataSource ds;
	private Connection conn;
	private ServletContext context; 
	private RequestDispatcher dispatcher;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierController() {
        super();
       
        // TODO Auto-generated constructor stub
    }

    private void initS()
    {
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/xe");
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		initS();
		try {
			PreparedStatement st = conn.prepareStatement("select * from Supplier");
			ResultSet rs = st.executeQuery();
			

			ArrayList<Supplier> supList = new ArrayList<Supplier>();	
			
			while(rs.next())
			{
				int bno = rs.getInt("BUSINESS_LICENSE_NUMBER");
				String name = rs.getString("SUPPLIER_NAME");
				String cno = rs.getString("CONTACT_NUMBER");
				
				Supplier Sup = new Supplier(name,cno,bno);
				
				supList.add(Sup);
			}
			
			int supListCnt = supList.size();
			request.setAttribute("supList", supList);
			dispatcher = request.getRequestDispatcher("Supplier.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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