package reviewmanager.services;

import reviewmanager.datastore.DataStore;
import reviewmanager.datastore.IDataStore;
import reviewmanager.model.*;

public class UserManager {
    private IDataStore<User> userDataStore;

    public UserManager() {
        userDataStore = new DataStore<User>();
    }

    /**
     * Create user
     * @param name
     * @throws ServiceException
     */
    public void createUser(String name) throws ServiceException {
        System.out.println(String.format("Create user with name %s", name));
        if(userDataStore.get(name) != null) {
            throw new ServiceException(String.format("User with name:%s already exist", name));
        }
        userDataStore.create(name, new User(name));
        System.out.println(String.format("User %s created successfully", name));
    }

    /**
     * get user by user name
     * @param name
     * @return
     */
    public User getUser(String name) {
        return userDataStore.get(name);
    }

    /**
     * print Users
     * @param name
     * @return
     */
    public void printUsers() {
        userDataStore.getCollectionStream().forEach((userkvp) -> {
            System.out.println(userkvp.getKey());
        });
    }
}
