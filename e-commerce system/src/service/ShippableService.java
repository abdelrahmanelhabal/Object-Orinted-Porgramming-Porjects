package service;

import interfaces.Shippable;

import java.util.HashMap;

public class ShippableService {

    public void ship(HashMap<Shippable, Integer> items){
        if (items.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }
        double totalWeight = 0;
        System.out.println("** Shipment notice **");

        for(HashMap.Entry<Shippable, Integer> item : items.entrySet()){
            String name = item.getKey().getName();
            int frequency = item.getValue();
            double ItemWeight = item.getKey().getWeight() * frequency;
            totalWeight += ItemWeight;
            System.out.printf("%dx %s\t %.1fg%n", frequency, name, ItemWeight);
        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight / 1000);
    }
}
