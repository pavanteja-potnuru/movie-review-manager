import java.time.LocalDate;
import java.util.*;
import reviewmanager.datastore.*;
import reviewmanager.model.*;
import reviewmanager.services.*;
import reviewmanager.utils.*;

/**
 * Movie review manager class
 */
public class MovieReviewManager {
    private static IServiceLogger serviceLogger;
    private static IDataStore<User> userDataStore;
    private static IDataStore<Movie> movieDataStore;
    static {
        serviceLogger = new ServiceLogger();
        userDataStore = new DataStore<User>();
        movieDataStore = new DataStore<Movie>();
    }
    public static void main(String[] args) {
        UserManager userManager = new UserManager(serviceLogger, userDataStore);
        MovieManager movieManager = new MovieManager(serviceLogger, movieDataStore);
        try{
            userManager.createUser("Pavan");
            userManager.createUser("Yesh");
            userManager.createUser("Harsh");
            userManager.createUser("Srinivas");
            userManager.createUser("Harsh");

            movieManager.createMovie("Bahubali1", LocalDate.of(2015, 7, 10), Arrays.asList("action", "fantasy"));
            movieManager.createMovie("Bahubali2", LocalDate.of(2017, 4, 28), Arrays.asList("action", "fantasy"));
        }
        catch (Exception ex) {
            serviceLogger.logError(ex.getMessage());
        }
        finally {
            serviceLogger.printLogs();
        }
    }
}