package dao;

import entity.Item;
import entity.Recipe;
import provider.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {
    private static RecipeDao instance;
    public static RecipeDao getInstance() {
        if(instance==null){
            instance = new RecipeDao();
        }
        return instance;
    }
    // SELECT r.id, r.name, i.name, ri.itemQuantity, i.unit FROM `recipe-items` as ri join recipes as r on ri.recipeId=r.id join items as i on ri.itemId=i.id;

    private Item extractItem(ResultSet res) throws SQLException {
        String name = res.getString("ingredientName");
        int quantity = res.getInt("ingredientQuantity");
        String unit = res.getString("ingredientUnit");
        int id = res.getInt("itemId");
        return new Item(id, name, quantity, unit);
    }

    public List<Recipe> getrecipes(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe currentRecipe = new Recipe(0,"");
        try{
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "SELECT r.description as recipeDescription, r.id as recipeId, r.name as recipeName, " +
                        "i.id as itemId, i.name as ingredientName, ri.itemQuantity as ingredientQuantity, i.unit as ingredientUnit " +
                        "FROM `recipe-items` as ri join recipes as r on ri.recipeId=r.id join items as i on ri.itemId=i.id;");
            ResultSet resultSelect = statement.executeQuery();
            while(resultSelect.next()){
                Item item = extractItem(resultSelect);
                int recipeId = resultSelect.getInt("recipeId");
                String recipeName = resultSelect.getString("recipeName");
                String recipeDescription = resultSelect.getString("recipeDescription");
                if(currentRecipe.getId()!=recipeId){
                    System.out.println(currentRecipe.getId()+" "+recipeId+"\nChanging recipe");
                    if(currentRecipe.getId()!=0){
                        System.out.println("adding recipe : "+ currentRecipe.getName());
                        recipes.add(currentRecipe);
                    }
                    currentRecipe = new Recipe(recipeId, recipeName);
                    currentRecipe.setDescription(recipeDescription);
                    System.out.println(currentRecipe);
                }
                System.out.println("adding "+item.getName()+" to "+ currentRecipe.getName());
                currentRecipe.addIngredient(item);
                System.out.println("recipes : "+ recipes);
            }
            recipes.add(currentRecipe);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }
}
