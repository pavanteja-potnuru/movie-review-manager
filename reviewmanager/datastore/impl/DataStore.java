package reviewmanager.datastore.impl;

import java.util.HashMap;
import java.util.stream.Stream;
import reviewmanager.datastore.IDataStore;

public class DataStore<T> implements IDataStore<T> {
    private HashMap<String, T> collection;

    public DataStore() {
        collection = new HashMap<String, T>();
    }
    
    /**
     * Create Object inside dataStore
     * @param id
     */
    public void create(String id, T t) {
        collection.put(id, t);
    }

    /**
     * Get object from datastore
     * @param id
     * @return
     */
    public T get(String id) {
        return collection.get(id);
    }

    /**
     * Get all objects as Stream
     * @return
     */
    public Stream<HashMap.Entry<String,T>> getCollectionStream() {
        return collection.entrySet().stream();
    }

    /**
     * get count
     * @return no of elements in datastore
     */
    public int getCount() {
        return collection.size();
    }
}
