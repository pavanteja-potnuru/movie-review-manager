import reviewmanager.services.*;

/**
 * Movie review manager class
 */
public class MovieReviewManager {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        userManager.createUser("Pavan");
        userManager.createUser("Teja");
        userManager.createUser("Pavan Teja");

        userManager.printUsers();
    }
}