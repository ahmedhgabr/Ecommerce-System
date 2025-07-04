package products.productImpl;

import products.Expirable;

import java.time.LocalDateTime;

public class ExpirableProduct extends BaseProduct implements Expirable {

    private final LocalDateTime expirationDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDateTime expirationDate) {
        super(name, price, quantity);
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

}
