package products.Decorator;

import products.Product;

public class ProductDecorator implements Product {
    private Product product;

    public ProductDecorator(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public void setName(String name) {
        product.setName(name);
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

    @Override
    public void setPrice(double price) {
        product.setPrice(price);
    }

    @Override
    public int getQuantity() {
        return product.getQuantity();
    }

    @Override
    public void addQuantity(int quantity) {
        product.addQuantity(quantity);
    }

    @Override
    public void removeQuantity(int quantity) {
        product.removeQuantity(quantity);
    }

    @Override
    public boolean isAvailable() {
        return product.isAvailable();
    }
}
