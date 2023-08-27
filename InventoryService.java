package j2ee.lab03;

public interface InventoryService {
    ItemTO getItem(int itemno) throws ItemNotFound;
}
