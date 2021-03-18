package reviewmanager.model;

import java.time.LocalDate;
import java.util.*;

public class Movie {
    private String name;
    private float rating;
    private int noOfRatings;
    private LocalDate releaseDate;
    private Set<Genre> genres;

    /**
     * Constructor
     * @param name
     */
    public Movie(String name, LocalDate releaseDate, HashSet<Genre> genres) {
        this.name = name;
        this.rating = 0;
        this.noOfRatings = 0;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public int getNoOfRatings() {
        return noOfRatings;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
