import java.time.LocalDate;
import java.util.*;
import reviewmanager.factory.*;
import reviewmanager.factory.impl.*;
import reviewmanager.services.*;
import reviewmanager.utils.*;

/**
 * Movie review manager class
 */
public class MovieReviewManager {
    private static IServiceLogger serviceLogger;

    private static IDataFactory dataFactory;

    private static IUserManager userManager;
    private static IMovieManager movieManager;
    private static IReviewManager reviewManager;
    private static IServiceFactory serviceFactory;
    
    static {
        // utilities
        serviceLogger = new ServiceLogger();

        // datastores
        dataFactory = new DataFactory();

        // services
        serviceFactory = new ServiceFactory(serviceLogger, dataFactory);
        userManager = serviceFactory.getUserManager();
        movieManager = serviceFactory.getMovieManager();
        reviewManager = serviceFactory.getReviewManager();
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