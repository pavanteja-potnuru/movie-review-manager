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
    public void addUser(String name) {
        try {
            serviceLogger.logInfo(String.format("Create user with name %s Initailized", name), Color.ANSI_YELLOW);
            validateInput(name);
            userDataStore.createOrUpdate(name, new User(name));
            serviceLogger.logInfo(String.format("User %s created successfully", name));
        }
        catch (ServiceException ex) {
            serviceLogger.logError(ex.getMessage());
        }
    }

    /**
     * validate user by username
     * @param name
     * @return
     * @throws ServiceException
     */
    private void validateInput(String name) throws ServiceException {
        if(userDataStore.get(name) != null) {
            throw new ServiceException(String.format("User with name %s already exists", name));
        }
    }

    /**
     * Print all Users
     * @param name
     * @return
     */
    public void printUsers() {
        userDataStore.getCollectionStream().forEach((userkvp) -> {
            System.out.println(userkvp.getKey() + " " + userkvp.getValue().getRole());
        });
    }
}
