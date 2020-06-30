import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;




/**
 * Servlet implementation class Employee
 */

@WebServlet("/Employee/*")

public class Employee extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private int eno;
	private String name;
	private String task;
	private Employee emp;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Employee(int eno, String name, String task) {
        emp = new Employee();
        emp.setName(name);
        emp.setEno(eno);
        emp.setTask(task);
        // TODO Auto-generated constructor stub
    }
    
    public void setName(String name)
    {
    	this.name=name;
    	
    }
    
    public String getName()
    {
    	return name;    	
    }
    
    

    public void setEno(int eno)
    {
    	this.eno=eno;
    	
    }
    
    public int getEno()
    {
    	return eno;    	
    }
    

    public void setTask(String task)
    {
    	this.task=task;
    	
    }
    public String getTask()
    {
    	return task;    	
    }
    
    public void setEmp(Employee emp)
    {
    	this.emp=emp;
    }
    public Employee getEmp()
    {
    	return emp;
    }
}
    
    
 