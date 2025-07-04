package products;

public class BaseProduct implements Product {

    private String name;
    private double price;
    /**
     * Quantity of the product in stock.
     **/
    private int quantity;

    public BaseProduct(String name, double price) {
        this(name, price, 0);
    }

    public BaseProduct(String name, double price, int quantity) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void addQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity to add cannot be negative");
        }
        this.quantity += quantity;
    }

    @Override
    public boolean isAvailable() {
        return quantity > 0;
    }

    @Override
    public void removeQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("Not enough stock");
        }
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + '}';
    }


}
