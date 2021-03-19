package reviewmanager.factory.impl;

import reviewmanager.factory.IDataFactory;
import reviewmanager.factory.IServiceFactory;
import reviewmanager.services.IMovieManager;
import reviewmanager.services.IReviewManager;
import reviewmanager.services.IUserManager;
import reviewmanager.services.impl.MovieManager;
import reviewmanager.services.impl.ReviewManager;
import reviewmanager.services.impl.UserManager;
import reviewmanager.utils.IServiceLogger;

public class ServiceFactory implements IServiceFactory{

    private IUserManager userManager;
    private IMovieManager movieManager;
    private IReviewManager reviewManager;

    public ServiceFactory(IServiceLogger serviceLogger, IDataFactory dataFactory) {
        userManager = new UserManager(serviceLogger, dataFactory.getUserDataStore());
        movieManager = new MovieManager(serviceLogger, dataFactory.getMoviewDataStore());
        reviewManager = new ReviewManager(serviceLogger, dataFactory);
    }

    @Override
    public IUserManager getUserManager() {
        return userManager;
    }

    @Override
    public IMovieManager getMovieManager() {
        return movieManager;
    }

    @Override
    public IReviewManager getReviewManager() {
        return reviewManager;
    }
    
}
