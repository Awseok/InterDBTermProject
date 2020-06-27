
public class FoodMaterial {
	
	private int amount;
	private Nutrition nut;
	
	FoodMaterial() {};
	
	FoodMaterial(String name, int amount)
	{
		nut = new Nutrition();
		nut.setName(name);
		this.amount = amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public void setNut(Nutrition nut)
	{
		this.nut = nut;
	}
	
	public Nutrition getNut()
	{
		return nut;
	}
}
