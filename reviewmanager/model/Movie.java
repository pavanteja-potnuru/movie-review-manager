package reviewmanager.model;

import java.time.LocalDate;
import java.util.*;

public class Movie {
    private String name;
    private float rating;
    private long totalWeightage;
    private LocalDate releaseDate;
    private Set<Genre> genres;

    /**
     * Constructor
     * @param name
     */
    public Movie(String name, LocalDate releaseDate, HashSet<Genre> genres) {
        this.name = name;
        this.rating = 0;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.totalWeightage = 0;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public float addRating(int rating, int weightage) {
        this.rating = (float)(((double)(this.totalWeightage*this.rating) + (rating*weightage)) / (this.totalWeightage+weightage));
        this.totalWeightage += weightage;
        return this.rating;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
