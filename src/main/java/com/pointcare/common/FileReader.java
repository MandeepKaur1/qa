package com.pointcare.common;

import jdk.nashorn.internal.parser.JSONParser;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
/**
 * Created by mandeep.
 */

/**
 * The FileReader util class is responsible for reading text from files.
 */
public class FileReader {
    private static final Logger logger = Logger.getLogger(FileReader.class);

    public static void readFromJson(String path) {
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();

        try {

            Object obj = parser.parse(new java.io.FileReader(path));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }



    public FileReader(String request) {
    }

    /**
     * Parses text content of the given file to plain text.
     *
     * @param path path to the file that needs to be read
     * @return plain text
     */
    public static String readFileAsText(String path) {
        return null;
    }

    /**
     * Parses text content of the given file to JSONObject.
     *
     * @param path path to the file that needs to be read
     * @return JSONObject representation of the text
     */
    public static org.json.JSONObject readFileAsJson(String path) {
        return new org.json.JSONObject(readFileAsText(path));
    }
}