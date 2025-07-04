import products.BaseProduct;
import products.Decorator.Expirable;
import products.Decorator.Shippable;
import products.Product;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Customer richCustomer = new Customer(1000.0);
        ShippingService shippingService = new ShippingService(30.0);
        Cart cart = new Cart(shippingService, richCustomer);

        Product cheese = new BaseProduct("Cheese", 10.0, 5);
        LocalDateTime expirationDateInFuture = LocalDateTime.now().plusDays(7);
        cheese = new Expirable(cheese, expirationDateInFuture );
        cheese = new Shippable(cheese, 0.25);


        if (cheese instanceof Shippable) {
            System.out.println("Cheese is shippable with weight: " + ((Shippable) cheese).getWeight() + " kg");
        }
        if (cheese instanceof Expirable) {
            System.out.println("Cheese expires on: " + ((Expirable) cheese).getExpirationDate());
        }

        cart.add(cheese, 2);

        cart.checkout();

    }
}