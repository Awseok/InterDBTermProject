import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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


@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuController() {
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public void destroy() {
    	try {
    		conn.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String mnoString=request.getParameter("mno");
		String cnoString=request.getParameter("cno");
		String date = request.getParameter("date");
		String dist = request.getParameter("dist");
		String form= request.getParameter("form");
		
		if (form != null && mnoString != null)
		{
			System.out.println("테스트25");
			deleteMember(Integer.parseInt(mnoString));
		}
		if(mnoString != null && form == null)
		{		
			System.out.println("테스트26");
			if(cnoString == null && date ==null && dist == null) {
			response.sendRedirect("Menu_modify.html");
			return;
		}
			System.out.println("테스트");
			MenuModify(Integer.parseInt(mnoString),Integer.parseInt(cnoString),date,dist);
	}
		try {
			PreparedStatement st = conn.prepareStatement("select * from Menu");
			ResultSet rs = st.executeQuery();
			System.out.println("테스트1");
			ArrayList<Menu> menuList = new ArrayList<Menu>();	
			
			while(rs.next())
			{
				System.out.println("테스트2");
				int mno = rs.getInt("MENU_KEY");
				int cno = rs.getInt("COOKING_KEY");
				String mdate = rs.getString("MENU_DATE");
				String mdist = rs.getString("DISTRIBUTION");
				
				
				Menu menu = new Menu(mno,cno,mdate,mdist);
				
				menuList.add(menu);
			}
			
			request.setAttribute("menuList", menuList);
			dispatcher = request.getRequestDispatcher("Menu.jsp");
			dispatcher.forward(request, response);
			
			rs.close();
			st.close();
			
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
	
	private void MenuModify(int mno,int cno,String date,String dist)
	{
		System.out.println("테스트3");
		try {
			PreparedStatement st = conn.prepareStatement(
				"merge into menu m using dual on(m.MENU_KEY=?) "
						+"when matched then update set m.COOKING_KEY=?, m.MENU_DATE=?,m.DISTRIBUTION=? "
						+"when not matched then insert (m.MENU_KEY,m.COOKING_KEY,m.MENU_DATE,m.DISTRIBUTION) values (?,?,?,?)");
			System.out.println("테스트4");
			st.setInt(1, mno);
			System.out.println("테스트5");
			st.setInt(2, cno);
			System.out.println("테스트6");
			st.setString(3, date);
			System.out.println("테스트7");
			st.setString(4,dist);
			System.out.println("테스트8");
			st.setInt(5, mno);
			System.out.println("테스트");
			st.setInt(6, cno);
			System.out.println("테스트2");
			st.setString(7,date);
			System.out.println("테스트3");
			st.setString(8, dist);
			System.out.println("테스트4");
			st.executeUpdate();
			System.out.println("테스트5");
			st.close();
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteMember(int mno) 
    { 
        try {  
        	System.out.println("테스트");
            PreparedStatement pstmt = conn.prepareStatement("select * from menu where menu_key=?");
            System.out.println("테스트2");
            pstmt.setInt(1, mno);
            System.out.println("테스트3");
            ResultSet rs = pstmt.executeQuery();
            System.out.println("테스트4");
            while (rs.next()) 
            {                        
            	System.out.println("테스트5");
                    pstmt = conn.prepareStatement("delete from menu where menu_key=?");
                    System.out.println("테스트6");
                    pstmt.setInt(1, mno);
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