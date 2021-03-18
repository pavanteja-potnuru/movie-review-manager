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
    void addMovie(String string, LocalDate of, List<String> asList);
    
    /**
     * print movies
     * @param name
     * @return
     */
    public void printMovies();
}
