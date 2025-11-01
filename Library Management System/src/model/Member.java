package model;

import java.util.regex.Pattern;

public class Member {
    private static final Pattern emailPattern = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private String username;
    private String password;
    private String email;
    private String Phone;
    private String Address;

    public Member(){

    }
    public Member(String username, String password, String email, String phone, String address){
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username;
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
        if (!emailPattern.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("Email format is invalid");
        }
        this.email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
       if (!phone.matches("^\\+?\\d{10,15}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }

        if (address.length() < 5 || address.length() > 100) {
            throw new IllegalArgumentException("Address must be between 5 and 100 characters");
        }

        if (!address.matches("^[\\w\\s,.-]+$")) {
            throw new IllegalArgumentException("Address contains invalid characters");
        }
        Address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }
}
