package reviewmanager.datastore;

import java.util.HashMap;
import java.util.stream.Stream;

public interface IDataStore<T> {
    /**
     * Create Object inside dataStore
     * @param id
     */
    public void createOrUpdate(String id, T t);

    /**
     * Get object from datastore
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * Get all objects as Stream
     * @return
     */
    public Stream<HashMap.Entry<String,T>> getCollectionStream();

    /**
     * get count
     * @return no of elements in datastore
     */
    public int getCount();
}
