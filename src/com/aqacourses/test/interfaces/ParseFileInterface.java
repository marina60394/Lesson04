package com.aqacourses.test.interfaces;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Marina on 06.02.2019.
 */
public interface ParseFileInterface {

    /**
     *  Parse data from file
     * @param pathToFile
     * @return ArrayList
     */
    public ArrayList <ArrayList<String>> parseFile(String pathToFile) throws IOException;

}
