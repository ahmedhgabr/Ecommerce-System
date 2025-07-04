package products;

public interface Product {
    String getName();
    void setName(String name);

    double getPrice();
    void setPrice(double price);

    int getQuantity();
    void addQuantity(int quantity);
    void removeQuantity(int quantity);

    boolean isAvailable();
}
