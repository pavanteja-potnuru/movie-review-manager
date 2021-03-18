package reviewmanager.datastore;

import java.util.HashMap;
import java.util.stream.Stream;

public interface IDataStore<T> {
    /**
     * Create Object inside dataStore
     * @param name
     */
    public void create(String name, T t);

    /**
     * Get object from datastore
     * @param name
     * @return
     */
    public T get(String name);

    /**
     * Get all objects as Stream
     * @return
     */
    public Stream<HashMap.Entry<String,T>> getCollectionStream();
}
