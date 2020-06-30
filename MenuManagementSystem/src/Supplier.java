import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;




/**
 * Servlet implementation class Employee
 */

@WebServlet("/Supplier/*")

public class Supplier extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private int bno;
	private String name;
	private String cno;
	private Supplier sup;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Supplier() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Supplier(String name,String cno, int bno) {
        sup = new Supplier();
        sup.setName(name);
        sup.setCno(cno);
        sup.setBno(bno);
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
    
    

    public void setBno(int bno)
    {
    	this.bno=bno;
    	
    }
    
    public int getBno()
    {
    	return bno;    	
    }
    

    public void setCno(String cno)
    {
    	this.cno=cno;
    	
    }
    public String getCno()
    {
    	return cno;    	
    }
    
    public void setSup(Supplier sup)
    {
    	this.sup=sup;
    }
    public Supplier getSup()
    {
    	return sup;
    }
}
    
    