package reviewmanager.model;

import java.time.LocalDate;
import java.util.*;

public class Movie {
    private String name;
    private LocalDate releaseDate;
    private Set<Genre> genres;

    /**
     * Constructor
     * @param name
     */
    public Movie(String name, LocalDate releaseDate, HashSet<Genre> genres) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
