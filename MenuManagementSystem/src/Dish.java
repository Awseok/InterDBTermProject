import java.util.ArrayList;

public class Dish {
	private String dishName;
	private ArrayList<Recipe> recipeArray;
	
	
	public Dish() {};
	
	public Dish(String dishName)
	{
		this.dishName=dishName;
		this.recipeArray = new ArrayList<Recipe>();
	}
	
	public void setDishName(String dishName)
	{
		this.dishName = dishName;
	}
	
	public String getDishName()
	{
		return dishName;
	}
	
	public void setRecipeArray(Recipe recipe)
	{
		this.recipeArray.add(recipe);
	}
	
	public ArrayList<Recipe> getRecipeArray()
	{
		return recipeArray;
	}
}
