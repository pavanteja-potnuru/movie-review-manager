package reviewmanager.services;

import reviewmanager.datastore.*;
import reviewmanager.model.*;
import reviewmanager.utils.*;
public class UserManager {
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
        serviceLogger.logInfo(String.format("Create user with name %s Initailized", name));
        if(userDataStore.get(name) != null) {
            serviceLogger.logError(String.format("Create user with name %s Initailized", name));
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
