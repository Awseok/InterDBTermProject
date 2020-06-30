import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Inspection/*")


public class Inspection extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int ino;
	private int eno;
	private String name;
	private String date;
	private String result;
	private Inspection ins;
	
	
	public Inspection(){
		super();
	};
	
	public Inspection(int ino,String name,int eno,String date,String result)
	{
		ins = new Inspection();
		ins.setIno(ino);
		ins.setEno(eno);
		ins.setDate(date);
		ins.setResult(result);
		ins.setName(name);
	}
	
	public void setIno(int ino)
	{
		this.ino=ino;
	}
	
	public int getIno()
	{
		return ino;
	}
	public void setEno(int eno)
	{
		this.eno=eno;
	}
	
	public int getEno()
	{
		return eno;
	}
	
	public void setDate(String Date)
	{
		this.date=Date;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	public String getname()
	{
		return name;
	}
	public void setResult(String result)
	{
		this.result=result;
	}
	
	public String getResult()
	{
		return result;
	}
	
    public void setIns(Inspection ins)
    {
    	this.ins=ins;
    }
    public Inspection getIns()
    {
    	return ins;
    }
	
}
