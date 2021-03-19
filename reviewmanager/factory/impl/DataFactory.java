package reviewmanager.factory.impl;

import reviewmanager.datastore.*;
import reviewmanager.datastore.impl.*;
import reviewmanager.factory.IDataFactory;
import reviewmanager.model.*;

public class DataFactory implements IDataFactory{
    private IDataStore<User> userDataStore;
    private IDataStore<Movie> movieDataStore;
    private IDataStore<Review> reviewDataStore;
    public DataFactory() {
        userDataStore = new DataStore<User>();
        movieDataStore = new DataStore<Movie>();
        reviewDataStore = new DataStore<Review>();
    }
    @Override
    public IDataStore<User> getUserDataStore() {
        return userDataStore;
    }
    @Override
    public IDataStore<Movie> getMoviewDataStore() {
        return movieDataStore;
    }
    @Override
    public IDataStore<Review> getReviewDataStore() {
        return reviewDataStore;
    }
}
