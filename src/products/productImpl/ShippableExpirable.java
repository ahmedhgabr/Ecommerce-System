package products.productImpl;

import products.Expirable;
import products.Shippable;

import java.time.LocalDateTime;

public class ShippableExpirable extends BaseProduct implements Shippable, Expirable {

    private ExpirableProduct expirableProduct;
    private ShippableProduct shippableProduct;

    public ShippableExpirable(String name, double price, int quantity, LocalDateTime expirationDate, double weight) {
        super(name, price, quantity);
        this.expirableProduct = new ExpirableProduct(name, price, quantity, expirationDate);
        this.shippableProduct = new ShippableProduct(name, price, quantity, weight);
    }

    @Override
    public double getWeight() {
        return shippableProduct.getWeight();
    }

    @Override
    public LocalDateTime getExpirationDate() {
        return expirableProduct.getExpirationDate();
    }

    @Override
    public boolean isExpired() {
        return expirableProduct.isExpired();
    }
}
