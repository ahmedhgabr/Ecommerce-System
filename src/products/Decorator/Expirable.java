package products.Decorator;

import products.Product;

import java.time.LocalDateTime;

public class Expirable extends ProductDecorator {

    private final LocalDateTime expirationDate;

    public Expirable(Product product, LocalDateTime expirationDate) {
        super(product);
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
