
public class Nutrition {

	private int cal;
	private int prt;
	private int vit;
	private int salt;
	private String name;
	private String ETC;

	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setCal(int cal)
	{
		this.cal = cal;
	}
	
	public int getCal()
	{
		return cal;
	}
	
	public void setVit(int vit)
	{
		this.vit = vit;
	}
	
	public int getVit()
	{
		return vit;
	}
	
	void setSalt(int salt)
	{
		this.salt = salt;
	}
	
	int getSalt()
	{
		return salt;
	}
	
	void setETC(String ETC)
	{
		this.ETC = ETC;
	}
	
	String getETC()
	{
		return ETC;
	}
}
