import products.Expirable;
import products.ProductFactory;
import products.Shippable;
import products.productImpl.BaseProduct;
import products.Product;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer(1000000.0);
        ShippingService shippingService = new ShippingService(2.0);
        Cart cart = new Cart(shippingService, customer);

        ProductFactory productFactory = new ProductFactory();

        // Create a shippable product that is expirable
        LocalDateTime expirationDateInFuture = LocalDateTime.now().plusDays(7);
        Product cheese = productFactory.createProduct("Cheese", 5.0, 10, expirationDateInFuture, 0.5);

        // Create a shippable product that is not expirable
        Product tv = productFactory.createProduct("TV", 500.0, 1, 5.0);

        // Create a regular product that is not shippable or expirable
        Product ebook = productFactory.createProduct("Ebook", 20.0, 3);

        cart.add(cheese, 2);
        cart.add(tv, 1);
        cart.add(ebook, 3);

        cart.checkout();

    }
}