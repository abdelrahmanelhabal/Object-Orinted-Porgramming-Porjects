package model;

import interfaces.Shippable;
import java.time.LocalDate;

public class ShippableExpireProduct extends ExpireProduct implements Shippable {
    private double weight;

    public ShippableExpireProduct(String name, double price, int quantity, LocalDate expireDate, double weight) {
        super(name, price, quantity, expireDate);
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
                "ShippableExpireProduct{name='%s', price=%.2f, quantity=%d, expireDate=%s, weight=%.2f}",
                name, price, quantity, expireDate , weight
        );
    }
}