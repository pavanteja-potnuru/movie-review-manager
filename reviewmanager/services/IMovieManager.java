package reviewmanager.services;

import java.time.LocalDate;
import java.util.List;

public interface IMovieManager {

    void createMovie(String string, LocalDate of, List<String> asList);
    
}
