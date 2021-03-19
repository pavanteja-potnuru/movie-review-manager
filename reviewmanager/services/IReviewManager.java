package reviewmanager.services;

import java.util.List;

public interface IReviewManager {

    /**
     * Add review
     * @param userName
     * @param movieName
     * @param rating
     */
    public void addReview(String userName, String movieName, int rating);

    /**
     * List top n movies by role in a particular genre.
     * @param n
     * @param byRole
     * @param inGenre
     * @return
     */
    public List<String> topNMoviesWithRoleGenre(int n, String byRole, String inGenre);
    
}
