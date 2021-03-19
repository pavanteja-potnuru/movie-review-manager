package reviewmanager.services;

public interface IReviewManager {

    /**
     * Add review
     * @param userName
     * @param movieName
     * @param rating
     */
    public void addReview(String userName, String movieName, int rating);

    public double averageReview(String movieName);

    public void printMovies(String byRole, String inGenre);
    
}
