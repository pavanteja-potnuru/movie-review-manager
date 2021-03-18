package reviewmanager.factory;

import reviewmanager.services.*;

public interface IServiceFactory {
    
    public IUserManager getUserManager();
    public IMovieManager getMovieManager();
    public IReviewManager getReviewManager();
}
