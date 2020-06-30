
public class Cooking {
	private int cookingKey;
	private Dish dish;
	private int employeeNumber;
	private String cookingDate;
	
	public Cooking() {
		dish = new Dish();
	};
	
	public Cooking(int cookingKey, int employeeNumber, String cookingDate)
	{
		dish = new Dish();
		this.cookingKey = cookingKey;
		this.employeeNumber = employeeNumber;
		this.cookingDate = cookingDate;
	}
	
	public int getCookingKey()
	{
		return cookingKey;
	}
	
	public void setCookingKey(int cookingKey)
	{
		this.cookingKey = cookingKey;
	}
	
	public Dish getDish()
	{
		return dish;
	}
	
	public void setDish(Dish dish)
	{
		this.dish = dish;
	}
	
	public int getEmployeeNumber()
	{
		return employeeNumber;
	}
	
	public void setEmployeeeNumber(int employeeNumber)
	{
		this.employeeNumber = employeeNumber;
	}
	
	public String getCookingDate()
	{
		return cookingDate;
	}
	
	public void setCookingDate(String cookingDate)
	{
		this.cookingDate = cookingDate;
	}
}
