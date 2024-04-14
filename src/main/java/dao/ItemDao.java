package dao;

import entity.Item;
import provider.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    private static ItemDao instance;
    private static ItemDao getInstance(){
        if(instance==null){
            instance = new ItemDao();
        }
        return instance;
    }
    private Item extractItem(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        byte[] picture = result.getBytes("picture");
        int parentId = result.getInt("parentId");
        float quantity = result.getFloat("quantity");
        String unit = result.getNString("unit");
        return new Item(id,name,picture,parentId,quantity,unit);
    }
    public List<Item> getItems(){
        List<Item> items = new ArrayList<>();
        try{
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * from items");
            ResultSet resultSelect = statement.executeQuery();
            while(resultSelect.next()){
                Item item = extractItem(resultSelect);
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

}
