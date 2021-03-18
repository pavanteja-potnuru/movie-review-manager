package reviewmanager.services;

import reviewmanager.model.Review;

public interface IReviewManager {

    /**
     * Add review
     * @param userName
     * @param movieName
     * @param rating
     */
    public void addReview(String userName, String movieName, int rating);

    /**
     * Get review given by user to particular Movie
     * @param userName
     * @param movieName
     * @return
     */
    public Review getReview(String userName, String movieName);
    
}
