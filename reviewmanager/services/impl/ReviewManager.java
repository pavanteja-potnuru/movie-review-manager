package reviewmanager.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import reviewmanager.datastore.IDataStore;
import reviewmanager.factory.IDataFactory;
import reviewmanager.model.*;
import reviewmanager.services.IReviewManager;
import reviewmanager.utils.IServiceLogger;
import reviewmanager.utils.ServiceUtils;

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
     * List top n movies by role in a particular genre.
     */
    public List<String> topNMoviesWithRoleGenre(int n, String byRole, String inGenre) {
        try{
            validateInput(byRole, inGenre);
            return new ArrayList<String>(reviewDataStore.getCollectionStream()
            .filter(reviewItem -> Objects.equals(reviewItem.getValue().getUserRole(), Role.valueOf(byRole)))
            .filter(reviewItem -> movieDataStore.get(reviewItem.getValue().getMovieName()).getGenres().contains(Genre.valueOf(inGenre.toUpperCase())))
            .collect(ServiceUtils.topRatedMoviesWithRolenGenre(n)));
        }
        catch (ServiceException ex) {
            serviceLogger.logError(ex.getMessage());
        }
        return null;
    }

    public float getAverageReview(String userName, int year) {
        return reviewDataStore.getCollectionStream()
        .filter(reviewItem -> Objects.equals(reviewItem.getValue().getUserName(), userName))
        .filter(reviewItem -> Objects.equals(movieDataStore.get(reviewItem.getValue().getMovieName()).getReleaseDate().getYear(), year))
        .collect(ServiceUtils.averagingWeighted());
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

    private void validateInput(String byRole, String inGenre) throws ServiceException {

        // Validate Role
        if(Role.valueOf(byRole.toLowerCase()) == null) {
            throw new ServiceException(String.format("Invalid Role: %s", byRole));
        }

        // Validate Role
        if(Genre.valueOf(inGenre.toUpperCase()) == null) {
            throw new ServiceException(String.format("Invalid Genre: %s\n. Please find available values: %s", inGenre, Genre.values().toString()));
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
