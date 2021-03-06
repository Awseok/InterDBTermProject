import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 */
@WebServlet("/SupplierController")
public class SupplierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierController() {
        super();
       
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException
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
    
    public void destroy() {
    	try {
    		conn.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		
		String bnoString = request.getParameter("bno");
		String sname = request.getParameter("name");
		String cno = request.getParameter("cno");
		String form= request.getParameter("form");
		
		System.out.println(sname);

		if (form != null && sname!= null)
		{
			
			System.out.println("�׽�Ʈ2");
			deleteMember(sname);
		}
		if(sname != null && form == null )
		{
			if(cno == null && bnoString ==null) {
			response.sendRedirect("Supplier_modify.html");
			return;
		}
			SupplierModify(sname,cno,Integer.parseInt(bnoString));
	}
		try {
			PreparedStatement st = conn.prepareStatement("select * from Supplier");
			ResultSet rs = st.executeQuery();
			
			ArrayList<Supplier> supList = new ArrayList<Supplier>();	
			
			while(rs.next())
			{
				String name = rs.getString("SUPPLIER_NAME");
				String Cno = rs.getString("Contact_Number");
				int Bno = rs.getInt("BUSINESS_LICENSE_NUMBER");
				
				Supplier Sup = new Supplier(name,Cno,Bno);
				
				supList.add(Sup);
			}
			
			request.setAttribute("supList", supList);
			dispatcher = request.getRequestDispatcher("Supplier.jsp");
			dispatcher.forward(request, response);
			
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODOddl Auto-generated catch block
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

	
	
	private void SupplierModify(String name, String cno, int bno)
	{
		try {
			PreparedStatement st = conn.prepareStatement(
				"merge into Supplier s using dual on(s.Supplier_name=?) "
						+ "when matched then update set s.Contact_Number=?,s.BUSINESS_LICENSE_NUMBER=?"
						+ "when not matched then insert (s.Supplier_name,s.Contact_number,s.BUSINESS_LICENSE_NUMBER) values (?,?,?)");
			st.setString(1, name);
			st.setString(2, cno);
			st.setInt(3,bno);
			st.setString(4, name);
			st.setString(5, cno);
			st.setInt(6, bno);
			st.executeUpdate();
			st.close();
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteMember(String sname) 
    { 
        try {     
            PreparedStatement pstmt = conn.prepareStatement("select * from supplier where supplier_name=?");
            pstmt.setString(1, sname);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) 
            {                             
                    pstmt = conn.prepareStatement("delete from supplier where supplier_name=?");

                    pstmt.setString(1, sname);

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
