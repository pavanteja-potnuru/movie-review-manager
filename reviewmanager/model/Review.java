package reviewmanager.model;

import java.time.LocalDate;

public class Review {
    private String movieName;
    private String userName;
    private int rating;
    private Role userRole;
    private LocalDate reviewDate;
    public Review(String movieName, String userName, int rating, Role userRole, LocalDate reviewDate) {
        this.movieName = movieName;
        this.userName = userName;
        this.rating = rating*(userRole.getWeightage());
        this.userRole = userRole;
        this.reviewDate = reviewDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getUserName() {
        return userName;
    }

    public int getRating() {
        return rating;
    }

    public Role getUserRole() {
        return userRole;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }
}
