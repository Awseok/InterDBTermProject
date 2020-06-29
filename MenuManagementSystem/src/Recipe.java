
public class Recipe {
	private int recipeKey;
	private String dishName;
	private String gredientName;
	private String recipeNumber;
	private int amount;
	
	public Recipe() {};
	
	public Recipe(int recipeKey, String dishName, String gredientName, int amount)
	{
		this.recipeKey = recipeKey;
		this.dishName = dishName;
		this.gredientName = gredientName;
		this.amount = amount;
	}
	
	public Recipe(int recipeKey, String dishName, String gredientName, String recipeNumber, int amount)
	{
		this.recipeKey = recipeKey;
		this.dishName = dishName;
		this.gredientName = gredientName;
		this.recipeNumber = recipeNumber;
		this.amount = amount;
	}
	
	public int getRecipeKey()
	{
		return recipeKey;
	}
	
	public void setRecipeKey(int recipeKey)
	{
		this.recipeKey = recipeKey;
	}
	
	public String getDishName()
	{
		return dishName;
	}
	
	public void setDishName(String dishName)
	{
		this.dishName = dishName;
	}
	
	public String getGredientName()
	{
		return gredientName;
	}
	
	public void setGredientName(String gredientName)
	{
		this.gredientName = gredientName;
	}
	
	public String getRecipeNumber()
	{
		return recipeNumber;
	}
	
	public void setRecipeNumber(String recipeNumber)
	{
		this.recipeNumber = recipeNumber;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	public int getAmount()
	{
		return amount;
	}
}
