package reviewmanager.services;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import reviewmanager.datastore.*;
import reviewmanager.model.*;
import reviewmanager.utils.*;

public class MovieManager {
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
    public void createMovie(String name, LocalDate releaseDate, List<String> genere) {
        serviceLogger.logInfo(String.format("Create movie with name %s Initailized", name));
        if(movieDataStore.get(name) != null) {
            serviceLogger.logError(String.format("Create user with name %s Initailized", name));
            return;
        }
        movieDataStore.create(name, new Movie(name, releaseDate, (HashSet<String>) genere.stream().collect(Collectors.toSet())));
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
