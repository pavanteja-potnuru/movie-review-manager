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
            userManager.addUser("Pavan");
            userManager.addUser("Yesh");
            userManager.addUser("Harsh");
            userManager.addUser("Srinivas");
            userManager.addUser("Harsh");

            movieManager.addMovie("Bahubali1", LocalDate.of(2015, 7, 10), Arrays.asList("action", "fantasy"));
            movieManager.addMovie("Bahubali2", LocalDate.of(2017, 4, 28), Arrays.asList("action", "fantasy"));
            movieManager.addMovie("Don", LocalDate.of(2006, 7, 10), Arrays.asList("action", "comedy"));
            movieManager.addMovie("Bahubali2", LocalDate.of(2017, 4, 28), Arrays.asList("action", "fantasy"));
            movieManager.addMovie("Tiger", LocalDate.of(2008, 1, 1), Arrays.asList("Drama"));
            movieManager.addMovie("Padmaavat", LocalDate.of(2008, 1, 1), Arrays.asList("Comedy"));
            movieManager.addMovie("Lunchbox", LocalDate.of(2008, 1, 1), Arrays.asList("Drama"));
            movieManager.addMovie("Guru", LocalDate.of(2008, 1, 1), Arrays.asList("Drama"));
            movieManager.addMovie("Metro", LocalDate.of(2008, 1, 1), Arrays.asList("Romance"));
            movieManager.addMovie("Saina", LocalDate.of(2021, 03, 26), Arrays.asList("Sport"));

            reviewManager.addReview("Pavan", "Bahubali1", 10);
            reviewManager.addReview("Pavan", "Padmaavat", 10);
            reviewManager.addReview("Pavan", "Guru", 10);
            reviewManager.addReview("Pavan", "Lunchbox", 10);
            reviewManager.addReview("Pavan", "Bahubali2", 10);
            reviewManager.addReview("Pavan", "Metro", 10);
            reviewManager.addReview("Pavan", "Saina", 10);
            
            reviewManager.addReview("Yesh", "Bahubali1", 9);
            reviewManager.addReview("Yesh", "Bahubali2", 8);
            reviewManager.addReview("Yesh", "Padmaavat", 8);
            reviewManager.addReview("Harsh", "Guru", 10);
            reviewManager.addReview("Harsh", "Lunchbox", 9);
            reviewManager.addReview("Harsh", "Metro", 7);
            reviewManager.addReview("Harsh", "Bahubali2", 10);
        }
        catch (Exception ex) {
            serviceLogger.logError(ex.getMessage());
        }
        finally {
            //Validate responses
            System.out.println("------------------------/Print Logs\\------------------------");
            serviceLogger.printLogs();
            System.out.println("------------------------/\\------------------------");
            System.out.println("------------------------/Print Users\\------------------------");
            userManager.printUsers();
            System.out.println("------------------------/\\------------------------");
            System.out.println("------------------------/Print Movies\\------------------------");
            movieManager.printMovies();
            System.out.println("------------------------/\\------------------------");
            System.out.println("------------------------/Print Movies by role and in genre\\------------------------");
            reviewManager.printMovies("Critic", "DRAMA");;
            System.out.println("------------------------/\\------------------------");
            System.out.println("------------------------/Print average review\\------------------------");
            System.out.println(reviewManager.averageReview("Bahubali1"));
            System.out.println(reviewManager.averageReview("Bahubali2"));
            System.out.println("------------------------/\\------------------------");
        }
    }
}