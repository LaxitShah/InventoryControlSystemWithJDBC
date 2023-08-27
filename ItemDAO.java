package j2ee.lab03;


import java.util.ArrayList;

public interface ItemDAO {
    void add(ItemTO item) throws DAOException;
    void update(ItemTO item) throws ItemNotFound, DAOException;
    ItemTO findItem(int item_code) throws ItemNotFound, DAOException;
    void delete(int item_code) throws ItemNotFound, DAOException;
    ArrayList<ItemTO> getAll() throws DAOException;
    ArrayList<ItemTO> getAllPaginated(int page_no) throws DAOException;
}
