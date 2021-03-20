package reviewmanager.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collector;

import reviewmanager.model.*;

public class ServiceUtils {
    public static Collector<Entry<String, Movie>,?, Collection<String>> topRatedMoviesCollector(int n) {
        return Collector.of(
                () -> new TreeMap<Float, String>(),
                (tm, e) -> { 
                    if(tm.size() == n && tm.firstKey() < e.getValue().getRating())
                        tm.pollFirstEntry();
                    if(tm.size() < n  || tm.firstKey() < e.getValue().getRating())
                        tm.put(e.getValue().getRating(), e.getValue().getName());
                },
                (tm1, tm2) -> {
                    tm2.forEach((key, value) -> {
                        if(tm1.size() == n && tm1.firstKey() < key)
                            tm1.pollFirstEntry();
                        if(tm1.size() < n  || tm1.firstKey() < key)
                            tm1.put(key, value);
                    });
            
                    return tm1;
                },
                tm -> tm.values()
               );
    }

    public static Collector<Entry<String, Review>, ?, Collection<String>> topRatedMoviesWithRolenGenre(int n) {
        
        return Collector.of(
                () -> new HashMap<String, Integer>(),
                (hMap, e) -> { 
                    final Review review = e.getValue();
                    if(hMap.containsKey(review.getMovieName())) {
                        hMap.put(review.getMovieName(), hMap.get(review.getMovieName())+review.getRating());
                    } else {
                        hMap.put(review.getMovieName(), review.getRating());
                    }
                },
                (hMap1, hMap2) -> {
                    hMap2.forEach((key, value) -> {
                        if(hMap1.containsKey(key)){
                            hMap1.put(key, hMap1.get(key)+value);
                        }
                        else {
                            hMap1.put(key, value);
                        }
                    });
            
                    return hMap1;
                },
                mrMap -> {
                    TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
                    mrMap.forEach(
                    (key, value)-> {
                        if(tmap.size() == n && tmap.firstKey() < value)
                            tmap.pollFirstEntry();
                        if(tmap.size() < n  || tmap.firstKey() < value)
                            tmap.put(value, key);
                    });

                    return tmap.values();
                }
                );
    }

    public static Collector<Entry<String, Review>,?,Float> averagingWeighted() {
        class Box {
            float num = 0;
            long denom = 0;
        }
        return Collector.of(
                 Box::new,
                 (b, e) -> { 
                     b.num +=  e.getValue().getRating() * e.getValue().getUserRole().getWeightage(); 
                     b.denom += e.getValue().getUserRole().getWeightage();
                 },
                 (b1, b2) -> { b1.num += b2.num; b1.denom += b2.denom; return b1; },
                 b -> b.num / b.denom
               );
    }
}
