package model;

import interfaces.Shippable;

import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> items;

    public Cart(){
        items = new HashMap<>();
    }

    public void setItems(HashMap<Product, Integer> items) {
        if (items == null) {
            throw new IllegalArgumentException("Items map cannot be null");
        }
        this.items = items;
    }

    public HashMap<Product, Integer> getItems(){
        return items;
    }

    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        items.merge(product, quantity, Integer::sum);
    }
    public void removeProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        items.remove(product);
    }
    public boolean isEmpty(){
        return items.isEmpty();
    }
}
