import products.Shippable;

import java.util.ArrayList;

public class ShippingService {
    private double costPerKg;

    ShippingService(double costPerKg) {
        if (costPerKg < 0) {
            throw new IllegalArgumentException("Cost per kg cannot be negative");
        }
        this.costPerKg = costPerKg;
    }

    public double calculateShippingCost(ArrayList<Shippable> shippable) {
        double totalWeight = getTotalWeight(shippable);
        return totalWeight * costPerKg;
    }

    public double getTotalWeight(ArrayList<Shippable> shippable) {
        if (shippable == null || shippable.isEmpty()) {
            return 0.0; // No items to ship
        }
        double totalWeight = 0.0;
        for (Shippable item : shippable) {
            if (item != null) {
                totalWeight += item.getWeight();
            }
        }
        return totalWeight;
    }

}
