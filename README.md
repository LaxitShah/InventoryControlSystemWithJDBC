# InventoryControlSystemWithJDBC



# Lab03: Inventory Application with Database Persistence

In this lab, we will extend the inventory application from Lab01 and make it database persistent by utilizing relational databases. We will create two tables for this application:

1. **Table Category** with attributes:
   - `cat_id` int
   - `cat_name` varchar(50)
   
   This table stores item categories.

2. **Table Item** with attributes:
   - `code` int
   - `description` varchar(50)
   - `stock` int
   - `min_stock` int
   - `cost` decimal(8,2)
   - `cat_id` int
   
   In this table, `cat_id` is a foreign key referring to the Category table.

We will create these tables in the DBMS of your choice and populate them with sample rows. For the sake of implementation, let's assume we have identified the following interface for the Data Access Object (DAO) for items, named `ItemDAO`, as discussed in the lectures.

In this lab, you are expected to:
1. Create a concrete class `ItemDAOImplSQL` that implements the methods specified in the `ItemDAO` interface. For reference, you can find an example implementation of a similar class, `BookDAOImplSQL`, [here](https://github.com/pmjat/j2ee/blob/master/j2ee/book/dao/sql/BookDAOImplSQL.java).

2. Develop a console-based `Tester` class that performs various CRUD operations on the Item table using the implemented DAO methods.

3. Design and implement the Inventory service class, which we'll name `InventoryService`. Here's the interface for `InventoryService`:
   ```java
   public interface InventoryService {
       void addItem(ItemTO item) throws DAOException;
       void updateItem(ItemTO item) throws ItemNotFound, DAOException;
       ItemTO findItem(int item_code) throws ItemNotFound, DAOException;
       void deleteItem(int item_code) throws ItemNotFound, DAOException;
       ArrayList<ItemTO> getAllItems() throws DAOException;
       ArrayList<ItemTO> getAllItemsPaginated(int page_no) throws DAOException;
   }
