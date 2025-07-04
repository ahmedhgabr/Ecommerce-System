package products;

import products.productImpl.BaseProduct;
import products.productImpl.ExpirableProduct;
import products.productImpl.ShippableExpirable;
import products.productImpl.ShippableProduct;

import java.time.LocalDateTime;

public class ProductFactory {

    public Product createProduct(String name, double price , int quantity) {
        return new BaseProduct(name, price, quantity);
    }

    public Product createProduct(String name, double price, int quantity, LocalDateTime expirationDate) {
        return new ExpirableProduct(name, price, quantity, expirationDate);
    }

    public Product createProduct(String name, double price, int quantity, double weight) {
        return new ShippableProduct(name, price, quantity, weight);
    }

    public Product createProduct(String name, double price, int quantity, LocalDateTime expirationDate, double weight) {
        return new ShippableExpirable(name, price, quantity, expirationDate, weight);
    }

}
