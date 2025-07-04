package products.productImpl;

import products.Product;
import products.Shippable;

public class ShippableProduct extends BaseProduct implements Shippable {

    private double weight; // Weight in kilograms

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero");
        }
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

}
