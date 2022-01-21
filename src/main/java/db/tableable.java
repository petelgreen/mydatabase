package db;

import java.util.List;

public interface tableable<T> {

    void print();
    <T> void insert(List<T> values);
    <T> void delete(List<T> values);
    <T> void delete(T mainKey);
    <T> void deleteAllByValue(String key, T value);
    <T> void deleteAllByValues(List<String> keys, List<T> values);
    <T> void updateAllByValue(T oldValue, T newValue);
    <T> void update(T mainKey, String keyToUpdate, T value);
}
