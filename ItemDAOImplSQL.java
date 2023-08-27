package j2ee.lab03;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImplSQL implements ItemDAO {
    private Connection connection;

    public ItemDAOImplSQL(Connection connection) {
        this.connection = connection;
    }

    public void add(ItemTO item) throws DAOException {
        try {
            String query = "INSERT INTO Item(code, description, stock, min_stock, cost, cat_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item.getCode());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setInt(3, item.getStock());
            preparedStatement.setInt(4, item.getMinStock());
            preparedStatement.setDouble(5, item.getCost());
            preparedStatement.setInt(6, item.getCategoryId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public void update(ItemTO item) throws ItemNotFound, DAOException {
        try {
            String query = "UPDATE Item SET description=?, stock=?, min_stock=?, cost=?, cat_id=? WHERE code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getDescription());
            preparedStatement.setInt(2, item.getStock());
            preparedStatement.setInt(3, item.getMinStock());
            preparedStatement.setDouble(4, item.getCost());
            preparedStatement.setInt(5, item.getCategoryId());
            preparedStatement.setInt(6, item.getCode());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new ItemNotFound("Item with code " + item.getCode() + " not found.");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public ItemTO findItem(int item_code) throws ItemNotFound, DAOException {
        try {
            String query = "SELECT * FROM Item WHERE code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item_code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new ItemTO(
                    resultSet.getInt("code"),
                    resultSet.getString("description"),
                    resultSet.getInt("stock"),
                    resultSet.getInt("min_stock"),
                    resultSet.getDouble("cost"),
                    resultSet.getInt("cat_id")
                );
            } else {
                throw new ItemNotFound("Item with code " + item_code + " not found.");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public void delete(int item_code) throws ItemNotFound, DAOException {
        try {
            String query = "DELETE FROM Item WHERE code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item_code);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new ItemNotFound("Item with code " + item_code + " not found.");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public ArrayList<ItemTO> getAll() throws DAOException {
        ArrayList<ItemTO> items = new ArrayList<ItemTO>();
        try {
            String query = "SELECT * FROM Item";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                items.add(new ItemTO(
                    resultSet.getInt("code"),
                    resultSet.getString("description"),
                    resultSet.getInt("stock"),
                    resultSet.getInt("min_stock"),
                    resultSet.getDouble("cost"),
                    resultSet.getInt("cat_id")
                ));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return items;
    }

    public ArrayList<ItemTO> getAllPaginated(int page_no) throws DAOException {
        int itemsPerPage = 10; 
        int offset = (page_no - 1) * itemsPerPage;
        ArrayList<ItemTO> items = new ArrayList<ItemTO>();
        try {
            String query = "SELECT * FROM Item LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, itemsPerPage);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                items.add(new ItemTO(
                    resultSet.getInt("code"),
                    resultSet.getString("description"),
                    resultSet.getInt("stock"),
                    resultSet.getInt("min_stock"),
                    resultSet.getDouble("cost"),
                    resultSet.getInt("cat_id")
                ));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return items;
    }

}
