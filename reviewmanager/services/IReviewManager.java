package reviewmanager.services;

public interface IReviewManager {

    /**
     * Add review
     * @param userName
     * @param movieName
     * @param rating
     */
    public void addReview(String userName, String movieName, int rating);

    /**
     * print all movies that match given role and genre
     * @param byRole
     * @param inGenre
     */
    public void printMovies(String byRole, String inGenre);
    
}
