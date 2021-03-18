package reviewmanager.datastore;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import reviewmanager.model.*;

public class DataStore<T> implements IDataStore<T> {
    private HashMap<String, T> collection;

    public DataStore() {
        collection = new HashMap<String, T>();
    }
    
    /**
     * Create Object inside dataStore
     * @param name
     * @throws ServiceException
     */
    public void create(String name, T t) {
        collection.put(name, t);
    }

    /**
     * Get object from datastore
     * @param name
     * @return
     */
    public T get(String name) {
        return collection.get(name);
    }

    /**
     * Get all objects as Stream
     * @return
     */
    public Stream<HashMap.Entry<String,T>> getCollectionStream() {
        return collection.entrySet().stream();
    }

    
}
