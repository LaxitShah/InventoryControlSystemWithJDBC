package j2ee.lab03;

public class ItemTO {
    private int code;
    private String description;
    private int stock;
    private int minStock;
    private double cost;
    private int categoryId;

    public ItemTO(int code, String description, int stock, int minStock, double cost, int categoryId) {
        this.code = code;
        this.description = description;
        this.stock = stock;
        this.minStock = minStock;
        this.cost = cost;
        this.categoryId = categoryId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ItemTO{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", minStock=" + minStock +
                ", cost=" + cost +
                ", categoryId=" + categoryId +
                '}';
    }
}
