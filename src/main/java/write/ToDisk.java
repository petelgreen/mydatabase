package write;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ToDisk<T> {

    private String tableName;
    private List<String> keys;
    private List<T> data;

    public ToDisk(String tableName, List<String> keys, List<T> data) {
        this.tableName = tableName;
        this.data = data;
        this.keys = keys;
    }

    public <T> void write() {
        String path = "C:\\Users\\yoles\\Desktop\\petel\\db\\USERS\\" + keys.get(0);
        File file = new File(path + (String) data.get(0));
        JSONObject jsonObject = new JSONObject();
        for (int i = 1; i < keys.size(); i++) {
            jsonObject.put(keys.get(i), data.get(i));
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvWrite();
    }

    private  <T> void csvWrite() {
        String path = "C:\\Users\\yoles\\Desktop\\petel\\db\\" + tableName + "\\";
        File file;
        String[] s = new String[2];
        for (int i = 1; i < keys.size(); i++) {
            try (FileWriter outputfile = new FileWriter(path + keys.get(i)) ){
                CSVWriter writer = new CSVWriter(outputfile);
                s[0] = (String) data.get(i);
                s[1] = (String) data.get(0);
                writer.writeNext(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

}
