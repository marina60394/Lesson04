package com.aqacourses.test.student;

import java.util.List;

public class Student extends AbstractStudent {

    /**
     * Verify that data is not empty
     *
     * @param data
     * @return true if not empty false otherwise
     */
    protected boolean validateData(List<String> data) {
        if (!data.isEmpty()) {
            return true;
        }
        return false;
    }

}
