package maneger;

import db.Table;

import java.io.File;
import java.util.*;

public class TablesManger implements mange {
    private HashMap<String, Table> tables;

    public TablesManger() {
        tables = new HashMap<>();
    }

    @Override
    public Table create(String tableName, List<String> keys) {
        String filePath =  "C:\\Users\\yoles\\Desktop\\petel\\db" + tableName;
        File file = new File(filePath);
        Table table = new Table(keys, tableName);
        this.tables.put(tableName, table);
        return table;
    }

    @Override
    public void delete(String tableName) {
        File file = new File("C:\\Users\\yoles\\Desktop\\petel\\db" + tableName);
        file.delete();
        this.tables.remove(tableName);
    }
}
