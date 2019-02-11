package com.aqacourses.test.student;

import com.aqacourses.test.interfaces.ParseFileInterface;
import com.aqacourses.test.interfaces.WriteToDbInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marina on 06.02.2019.
 */
public class StudentPedin extends Student implements ParseFileInterface, WriteToDbInterface {

    private File file;
    private BufferedWriter bufferedWriter;

    @Override
    public void writeToDb(List <String> data) {

        ArrayList <String> student = (ArrayList <String>) data;

        try {
            openConnectionToDb();
            if (isDataValid(student)) {
                for (String datum : student) {
                    bufferedWriter.write(getDate() + " - " + datum);
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("==================\n");
                System.out.println("All data is written to MS-SQL DB");
                closeConnectionToDb();
            }
        } catch (IOException e) {
            System.err.println("ERROR!!!");
            e.printStackTrace();
        }
    }

    /**
     * Parse data about students : name and surname, age, sex
     *
     * @param pathToFile
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList <String> parseFile(String pathToFile) throws IOException {
        ArrayList <String> students = new ArrayList <>();

        FileReader fileReader = new FileReader(pathToFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        String studentSeparator = "==================";

        int countLine = 0;

        while ((line = bufferedReader.readLine()) != null) {
            countLine++;

            if (line.equals(studentSeparator)) {
                countLine = 0;
            } else
                students.add(line);
        }
        bufferedReader.close();
        return students;
    }


    /**
     * Open connection to MS SQL DB
     *
     * @throws IOException
     */
    private void openConnectionToDb() throws IOException {
        String path = "./src/com/aqacourses/test/DBFiles/MS-SQL-DB.txt";
        file = new File(path);
        bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * CLose connection to MS SQL DB
     */
    private void closeConnectionToDb() throws IOException {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Cannot close connection to MS-SQL-DB");
            e.printStackTrace();
        }
        System.out.println("Close connection to Postgres MS-SQL-DB");
    }

}
