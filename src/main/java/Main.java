import db.Table;
import maneger.TablesManger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TablesManger tablesManger = new TablesManger();
        List<String> myTableData = new ArrayList<>();
        myTableData.add("id");
        myTableData.add("first name");
        myTableData.add("last name");
        myTableData.add("city");
        Table t = tablesManger.create("myTable", myTableData);

    }
}
