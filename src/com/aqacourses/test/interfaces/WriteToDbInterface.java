package com.aqacourses.test.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface WriteToDbInterface {

    /**
     * Write to exact DB
     *
     * @param data
     */
    void writeToDb(List<String> data);

}
