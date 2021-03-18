import reviewmanager.model.ServiceException;
import reviewmanager.services.*;

/**
 * Movie review manager class
 */
public class MovieReviewManager {
    public static void main(String[] args) {
        try{
            UserManager userManager = new UserManager();
            userManager.createUser("Pavan");
            userManager.createUser("Teja");
            userManager.createUser("Pavan Teja");
            userManager.createUser("Teja");
            System.out.println("Users: ");
            userManager.printUsers();
        }
        catch (ServiceException ex) {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}