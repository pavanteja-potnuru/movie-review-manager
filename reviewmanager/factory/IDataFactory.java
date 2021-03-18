package reviewmanager.factory;

import reviewmanager.datastore.IDataStore;
import reviewmanager.model.*;

public interface IDataFactory {
    public IDataStore<User> getUserDataStore();
    public IDataStore<Movie> getMoviewDataStore();
    public IDataStore<Review> getReviewDataStore();
}
