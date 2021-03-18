package reviewmanager.services.impl;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Map.Entry;

import reviewmanager.datastore.IDataStore;
import reviewmanager.factory.IDataFactory;
import reviewmanager.model.*;
import reviewmanager.services.IReviewManager;
import reviewmanager.utils.IServiceLogger;

public class ReviewManager implements IReviewManager {
    private IDataStore<Review> reviewDataStore;
    private IServiceLogger serviceLogger;
    private IDataStore<User> userDataStore;
    private IDataStore<Movie> movieDataStore;
    public ReviewManager(IServiceLogger serviceLogger, IDataFactory dataFactory) {
        this.serviceLogger = serviceLogger;
        reviewDataStore = dataFactory.getReviewDataStore();
        userDataStore = dataFactory.getUserDataStore();
        movieDataStore = dataFactory.getMoviewDataStore();
    }

    public void addReview(String userName, String movieName, int rating) {
        try{
            serviceLogger.logInfo(String.format("Initialized: Add movie(%s) review by user %s", movieName, userName), Color.ANSI_YELLOW);

            validateInput(userName, movieName);    
            User user = userDataStore.get(userName);
            if(user == null) {
                throw new ServiceException(String.format("User with name %s doesn't exist", userName));
            }

            // TODO: instead of taking count as id generate hash combining movieName and userName.
            // To add feasability for remove movie functionality
            // Add review
            reviewDataStore.createOrUpdate(String.valueOf(reviewDataStore.getCount()), new Review(movieName, userName, rating, user.getRole(), LocalDate.now()));

            // Update user review count and update datastore
            user.incrementReviewCount();
            userDataStore.createOrUpdate(userName, user);

            serviceLogger.logInfo(String.format("Completed: Add movie(%s) review by user %s", movieName, userName));
        }
        catch(ServiceException ex) {
            serviceLogger.logError(ex.getMessage());
        }
    }

    private void validateInput(String userName, String movieName) throws ServiceException {

        // TODO: change strategy of storing key/id in datastore for review collection
        // Calculate hash combining userName and movieName. to make get call simpler. 
        Entry<String, Review> reviewObj = reviewDataStore.getCollectionStream()
        .filter(reviewItem -> Objects.equals(reviewItem.getValue().getMovieName(), movieName) && Objects.equals(reviewItem.getValue().getUserName(), userName)) 
        .findFirst().orElse(null);
        if(reviewObj != null) {
            throw new ServiceException(String.format("User %s already reviewed Movie(%s)", userName, movieName));
        }

        Movie movie = movieDataStore.get(movieName);
        if(movie == null) {
            throw new ServiceException(String.format("Movie with name %s doesn't exist", movieName));
        }

        if(movie.getReleaseDate().compareTo(LocalDate.now()) > 0) {
            throw new ServiceException(String.format("You can not review Movie(%s) that is not released yet", movieName));
        }
    }
}
