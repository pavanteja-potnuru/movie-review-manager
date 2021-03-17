package reviewmanager.services;
import java.util.*;
import reviewmanager.model.*;

public class UserManager {

    private HashMap<String, User> users;

    public UserManager() {
        users = new HashMap<String, User>();
    }

    /**
     * Create user
     * @param name
     */
    public String createUser(String name) {
        if(users.get(name) != null) {
            return String.format("User with name:%s already exist", name);
        }
        users.put(name, new User(name));
        return "Successfully created";
    }

    /**
     * get user by user name
     * @param name
     * @return
     */
    public User getUser(String name) {
        return users.get(name);
    }

    /**
     * print Users
     * @param name
     * @return
     */
    public void printUsers() {
        users.forEach((name, user) -> {
            System.out.println(name);
        });
    }
}
