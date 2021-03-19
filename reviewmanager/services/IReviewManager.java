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
     * Get average review by moviename
     * @param movieName
     * @return average review
     */
    public double averageReview(String movieName);

    /**
     * print all movies that match given role and genre
     * @param byRole
     * @param inGenre
     */
    public void printMovies(String byRole, String inGenre);
    
}
