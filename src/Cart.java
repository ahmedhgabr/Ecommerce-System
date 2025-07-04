import products.Expirable;
import products.Product;
import products.Shippable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Cart {
    private HashMap<Product, CartItem> items;
    private ShippingService shippingService;
    private Customer customer;

    private ArrayList<Shippable> shippableItems;

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

        setShippableItems();
        double shippingCost = getShippingCost();

        double total = subtotal + shippingCost;
        customer.deductBalance(total);

        printCheckout(subtotal, shippingCost, total);

        items.clear();
        shippableItems = null; // Clear shippable items after checkout
    }

    private double calculateSubtotal() {
        double total = 0.0;
        for (CartItem item : items.values()) {
            total += item.product.getPrice() * item.quantity;
        }
        return total;
    }

    private void setShippableItems() {
        shippableItems = items.values().stream()
                .filter(item -> item.product instanceof Shippable)
                .flatMap(item ->
                        Collections.nCopies(item.quantity, (Shippable) item.product)
                                .stream() // Convert List<Shippable> to Stream<Shippable>
                )
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private double getShippingCost() {
        return shippingService.calculateShippingCost(shippableItems);
    }

    private void printCheckout(double subtotal, double shippingCost, double total) {
        StringBuilder shipmentNotice = new StringBuilder("** Shipment notice **\n");
        StringBuilder checkoutReceipt = new StringBuilder("** Checkout receipt ** \n");

        for (CartItem item : items.values()) {
            shipmentNotice.append(item.addToShipmentNotice()).append("\n");
            checkoutReceipt.append(item.addToCheckoutReceipt()).append("\n");
        }
        shipmentNotice.append("Total package weight ")
                .append(shippingService.getTotalWeight(shippableItems)).append("kg\n");

        checkoutReceipt.append("----------------------\n")
                .append(String.format("Subtotal" + ": %.2f\n", subtotal))
                .append(String.format("Shipping" + ": %.2f\n", shippingCost))
                .append(String.format("Amount  " + ": %.2f\n", total));
        System.out.println(shipmentNotice);
        System.out.println(checkoutReceipt);
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

        public String addToShipmentNotice() {
            if (!(product instanceof Shippable)) {
                return "";
            }
            return quantity + "x " + product.getName() + "     " + ((Shippable) product).getWeight() * 1000 * quantity + "g";
        }

        public String addToCheckoutReceipt() {
            return quantity + "x " + product.getName() + "     " + product.getPrice() * quantity;
        }
    }
}