package service;

import interfaces.Shippable;
import model.Cart;
import model.Product;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class CheckoutService {
    private final int shippingFeePerKg = 40 ;
    public void Checkout(User Customer , Cart cart ){
        if (cart == null || cart.isEmpty()){
            throw new RuntimeException("Cart is null or empty");
        }
        double totalPrice = 0;
        HashMap<Shippable, Integer> shippableProducts = new HashMap<>();
       for (Map.Entry<Product, Integer> item : cart.getItems().entrySet()){
           Product product = item.getKey();
           int frequency = item.getValue();

            double ItemPrice = product.getPrice() * frequency;
            totalPrice += ItemPrice;

           if (product instanceof Shippable sh) {
               shippableProducts.merge(sh, frequency, Integer::sum);
           }
       }
        double totalWeight = 0;
        if (!shippableProducts.isEmpty()) {
            for(Map.Entry<Shippable, Integer> item : shippableProducts.entrySet()){
                totalWeight += item.getValue();
            }
        }
        double totalFee = totalWeight * shippingFeePerKg;
        totalPrice = totalPrice + totalFee;
        double oldBalance = Customer.getBalance();
        Customer.decreaseBalance(totalPrice);
        printReceipt(totalPrice, totalFee, totalWeight, Customer.getBalance(),oldBalance);
    }
    private void printReceipt(double subtotal, double shippingFees, double totalAmount, double customerBalance, double oldBalance) {
        System.out.println("** Checkout receipt **");
        System.out.printf("Customer Balance before process        %.2f\n", oldBalance);
        System.out.printf("Subtotal         %.2f\n", subtotal);
        System.out.printf("Shipping         %.2f\n", shippingFees);
        System.out.printf("Total Amount     %.2f\n", totalAmount);
        System.out.printf("Customer Balance after process        %.2f\n", customerBalance);
    }
}
