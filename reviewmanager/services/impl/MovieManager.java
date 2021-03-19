package reviewmanager.services.impl;

import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;
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
    public void addMovie(String name, LocalDate releaseDate, List<String> genreStrings) {
        try{
            serviceLogger.logInfo(String.format("Create movie with name %s Initailized", name), Color.ANSI_YELLOW);
            validateInput(name);
            HashSet<Genre> genres = (HashSet<Genre>)genreStrings.stream().map((genre) -> {return Genre.valueOf(genre.toUpperCase());}).collect(Collectors.toSet());
            movieDataStore.createOrUpdate(name, new Movie(name, releaseDate, genres));
            serviceLogger.logInfo(String.format("Movie %s created successfully", name));
        }
        catch(ServiceException ex) {
            serviceLogger.logError(ex.getMessage());
        }
    }

    /**
     * print movies
     * @param name
     * @return
     */
    public void printMovies() {
        movieDataStore.getCollectionStream().forEach((userkvp) -> {
            System.out.println(userkvp.getKey()+" :\t"+Double.toString(userkvp.getValue().getRating()));
        });
    }


    public float getAverageRating(String movieName) {
        return movieDataStore.get(movieName).getRating();
    }

    public List<String> getTopNRatedMovies(int n) {
        if(movieDataStore.getCount() <= n) {
            return movieDataStore.getCollectionStream().map(movieObject ->  movieObject.getValue().getName()).collect(Collectors.toList());
        }

        return new ArrayList<String>(movieDataStore.getCollectionStream()
        .collect(topRatedMoviesCollector(n)));
    }

//#region private

    private void validateInput(String name) throws ServiceException {
        if(movieDataStore.get(name) != null) {
            throw new ServiceException(String.format("Movie with name %s already exists", name));
        }
    }

    private Collector<Entry<String, Movie>,?, Collection<String>> topRatedMoviesCollector(int n) {
        return Collector.of(
                () -> new TreeMap<Float, String>(),
                (tm, e) -> { 
                    if(tm.size() == n && tm.firstKey() < e.getValue().getRating())
                        tm.pollFirstEntry();
                    if(tm.size() < n  || tm.firstKey() < e.getValue().getRating())
                        tm.put(e.getValue().getRating(), e.getValue().getName());
                },
                (tm1, tm2) -> {
                    return combineTwoTreeMaps(tm1, tm2, n);
                },
                tm -> tm.values()
               );
    }

    private TreeMap<Float, String> combineTwoTreeMaps(TreeMap<Float, String> tm1, TreeMap<Float, String> tm2, int n) {
        tm2.forEach((key, value) -> {
            if(tm1.size() == n && tm1.firstKey() < key)
                tm1.pollFirstEntry();
            if(tm1.size() < n  || tm1.firstKey() < key)
                tm1.put(key, value);
        });

        return tm1;
    }
//#endregion private
}
