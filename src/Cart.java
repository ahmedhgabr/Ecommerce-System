import products.Decorator.Expirable;
import products.Decorator.Shippable;
import products.Product;
import java.util.HashMap;

public class Cart {
    private HashMap<Product, CartItem> items;
    private ShippingService shippingService;
    private Customer customer;

    public Cart(ShippingService shippingService, Customer customer) {
        this.items = new HashMap<>();
        this.shippingService = shippingService;
        this.customer = customer;
    }

    public void add(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        CartItem cartItem = items.get(product);
        if (cartItem == null) {
            cartItem = new CartItem(product, quantity);
            items.put(product, cartItem);
        } else {
            cartItem.addQuantity(quantity);
        }
    }

    public void checkout() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot proceed to checkout.");
        }

        double subtotal = calculateSubtotal();
        Shippable[] shippableItems = items.values().stream()
                .filter(item -> item.product instanceof Shippable)
                .map(item -> (Shippable) item.product)
                .toArray(Shippable[]::new);

        double shippingCost = shippingService.calculateShippingCost(shippableItems);
        double total = subtotal + shippingCost;

        customer.deductBalance(total);

        System.out.println("Order Summary:");
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Shipping Cost: " + shippingCost);
        System.out.println("Total: " + total);

        items.clear();
    }

    private double calculateSubtotal() {
        double total = 0.0;
        for (CartItem item : items.values()) {
            total += item.product.getPrice() * item.quantity;
        }
        return total;
    }

    class CartItem {
        Product product;
        int quantity;

        CartItem(Product product, int quantity) {
            this.product = product;
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            } else if (product.getQuantity() < quantity) {
                throw new IllegalArgumentException("Quantity exceeds available stock. Available: " + product.getQuantity());
            }

            if (product instanceof Expirable expirableProduct) {
                if (expirableProduct.isExpired()) {
                    throw new IllegalArgumentException("Cannot add expired product to cart");
                }
            }
            this.quantity = quantity;
        }

        void addQuantity(int newQuantity) {
            quantity += newQuantity;
            if (quantity > product.getQuantity()) {
                throw new IllegalArgumentException("Quantity exceeds available stock. Available: " + product.getQuantity());
            }
        }
    }
}