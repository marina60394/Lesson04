package com.aqacourses.test.student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractStudent {

    /**
     * Parse data from GUI
     *
     * @return list with student data
     */
    public List <String> parseData() {
        List <String> data = new ArrayList <>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter name of student:");
        String name = scanner.nextLine();
        if (isLineNotEmpty(name)) {
            data.add(name);
        }

        System.out.println("Please enter age of student:");
        String age = scanner.nextLine();
        if (isLineNotEmpty(age)) {
            data.add(age);
        }

        System.out.println("Please enter sex of student:");
        String sex = scanner.nextLine();
        if (isLineNotEmpty(sex)) {
            data.add(sex);
        }

        return data;
    }

    /**
     * Opens GUI
     */
    public void openGui() {
        System.out.println("Opened GUI");
    }

    /**
     * Verify that string is not empty
     *
     * @param line
     * @return
     */
    private boolean isLineNotEmpty(String line) {
        if (!line.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Get date
     *
     * @return string that represents current date and time
     */
    public String getDate() {
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                .format(Calendar.getInstance().getTime());
    }

    /**
     * Check that student has name and surname, 17 < age < 25, sex is male of female
     *
     * @param student
     * @return
     */
    public boolean isDataValid(ArrayList <String> student) {
        int age = Integer.parseInt(student.get(1));
        String sex= student.get(2);
        if (student.size() > 2 && student.get(0).matches("\\w{1,25} \\w{1,25}") && (age > 17 && age < 25)
                && (sex.contains("male") || sex.contains("female"))) {
            return true;
        }
        return false;
    }
}
