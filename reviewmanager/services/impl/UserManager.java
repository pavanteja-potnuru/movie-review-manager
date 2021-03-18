package reviewmanager.services.impl;

import reviewmanager.datastore.*;
import reviewmanager.model.*;
import reviewmanager.services.IUserManager;
import reviewmanager.utils.*;
public class UserManager implements IUserManager {
    private IDataStore<User> userDataStore;
    private IServiceLogger serviceLogger;

    public UserManager(IServiceLogger serviceLogger, IDataStore<User> userDataStore) {
        this.serviceLogger = serviceLogger;
        this.userDataStore = userDataStore;
    }

    /**
     * Create user
     * @param name
     */
    public void createUser(String name) {
        serviceLogger.logInfo(String.format("Create user with name %s Initailized", name), Color.ANSI_YELLOW);
        if(userDataStore.get(name) != null) {
            serviceLogger.logError(String.format("User with name %s already exists", name));
            return;
        }
        userDataStore.create(name, new User(name));
        serviceLogger.logInfo(String.format("User %s created successfully", name));
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
