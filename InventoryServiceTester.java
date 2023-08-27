package j2ee.lab03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InventoryServiceTester {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/InventoryManage";
        String username = "postgres";
        String password = "admin";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            ItemDAO itemDAO = new ItemDAOImplSQL(connection);
            InventoryService inventoryService = new InventoryServiceImpl(itemDAO);

           
            try {
                int itemCode = 1; // Change this to the item code you want to retrieve
                ItemTO item = inventoryService.getItem(itemCode);
                System.out.println("Retrieved Item: " + item);
            } catch (ItemNotFound e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
