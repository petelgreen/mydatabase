package db;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class tableBasic{

    public <T> LinkedHashMap<String,T> createMapWithKeys(List<String> keys) {
        LinkedHashMap map = new LinkedHashMap();
        for (String s: keys) {
            map.put(s, null);
        }
        return map;
    }
}
