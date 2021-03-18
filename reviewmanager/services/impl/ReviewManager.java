package reviewmanager.services.impl;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Map.Entry;

import reviewmanager.datastore.IDataStore;
import reviewmanager.model.*;
import reviewmanager.services.IReviewManager;
import reviewmanager.utils.IServiceLogger;

public class ReviewManager implements IReviewManager {
    private IDataStore<Review> reviewDataStore;
    private IServiceLogger serviceLogger;
    private IDataStore<User> userDataStore;
    public ReviewManager(IServiceLogger serviceLogger, IDataStore<Review> reviewDataStore, IDataStore<User> userDataStore) {
        this.serviceLogger = serviceLogger;
        this.reviewDataStore = reviewDataStore;
        this.userDataStore = userDataStore;
    }

    public void addReview(String userName, String movieName, int rating) {
        serviceLogger.logInfo(String.format("Initialized: Add movie(%s) review by user %s", movieName, userName), Color.ANSI_YELLOW);
        if(getReview(userName, movieName) != null) {
            serviceLogger.logError(String.format("User %s already reviewed Movie(%s)", userName, movieName));
            return;
        }
        try {
            User user = userDataStore.get(userName);
            reviewDataStore.create(String.valueOf(reviewDataStore.getCount()), new Review(movieName, userName, rating, user.getRole(), LocalDate.now()));
            serviceLogger.logInfo(String.format("Completed: Add movie(%s) review by user %s", movieName, userName));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Review getReview(String userName, String movieName) {
        Entry<String, Review> reviewObj = reviewDataStore.getCollectionStream()
        .filter(reviewItem -> Objects.equals(reviewItem.getValue().getMovieName(), movieName) && Objects.equals(reviewItem.getValue().getUserName(), userName)) 
        .findFirst().orElse(null);
        if(reviewObj == null) {
            return null;
        }
        return reviewObj.getValue();
    }
}
