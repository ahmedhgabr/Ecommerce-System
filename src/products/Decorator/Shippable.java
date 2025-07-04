package products.Decorator;

import products.Product;

public class Shippable extends ProductDecorator {

    private double weight; // Weight in kilograms

    public Shippable(Product product, double weight) {
        super(product);
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

}
