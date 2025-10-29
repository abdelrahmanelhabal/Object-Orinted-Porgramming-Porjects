import interfaces.Shippable;
import model.*;
import service.CheckoutService;
import service.ShippableService;
import service.UserService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static UserService userService = new UserService();
    static ShippableProduct TV = new ShippableProduct("TV",250,5,1000);
    static ShippableExpireProduct Cheese =  new ShippableExpireProduct("cheese",250,4,LocalDate.now().plusDays(2),500);
    static Product scratchCard = new Product("scratchCard" , 250,6);
    static Cart cart = new Cart();
    static ShippableService service =  new ShippableService();
    static CheckoutService checkoutService = new CheckoutService() ;
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("\tWelcome to Our shop");
        System.out.println("=================================================");
        User Customer = null;
        int num ;
        do{

            System.out.println("\n================= Menu =================");
            System.out.println("0.Exit\n1.Login\n2.Register\n3.Shopping\n4.Checkout");
            System.out.println("=================================================");
            num = input.nextInt();
            switch(num){
                case 0:
                    System.out.println("Good Bye!");
                    break;
                case 1:
                    Customer = Login() ;
                    break;
                case 2:
                    Register();
                    break;
                case 3:
                    if (Customer == null){
                        System.out.println("You should Login in Before Shopping");
                        continue;
                    }
                    Shopping(Customer);
                    break;
                case 4:
                    if (Customer == null){
                        System.out.println("You should Login in Before Shopping");
                        continue;
                    }
                    Checkout(Customer,cart);
                    break;
                default:
                    System.out.println("Invalid selection. Try again");
            }
        }while (num != 0);
    }

    static User Login(){
        System.out.println("Please enter your username: ");
        String username = input.next();

        System.out.println("Please enter your password: ");
        String password = input.next();

        if (username.isEmpty() || password.isEmpty()){
            System.out.println("Username or password cannot be empty.");
            return null;
        }
        if (userService.findUser(username, password)){
            User logged = userService.getUsers(username, password);
            System.out.println("Login successful!");
            System.out.println("Welcome Mr: " + logged.getUsername());
            return logged;
        }
        else{
            System.out.println("Invalid username or password.");
            return null;
        }
    }
    static void Register(){
        System.out.println("Please enter your username: ");
        String username = input.next();

        System.out.println("Please enter your email: ");
        String email = input.next();

        System.out.println("Please enter your password: ");
        String password = input.next();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            System.out.println("Username, email and password are required.");
            return;
        }

        if (userService.findUser(username, password)){
            System.out.println("This username already exists!");
            return;
        }
        userService.addUser(username,password,email);
        System.out.println("Register successful! You can now login.");
    }
    static void Shopping(User Customer) {
        double totalPrice = 0 ;
        HashMap<Shippable,Integer>order = new HashMap<>();
        while (true) {
            Map<Integer, Product> menu = showAvailableProducts();
            if (menu.isEmpty()) {
                System.out.println("No Available Products!");
                return;
            }

            System.out.print("Enter product number (or 0 to go back): ");
            int sel = input.nextInt();
            if (sel == 0) return;

            Product selected = menu.get(sel);
            if (selected == null) {
                System.out.println("Selected product is not available.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int  quantity = input.nextInt();
            if (quantity <= 0) {
                System.out.println("Quantity must be at least 1.");
                continue;
            }
            if (quantity > selected.getQuantity()) {
                System.out.printf("Requested quantity exceeds stock (available: %d).\n", selected.getQuantity());
                continue;
            }

            totalPrice += selected.getPrice() * quantity;
            if (Customer.getBalance() < totalPrice) {
                System.out.println("Insufficient balance. Please top up or choose fewer items.");
                return;
            }

            selected.decreaseQuantity(quantity);
            cart.addProduct(selected, quantity);

            if (selected instanceof Shippable sh) {
                order.merge(sh, quantity, Integer::sum);
            }

            System.out.println("Added to cart. Continue shopping? 1.YES  2.NO");
            int choice = input.nextInt();
            if (choice != 1) {
                service.ship(order);
                return;
            }
        }
    }
    static private Map<Integer, Product> showAvailableProducts() {
        Map<Integer, Product> menu = new HashMap<>();
        int idx = 1;

        Product[] products = {Cheese, TV, scratchCard};
        System.out.println("\n================= Available Products =================");

        for (Product product : products) {
            if (product == null || product.getQuantity() <= 0) continue;

            if (product instanceof ShippableExpireProduct expProd
                    && expProd.getExpireDate().isBefore(LocalDate.now())) {
                continue;
            }

            StringBuilder info = new StringBuilder();
            info.append(idx).append(". ")
                    .append("(Product Name: ").append(product.getName()).append(") ")
                    .append("(Price: ").append(product.getPrice()).append(") ")
                    .append("(Quantity: ").append(product.getQuantity()).append(")");

            if (product instanceof Shippable shippable) {
                info.append(" (Weight: ").append(shippable.getWeight()).append(")");
            }

            if (product instanceof ShippableExpireProduct expirable) {
                info.append(" (Expire Date: ").append(expirable.getExpireDate()).append(")");
            }

            System.out.println(info);
            menu.put(idx++, product);
        }

        if (menu.isEmpty()) {
            System.out.println("No available products right now!");
        }

        System.out.println("======================================================\n");
        return menu;
    }
    static private void  Checkout(User Customer ,Cart cart){
        if (cart == null || cart.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }
        checkoutService.Checkout(Customer, cart);
    }
}
