package model;

import java.util.regex.Pattern;

public class User {
    private static final Pattern pattern = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private String username;
    private String password;
    private String email;
    private double balance;

    public User(){

    }

    public User(String username, String password, String email , double balance) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setBalance(balance);
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (password.length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters");
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String trimmed = email.trim();
        if (!pattern.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("Email format is invalid");
        }
        this.email = trimmed;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0.0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public void decreaseBalance(double amount) {
        if (Double.isNaN(amount) || amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Amount cannot exceed current balance. Your Balance is : " + this.balance);
        }
        this.balance -= amount;
    }

    public void increaseBalance(double amount) {
        if (Double.isNaN(amount) || amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance += amount;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + String.format("%.2f", balance) +
                '}';
    }
}

