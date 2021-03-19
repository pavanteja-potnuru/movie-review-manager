package reviewmanager.services.impl;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collector;

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

            validateInput(userName, movieName, rating);

            User user = userDataStore.get(userName);
            // Create review with userName and movieName given
            reviewDataStore.createOrUpdate(generateReviewId(movieName, userName), new Review(movieName, userName, rating, user.getRole(), LocalDate.now()));

            // Update user review count and update datastore
            user.incrementReviewCount();
            userDataStore.createOrUpdate(userName, user);

            serviceLogger.logInfo(String.format("Completed: Add movie(%s) review by user %s", movieName, userName));
        }
        catch(ServiceException ex) {
            serviceLogger.logError(ex.getMessage());
        }
    }

    public double averageReview(String movieName) {
        return reviewDataStore.getCollectionStream()
        .filter(reviewItem -> Objects.equals(reviewItem.getValue().getMovieName(), movieName))
        .collect(averagingWeighted());
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
    private void validateInput(String userName, String movieName, int rating) throws ServiceException {

        // Check whether rating is valid or not
        if(rating < 0 || rating >10 ) {
            throw new ServiceException(String.format("Rating is out of range: should be 1 to 10", userName, movieName));
        }

        // validate whether a user with name exists or not
        if(userDataStore.get(userName) == null) {
            throw new ServiceException(String.format("User with name %s doesn't exist", userName));
        }

        // validate whether a movie with name exists or not
        Movie movie = movieDataStore.get(movieName);
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
    private Collector<Entry<String, Review>,?,Double> averagingWeighted() {
        class Box {
            double num = 0;
            long denom = 0;
        }
        return Collector.of(
                 Box::new,
                 (b, e) -> { 
                     b.num +=  e.getValue().getRating() * e.getValue().getUserRole().getWeightage(); 
                     b.denom += e.getValue().getUserRole().getWeightage();
                 },
                 (b1, b2) -> { b1.num += b2.num; b1.denom += b2.denom; return b1; },
                 b -> b.num / b.denom
               );
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
