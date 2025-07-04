import products.Decorator.Shippable;

public class ShippingService {
    private double costPerKg;

    ShippingService(double costPerKg) {
        if (costPerKg < 0) {
            throw new IllegalArgumentException("Cost per kg cannot be negative");
        }
        this.costPerKg = costPerKg;
    }

    public double calculateShippingCost(Shippable[] shippable) {
        if (shippable == null || shippable.length == 0) {
            return 0.0; // No items to ship
        }
        double totalWeight = 0.0;
        for (Shippable item : shippable) {
            if (item != null) {
                totalWeight += item.getWeight();
            }
        }
        return totalWeight * costPerKg;
    }

}
