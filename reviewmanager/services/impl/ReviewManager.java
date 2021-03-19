package reviewmanager.services.impl;

import java.time.LocalDate;
import java.util.Objects;

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

    /**
     * Add review
     */
    public void addReview(String userName, String movieName, int rating) {
        try{
            serviceLogger.logInfo(String.format("Initialized: Add movie(%s) review by user %s", movieName, userName), Color.ANSI_YELLOW);

            
            Movie movie = movieDataStore.get(movieName);
            User user = userDataStore.get(userName);
            validateInput(user, movie, userName, movieName, rating);

            // Create review with userName and movieName given
            reviewDataStore.createOrUpdate(generateReviewId(movieName, userName), new Review(movieName, userName, rating, user.getRole(), LocalDate.now()));

            // Update user review count and update datastore
            user.incrementReviewCount();
            userDataStore.createOrUpdate(userName, user);

            // Update movie rating and update datastore
            movie.addRating(rating, user.getRole().getWeightage());
            movieDataStore.createOrUpdate(movieName, movie);

            serviceLogger.logInfo(String.format("Completed: Add movie(%s) review by user %s", movieName, userName));
        }
        catch(ServiceException ex) {
            serviceLogger.logError(ex.getMessage());
        }
    }

    /**
     * List movies by role in a particular genre.
     */
    public void printMovies(String byRole, String inGenre) {
        reviewDataStore.getCollectionStream()
        .filter(reviewItem -> Objects.equals(reviewItem.getValue().getUserRole(), Role.valueOf(byRole)))
        .filter(reviewItem -> movieDataStore.get(reviewItem.getValue().getMovieName()).getGenres().contains(Genre.valueOf(inGenre.toUpperCase())))
        .forEach(reviewItem -> System.out.println(reviewItem.getValue().getMovieName()));
    }

//#region private
    private void validateInput(User user, Movie movie, String userName, String movieName, int rating) throws ServiceException {

        // Check whether rating is valid or not
        if(rating < 0 || rating >10 ) {
            throw new ServiceException(String.format("Rating is out of range: should be 1 to 10", userName, movieName));
        }

        // validate whether a user with name exists or not
        if(user == null) {
            throw new ServiceException(String.format("User with name %s doesn't exist", userName));
        }

        // validate whether a movie with name exists or not
        if(movie == null) {
            throw new ServiceException(String.format("Movie with name %s doesn't exist", movieName));
        }
        // Check whther movie already released or not
        if(movie.getReleaseDate().compareTo(LocalDate.now()) > 0) {
            throw new ServiceException(String.format("You can not review Movie(%s) that is not released yet", movieName));
        }

        // check with movie with same reviewId exist or not
        if(reviewDataStore.get(generateReviewId(movieName, userName)) != null) {
            throw new ServiceException(String.format("User %s already reviewed Movie(%s)", userName, movieName));
        }

    }

    /**
     * generate movieId from movieName and userName
     * @param movieName
     * @param userName
     * @return
     */
    private String generateReviewId(String movieName, String userName) {
        return Integer.toString(movieName.length()) + "_" + movieName + userName;
    }
    
//#endregion private
}
