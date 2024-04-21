package dao;

import entity.Item;
import entity.Receipe;
import provider.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceipeDao {
    private static ReceipeDao instance;
    public static ReceipeDao getInstance() {
        if(instance==null){
            instance = new ReceipeDao();
        }
        return instance;
    }
    // SELECT r.id, r.name, i.name, ri.itemQuantity, i.unit FROM `receipe-items` as ri join receipes as r on ri.receipeId=r.id join items as i on ri.itemId=i.id;

    private Item extractItem(ResultSet res) throws SQLException {
        String name = res.getString("ingredientName");
        int quantity = res.getInt("ingredientQuantity");
        String unit = res.getString("ingredientUnit");
        int id = res.getInt("itemId");
        return new Item(id, name, quantity, unit);
    }

    public List<Receipe> getReceipes(){
        List<Receipe> receipes = new ArrayList<>();
        Receipe currentReceipe = new Receipe(0,"");
        try{
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "SELECT r.description as receipeDescription, r.id as receipeId, r.name as receipeName, " +
                        "i.id as itemId, i.name as ingredientName, ri.itemQuantity as ingredientQuantity, i.unit as ingredientUnit " +
                        "FROM `receipe-items` as ri join receipes as r on ri.receipeId=r.id join items as i on ri.itemId=i.id;");
            ResultSet resultSelect = statement.executeQuery();
            while(resultSelect.next()){
                Item item = extractItem(resultSelect);
                int receipeId = resultSelect.getInt("receipeId");
                String receipeName = resultSelect.getString("receipeName");
                String receipeDescription = resultSelect.getString("receipeDescription");
                if(currentReceipe.getId()!=receipeId){
                    System.out.println(currentReceipe.getId()+" "+receipeId+"\nChanging receipe");
                    if(currentReceipe.getId()!=0){
                        System.out.println("adding receipe : "+currentReceipe.getName());
                        receipes.add(currentReceipe);
                    }
                    currentReceipe = new Receipe(receipeId, receipeName);
                    currentReceipe.setDescription(receipeDescription);
                    System.out.println(currentReceipe);
                }
                System.out.println("adding "+item.getName()+" to "+currentReceipe.getName());
                currentReceipe.addIngredient(item);
                System.out.println("receipes : "+receipes);
            }
            receipes.add(currentReceipe);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receipes;
    }
}
