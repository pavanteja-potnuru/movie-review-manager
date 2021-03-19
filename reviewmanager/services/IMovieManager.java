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
     * Get average rating
     * @param movieName
     * @return average rating
     */
    public float getAverageRating(String movieName);

    /**
     * Return top rated n movies.
     * If two movies have same rating latest released movie is preferred. 
     * @param n
     * @return names of top rated n movies
     */
    public List<String> getTopNRatedMovies(int n);
    
    /**
     * print movies
     * @param name
     */
    public void printMovies();
}
