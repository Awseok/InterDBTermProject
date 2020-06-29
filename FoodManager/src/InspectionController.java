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


@WebServlet("/InspectionController")
public class InspectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InspectionController() {
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
		String inoString=request.getParameter("ino");
		String enoString=request.getParameter("eno");
		String edate = request.getParameter("date");
		String eresult = request.getParameter("result");
		
		if(inoString != null)
		{
			if(enoString == null && eresult ==null) {
			response.sendRedirect("Inspection_modify.html");
			return;
		}
			InspectionModify(Integer.parseInt(inoString),Integer.parseInt(enoString),edate,eresult);
	}
		try {
			PreparedStatement st = conn.prepareStatement("select * from Inspection_result");
			ResultSet rs = st.executeQuery();
			
			ArrayList<Inspection> insList = new ArrayList<Inspection>();	
			
			while(rs.next())
			{
				int ino = rs.getInt("INSPECTION_KEY");
				int eno = rs.getInt("EMPLOYEE_NUMBER");
				String date = rs.getString("INSPECTION_DATE");
				String result = rs.getString("RESULT");
				
				Inspection Ins = new Inspection(ino,eno,date,result);
				
				insList.add(Ins);
			}
			
			request.setAttribute("insList", insList);
			dispatcher = request.getRequestDispatcher("Inspection.jsp");
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
	
	private void InspectionModify(int ino,int eno,String date,String result)
	{
		try {
			PreparedStatement st = conn.prepareStatement(
				"merge into Inspection_result i using dual on(i.INSPECTION_KEY=?)"
						+"when matched then update set i.employee_number=?,i.inspection_date=?,i.result=?"
						+"when not matched then insert (i.inspection_key,i.employee_number,i.inspection_date,i.result) values (?,?,?,?)");
			st.setInt(1, ino);
			st.setInt(2, eno);
			st.setString(3,date);
			st.setString(4, result);
			st.setInt(5, ino);
			st.setInt(6, eno);
			st.setString(7, date);
			st.setString(8, result);
			st.executeUpdate();
			st.close();
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}