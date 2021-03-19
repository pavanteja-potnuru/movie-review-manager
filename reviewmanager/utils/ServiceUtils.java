package reviewmanager.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collector;

import reviewmanager.model.*;

public class ServiceUtils {
    private static class Box {
        float num = 0;
        long denom = 0;
        Box(int num,int denom) {
            this.num = num;
            this.denom = denom;
        }
    }
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
                () -> new HashMap<String, Box>(),
                (hMap, e) -> { 
                    final Review review = e.getValue();
                    if(hMap.containsKey(review.getMovieName())) {
                        final Box box = hMap.get(review.getMovieName());
                        box.num +=  review.getRating() * review.getUserRole().getWeightage(); 
                        box.denom += review.getUserRole().getWeightage();
                        hMap.put(review.getMovieName(), box);
                    } else {
                        hMap.put(review.getMovieName(),
                            new Box(review.getRating() * review.getUserRole().getWeightage(), review.getUserRole().getWeightage()));
                    }
                },
                (hMap1, hMap2) -> {
                    hMap2.forEach((key, value) -> {
                        if(hMap1.containsKey(key)){
                            Box box1 = hMap1.get(key);
                            box1.num += value.num;
                            box1.denom += value.denom;
                            hMap1.put(key, box1);
                        }
                        else {
                            hMap1.put(key, value);
                        }
                    });
            
                    return hMap1;
                },
                mrMap -> {
                    TreeMap<Float, String> tmap = new TreeMap<Float, String>();
                    mrMap.forEach(
                    (key, value)-> {
                        float rating = (Float)(value.num)/value.denom;
                        if(tmap.size() == n && tmap.firstKey() < rating)
                            tmap.pollFirstEntry();
                        if(tmap.size() < n  || tmap.firstKey() < rating)
                            tmap.put(rating, key);
                    });

                    return tmap.values();
                }
                );
    }
}
