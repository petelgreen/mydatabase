package reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.util.*;

public class FromDisk {

    private String tableName;
    private List<String> keys;

    public FromDisk(String tableName, List<String> keys) {
        this.keys = keys;
        this.tableName = tableName;
    }

    public <T> LinkedHashMap readByMainKey(T mk) {
        String filePath =  "C:\\Users\\yoles\\Desktop\\petel\\db\\" + tableName + "\\" + keys.get(0);
        File file = new File(filePath);
        LinkedHashMap<String, T> data = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           data = objectMapper.readValue(file, LinkedHashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public<T> List<String> readByKey(T key) {
        String path = "C:\\Users\\yoles\\Desktop\\petel\\db\\" + tableName + "\\" + key;
        File file = new File(path);
        List<String> data = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.readLine() != null) {
               String[] cols = br.readLine().split(",");
               data.add(cols[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public<T> HashMap<String, String> readByKeyWithMK(T key) {
        String path = "C:\\Users\\yoles\\Desktop\\petel\\db\\" + tableName + "\\" + key;
        File file = new File(path);
        HashMap<String, String> data = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.readLine() != null) {
                String[] cols = br.readLine().split(",");
                data.put(cols[1], cols[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public<T> void deleteFromAll(T mk) {
        LinkedHashMap<String, T> toDelete = readByMainKey(mk);
        String path = "C:\\Users\\yoles\\Desktop\\petel\\db\\" + tableName + "\\";
        for (int i = 1; i < keys.size(); i++) {
            HashMap<String, String> originData = readByKeyWithMK(keys.get(i));
            originData.remove(toDelete.get(keys.get(i)));
            (new File(path + keys.get(i))).delete();
            File file = new File(path + keys.get(i));
            try {
                Writer writer = new FileWriter(path + keys.get(i));
                for (Map.Entry<String, String> entry : originData.entrySet()) {
                    writer.append(entry.getKey())
                            .append(',')
                            .append(entry.getValue())
                            .append(",");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public<T> void deleteFromMainFolder(T mk) {
        String path = "C:\\Users\\yoles\\Desktop\\petel\\db\\" + tableName+ "\\" + keys.get(0) + "\\" + (String) mk;
        File file = new File(path);
        file.delete();
    }
}
