package j2ee.lab03;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOTester {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/InventoryManage";
        String username = "postgres";
        String password = "admin";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            ItemDAO itemDAO = new ItemDAOImplSQL(connection);
            System.out.println("Adding a new item...");

            ItemTO newItem = new ItemTO(20, "Test Item", 50, 5, 10.99, 1);
            itemDAO.add(newItem);
            System.out.println("Item added: " + newItem);

            
            try {
                int itemCodeToUpdate = 3;
                System.out.println("Retrieving item with code " + itemCodeToUpdate + "...");
                ItemTO retrievedItem = itemDAO.findItem(itemCodeToUpdate);
                System.out.println("Retrieved item: " + retrievedItem);

                retrievedItem.setDescription("Updated Laptop");
                System.out.println("Updating item description...");
                itemDAO.update(retrievedItem);
                System.out.println("Item updated: " + retrievedItem);
            } catch (ItemNotFound e) {
                System.out.println(e.getMessage());
            }

            int itemCodeToDelete = 1;
            try {
                System.out.println("Deleting item with code " + itemCodeToDelete + "...");
                itemDAO.delete(itemCodeToDelete);
                System.out.println("Item deleted: " + itemCodeToDelete);
            } catch (ItemNotFound e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Fetching all items...");
            ArrayList<ItemTO> allItems = itemDAO.getAll();
            for (ItemTO item : allItems) {
                System.out.println(item);
            }

            int pageNo = 1;
            System.out.println("Fetching items on page " + pageNo + "...");
            ArrayList<ItemTO> paginatedItems = itemDAO.getAllPaginated(pageNo);
            for (ItemTO item : paginatedItems) {
                System.out.println(item);
            }

        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }
    }
}
