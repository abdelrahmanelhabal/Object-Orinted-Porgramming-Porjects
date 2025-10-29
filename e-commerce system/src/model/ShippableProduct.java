package model;

import interfaces.Shippable;

public class ShippableProduct extends Product implements Shippable {
    private double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format(
                "ShippableProduct{name='%s', price=%.2f, quantity=%d, weight=%.2f}",
                name, price, quantity, weight
        );
    }
}
