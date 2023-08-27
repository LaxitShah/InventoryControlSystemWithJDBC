package j2ee.lab03;

public class InventoryServiceImpl implements InventoryService {
    private ItemDAO itemDAO;

    public InventoryServiceImpl(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public ItemTO getItem(int itemno) throws ItemNotFound {
        try {
            return itemDAO.findItem(itemno);
        } catch (DAOException e) {
            throw new ItemNotFound("Item with code " + itemno + " not found.");
        }
    }
}
