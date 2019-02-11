package com.aqacourses.test.student;

import com.aqacourses.test.interfaces.WriteToDbInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StudentNki extends Student implements WriteToDbInterface {

    private FileWriter fileWriter;
    private PrintWriter printWriter;

    /**
     * Write student data to MySQL Db
     *
     * @param data
     */
    @Override
    public void writeToDb(List<String> data) {
        try {
            openConnectionToDb();
            if (validateData(data)) {
                for (String datum : data) {
                    printWriter.println(getDate() + " - " + datum);
                }
                printWriter.print("=====================\n");
                System.out.println("All data is written to MySQL DB");
                closeConnectionToDb();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open connection to MySQL DB
     */
    private void openConnectionToDb() throws IOException {
        String path = "./src/com/aqacourses/test/DBFiles/MySQL-DB.txt";
        fileWriter = new FileWriter(path);
        printWriter = new PrintWriter(fileWriter);
    }

    /**
     * CLose connection to MySQL DB
     */
    private void closeConnectionToDb() throws IOException {
        printWriter.close();
        fileWriter.close();
        System.out.println("Close connection to MySQL DB");
    }
}
