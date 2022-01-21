package maneger;

import db.Table;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface mange {
    Table create(String tableName, List<String> keys);
    void delete(String tableName);


}
