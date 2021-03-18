package reviewmanager.services.impl;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import reviewmanager.datastore.*;
import reviewmanager.model.*;
import reviewmanager.services.IMovieManager;
import reviewmanager.utils.*;

public class MovieManager implements IMovieManager{
    private IDataStore<Movie> movieDataStore;
    private IServiceLogger serviceLogger;

    public MovieManager(IServiceLogger serviceLogger, IDataStore<Movie> movieDataStore) {
        this.serviceLogger = serviceLogger;
        this.movieDataStore = movieDataStore;
    }

    /**
     * Create movie
     * @param name
     * @param releaseDate
     * @param genere
     */
    public void createMovie(String name, LocalDate releaseDate, List<String> genreStrings) {
        serviceLogger.logInfo(String.format("Create movie with name %s Initailized", name), Color.ANSI_YELLOW);
        if(movieDataStore.get(name) != null) {
            serviceLogger.logError(String.format("Create user with name %s Initailized", name));
            return;
        }
        HashSet<Genre> genres = (HashSet<Genre>)genreStrings.stream().map((genre) -> {return Genre.valueOf(genre.toUpperCase());}).collect(Collectors.toSet());
        movieDataStore.create(name, new Movie(name, releaseDate, genres));
        serviceLogger.logInfo(String.format("Movie %s created successfully", name));
    }

    /**
     * get movie by name
     * @param name
     * @return
     */
    public Movie getMovie(String name) {
        return movieDataStore.get(name);
    }

    /**
     * print movies
     * @param name
     * @return
     */
    public void printMovies() {
        movieDataStore.getCollectionStream().forEach((userkvp) -> {
            System.out.println(userkvp.getKey());
        });
    }
}
