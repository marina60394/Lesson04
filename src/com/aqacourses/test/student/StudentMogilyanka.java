package com.aqacourses.test.student;

import com.aqacourses.test.interfaces.WriteToDbInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentMogilyanka extends Student implements WriteToDbInterface {

    private File file;
    private BufferedWriter bufferedWriter;

    /**
     * Write student data to Postgres DB
     *
     * @param data
     */
    @Override
    public void writeToDb(List<String> data) {
        try {
            openConnection();
            if (validateData(data)) {
                for (String datum : data) {
                    bufferedWriter.write(getDate() + " - " + datum);
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("==================\n");
                System.out.println("All data is written to Postgres DB");
                closeConnection();
            }
        } catch (IOException e) {
            System.err.println("ERROR!!!");
            e.printStackTrace();
        }
    }

    /**
     * Open connection to Postgres DB
     */
    private void openConnection() throws IOException {
        String path = "./src/com/aqacourses/test/DBFiles/Postgres-DB.txt";
        file = new File(path);
        bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * Close connection to Postgres DB
     */
    private void closeConnection() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Cannot close connection to Postgres DB");
            e.printStackTrace();
        }
        System.out.println("Close connection to Postgres DB");
    }


}
