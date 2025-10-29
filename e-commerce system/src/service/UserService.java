package service;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();

   public boolean findUser(String username , String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public User getUsers(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public void addUser(String username, String password , String email) {
       users.add(new User(username , password,email,1000)); // by default each user have 1000$
       System.out.println("User has been added successfully");
    }


}
