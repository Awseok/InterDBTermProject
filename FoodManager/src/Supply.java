
public class Supply {

	
	private int sno;
	private int amount;
	private String gname;
	private String sname;
	private String sDate;
	private String eDate;
	private Supply sup;
	
	
	public Supply()
	{
		super();
	}
	
	public Supply(int sno,String sDate,int amount, String gname, String sname,String eDate)
	{
		sup = new Supply();
		sup.setSno(sno);
		sup.setSDate(sDate);
		sup.setAmount(amount);
		sup.setGname(gname);
		sup.setSname(sname);
		sup.setEDate(eDate);
		
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
	
	
	public void setSDate(String sDate)
	{
		this.sDate=sDate;
	}
	
	public String getSDate()
	{
		return sDate;
	}
	
	public void setEDate(String eDate)
	{
		this.eDate=eDate;
	}
	
	public String getEDate()
	{
		return eDate;
	}
	
	
}
