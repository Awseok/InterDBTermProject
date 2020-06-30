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


@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context ctx;
	private Connection conn;
	private RequestDispatcher dispatcher;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
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
		String enoString=request.getParameter("eno");
		String ename = request.getParameter("name");
		String eTask = request.getParameter("task");
		String form= request.getParameter("form");
		
		if (form != null && enoString != null)
		{
			
			System.out.println("Å×½ºÆ®2");
			deleteMember(Integer.parseInt(enoString));
		}
		
		if(enoString != null && form == null)
		{
			if(ename == null && eTask ==null) {
			response.sendRedirect("Employee_modify.html");
			return;
		}
			EmployeeModify(Integer.parseInt(enoString),ename,eTask);
	}
		try {
			PreparedStatement st = conn.prepareStatement("select * from Employee");
			ResultSet rs = st.executeQuery();
			
			ArrayList<Employee> empList = new ArrayList<Employee>();	
			
			while(rs.next())
			{
				int Eno = rs.getInt("EMPLOYEE_NUMBER");
				String Ename = rs.getString("EMPLOYEE_NAME");
				String task = rs.getString("TASK");
				
				Employee Emp = new Employee(Eno,Ename,task);
				
				empList.add(Emp);
			}
			
			request.setAttribute("empList", empList);
			dispatcher = request.getRequestDispatcher("Employee.jsp");
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
	
	private void EmployeeModify(int eno,String name,String task)
	{
		try {
			PreparedStatement st = conn.prepareStatement(
				"merge into Employee e using dual on(e.EMPLOYEE_NUMBER=?) "
						+ "when matched then update set e.employee_name=?,e.task=?"
						+ "when not matched then insert (e.employee_number,e.employee_name,e.task) values (?,?,?)");
			st.setInt(1, eno);
			st.setString(2, name);
			st.setString(3,task);
			st.setInt(4, eno);
			st.setString(5, name);
			st.setString(6, task);
			st.executeUpdate();
			st.close();
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMember(int eno) 
    { 
        try {         
            PreparedStatement pstmt = conn.prepareStatement("select * from employee where employee_number=?");
            pstmt.setInt(1, eno);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) 
            {                               
                    pstmt = conn.prepareStatement("delete from employee where employee_number=?");
                    pstmt.setInt(1, eno);
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
