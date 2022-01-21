package db;

import reader.FromDisk;
import write.ToDisk;

import java.util.*;

public class Table<T> extends tableBasic {
    private FromDisk fromDisk;
    private List<String> keys;
    private String tableName;
    private List<T> mainKeys;

    public Table(List<String> keys, String tableName) {
        this.keys = keys;
        this.tableName = tableName;
        this.fromDisk = new FromDisk(tableName, keys);
        this.mainKeys = new ArrayList<>();
    }

    public void print() {
        for (T t: mainKeys) {
            System.out.println(this.fromDisk.readByMainKey(t));
        }
    }

    public void insert(List<T> values) {
        this.mainKeys.add(values.get(0));
      ToDisk toDisk = new ToDisk(this.tableName, this.keys, values);
      toDisk.write();
    }

    public void delete(T mk) {
        fromDisk.deleteFromMainFolder(mk);
        fromDisk.deleteFromAll(mk);
    }

    public void update(T mk, String[] keysToUpdate, T[] valuesToUpdate) {
        LinkedHashMap<String, T> data = fromDisk.readByMainKey(mk);
        for (int i = 0; i < keysToUpdate.length; i++) {
            data.put(keysToUpdate[i], valuesToUpdate[i]);
        }
        List<T> values = new ArrayList<>();
        for (Map.Entry<String, T> d : data.entrySet()) {
            values.add(d.getValue());

        }
        fromDisk.deleteFromMainFolder(mk);
        fromDisk.deleteFromAll(mk);
        insert(values);
    }


    public List<String> getAllByKey(String key) {
        return fromDisk.readByKey(key);
    }

    public LinkedHashMap<String, T> getByMainKey(T mk) {
        return fromDisk.readByMainKey(mk);
    }


}
