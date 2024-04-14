import dao.ItemDao;
import entity.Item;
import org.junit.Test;

import java.util.List;

public class ItemServiceTestCase {
    ItemDao itemDao = new ItemDao();
    @Test
    public void shouldGetItems(){
        List<Item> items = itemDao.getItems();
        for(Item item:items){
            System.out.println(item);
        }
    }
}
