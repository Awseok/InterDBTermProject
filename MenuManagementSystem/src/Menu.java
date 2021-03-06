import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Menu/*")


public class Menu extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int mno;
	private int cno;
	private String date;
	private String dist;
	private Menu menu;
	
	public Menu(){
		super();
	};
	
	public Menu(int mno,int cno,String date,String dist)
	{
		menu = new Menu();
		menu.setMno(mno);
		menu.setCno(cno);
		menu.setDate(date);
		menu.setDist(dist);
		
	}
	
	public void setMno(int mno)
	{
		this.mno=mno;
	}
	
	public int getMno()
	{
		return mno;
	}
	public void setCno(int cno)
	{
		this.cno=cno;
	}
	
	public int getCno()
	{
		return cno;
	}
	
	public void setDate(String Date)
	{
		this.date=Date;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setDist(String dist)
	{
		this.dist=dist;
	}
	public String getDist()
	{
		return dist;
	}
		
    public void setMenu(Menu menu)
    {
    	this.menu=menu;
    }
    public Menu getMenu()
    {
    	return menu;
    }
	
}
