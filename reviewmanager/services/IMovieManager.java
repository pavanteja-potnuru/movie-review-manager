package reviewmanager.services;

import java.time.LocalDate;
import java.util.List;

public interface IMovieManager {

    /**
     * Create movie
     * @param name
     * @param releaseDate
     * @param genere
     */
    public void addMovie(String string, LocalDate of, List<String> asList);
    
    /**
     * print movies
     * @param name
     */
    public void printMovies();
}
