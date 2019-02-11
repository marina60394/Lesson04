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

    private FileWriter fileWriter;
    private PrintWriter printWriter;

    @Override
    public void writeToDb(List <String> data) {
        ArrayList <String> student = new ArrayList <String>();
        try {
            openConnectionToDb();
            if (validateData(data) && isDataValid(student)) {
                for (String datum : data) {
                    printWriter.println(getDate() + " - " + datum);
                }
                printWriter.print("=====================\n");
                System.out.println("All data is written to MS -SQL-DB");
                closeConnectionToDb();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overload method writeToDB
     * @param data
     */
    public void writeToDb(ArrayList <ArrayList <String>> data) {
        try {
            openConnectionToDb();
            for (ArrayList <String> student : data) {
                if (isDataValid(student)) {
                    for (String value : student) {
                        printWriter.println(getDate() + " - " + value);
                    }
                    printWriter.print("=====================\n");
                }
            }
            System.out.println("All data is written to MS -SQL-DB");
            closeConnectionToDb();
        } catch (IOException e) {
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
    public ArrayList <ArrayList <String>> parseFile(String pathToFile) throws IOException {
        ArrayList <ArrayList <String>> listOfStudents = new ArrayList <ArrayList <String>>();

        FileReader fileReader = new FileReader(pathToFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        String studentSeparator = "==================";

        int countLine = 0;
        ArrayList <String> student = new ArrayList <String>();

        while ((line = bufferedReader.readLine()) != null) {
            countLine++;

            if (countLine == 1) {
                String name = line.substring(22);
                student.add(name);
            }
            if (countLine == 2) {
                String age = line.substring(22);
                student.add(age);
            }
            if (countLine == 3) {
                String sex = line.substring(22);
                student.add(sex);
            }

            if (line.equals(studentSeparator)) {
                listOfStudents.add(student);
                student = new ArrayList <String>();
                countLine = 0;
            }
        }
        bufferedReader.close();
        return listOfStudents;
    }


    /**
     * Open connection to MS SQL DB
     *
     * @throws IOException
     */
    private void openConnectionToDb() throws IOException {
        String path = "./src/com/aqacourses/test/DBFiles/MS-SQL-DB.txt";
        fileWriter = new FileWriter(path);
        printWriter = new PrintWriter(fileWriter);
    }

    /**
     * CLose connection to MS SQL DB
     */
    private void closeConnectionToDb() throws IOException {
        printWriter.close();
        fileWriter.close();
        System.out.println("Close connection to MS SQL DB");
    }

}
