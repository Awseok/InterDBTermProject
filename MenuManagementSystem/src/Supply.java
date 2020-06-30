import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Supply/*")

public class Supply extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int sno;
	private int amount;
	private String gname;
	private String sname;
	private String sdate;
	private String edate;
	private Supply supply;
	
	
	public Supply()
	{
		super();
	}
	
	public Supply(int sno,String sdate,int amount, String gname, String sname,String edate)
	{
		supply = new Supply();
		supply.setSno(sno);
		supply.setSdate(sdate);
		supply.setAmount(amount);
		supply.setGname(gname);
		supply.setSname(sname);
		supply.setEdate(edate);
		
	}
	
	public void setSno(int sno)
	{
		this.sno=sno;
	}
	
	public int getSno()
	{
		return sno;
	}
	public void setAmount(int amount)
	{
		this.amount=amount;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public void setGname(String gname)
	{
		this.gname=gname;
	}
	
	public String getGname()
	{
		return gname;
	}
	
	public void setSname(String sname)
	{
		this.sname=sname;
	}
	
	public String getSname()
	{
		return sname;
	}
	
	
	public void setSdate(String sdate)
	{
		this.sdate=sdate;
	}
	
	public String getSdate()
	{
		return sdate;
	}
	
	public void setEdate(String edate)
	{
		this.edate=edate;
	}
	
	public String getEdate()
	{
		return edate;
	}
	
	  public void setSupply(Supply supply)
	    {
	    	this.supply=supply;
	    }
	    public Supply getSupply()
	    {
	    	return supply;
	    }
	
}
