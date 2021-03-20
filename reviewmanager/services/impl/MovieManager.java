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
    public void addMovie(String name, LocalDate releaseDate, List<String> genreStrings) {
        try{
            serviceLogger.logInfo(String.format("Create movie with name %s Initailized", name), Color.ANSI_YELLOW);
            HashSet<Genre> genres = validateInput(name, genreStrings);
            movieDataStore.createOrUpdate(name, new Movie(name, releaseDate, genres));
            serviceLogger.logInfo(String.format("Movie %s created successfully", name));
        }
        catch(ServiceException ex) {
            serviceLogger.logError(ex.getMessage());
        }
        catch(Exception ex) {
            serviceLogger.logError(ex.getMessage(), Color.ANSI_RED);
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
        .collect(ServiceUtils.topRatedMoviesCollector(n)));
    }

//#region private

    private HashSet<Genre> validateInput(String name, List<String> genreStrings) throws ServiceException {
        if(movieDataStore.get(name) != null) {
            throw new ServiceException(String.format("Movie with name %s already exists", name));
        }

        return (HashSet<Genre>)genreStrings.stream().map((genreStr) -> {
            return Genre.valueOf(genreStr.toUpperCase());
        }).collect(Collectors.toSet());
    }
    
//#endregion private
}
