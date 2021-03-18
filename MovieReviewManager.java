import java.time.LocalDate;
import java.util.*;
import reviewmanager.datastore.*;
import reviewmanager.model.*;
import reviewmanager.services.*;
import reviewmanager.services.impl.*;
import reviewmanager.utils.*;

/**
 * Movie review manager class
 */
public class MovieReviewManager {
    private static IServiceLogger serviceLogger;
    private static IDataStore<User> userDataStore;
    private static IDataStore<Movie> movieDataStore;
    private static IDataStore<Review> reviewDataStore;
    private static IUserManager userManager;
    private static IMovieManager movieManager;
    private static IReviewManager reviewManager;
    static {
        // utilities
        serviceLogger = new ServiceLogger();

        // datastores
        userDataStore = new DataStore<User>();
        movieDataStore = new DataStore<Movie>();
        reviewDataStore = new DataStore<Review>();

        // services
        userManager = new UserManager(serviceLogger, userDataStore);
        movieManager = new MovieManager(serviceLogger, movieDataStore);
        reviewManager = new ReviewManager(serviceLogger, reviewDataStore, userDataStore);
    }
    public static void main(String[] args) {
        try{
            userManager.createUser("Pavan");
            userManager.createUser("Yesh");
            userManager.createUser("Harsh");
            userManager.createUser("Srinivas");
            userManager.createUser("Harsh");

            movieManager.createMovie("Bahubali1", LocalDate.of(2015, 7, 10), Arrays.asList("action", "fantasy"));
            movieManager.createMovie("Bahubali2", LocalDate.of(2017, 4, 28), Arrays.asList("action", "fantasy"));

            reviewManager.addReview("Pavan", "Bahubali1", 10);
            reviewManager.addReview("Pavan", "Bahubali1", 10);
        }
        catch (Exception ex) {
            serviceLogger.logError(ex.getMessage());
        }
        finally {
            serviceLogger.printLogs();
        }
    }
}