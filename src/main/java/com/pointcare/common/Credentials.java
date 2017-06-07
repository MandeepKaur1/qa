package com.pointcare.common;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by mandeep.
 */

public class Credentials {
    private static JSONObject jsonCredentials;
    private static Credentials instance;
    private Credentials(String path) {
        jsonCredentials = FileReader.readFileAsJson(path);
    }

    public static Credentials credentials;

    /**
     * Returns instance of the Credentials using Singleton pattern.
     *
     * @param path path to the file
     * @return instance of the Credentials
     */
    public static Credentials getInstance(String path) {
        if (instance == null) {
            instance = new Credentials(path);
        }
        return instance;
    }
    //credentials = Credentials..getInstance("src/main/resources/Credentials.json");


    /**
     * Retrieves user's domain name from the JSON file by the given username.
     *
     * @param t tenant
     * @param <T> Credentials.User or String representation of username
     * @return user's email
     */
    public <T> String getTeant(T t) {
        return getValueAsString(getUsername(t), "tenant");
    }

    /**
     * Retrieves user's email from the JSON file by the given username.
     *
     * @param t username
     * @param <T> Credentials.User or String representation of username
     * @return user's email
     */
    public <T> String getEmail(T t) {
        return getValueAsString(getUsername(t), "email");
    }

    /**
     * Retrieves user's password from the JSON file by the given username.
     *
     * @param t username
     * @param <T> Credentials.User or String representation of username
     * @return user's password
     */
    public <T> String getPassword(T t) {
        return getValueAsString(getUsername(t), "password");
    }

    private <T> String getUsername(T t) {
        String username;
        if (t instanceof Credentials.User) {
            Credentials.User user = (Credentials.User) t;
            username = user.toString();
        } else if (t instanceof String) {
            username = (String) t;
        } else {
            throw new IllegalArgumentException("Must be Credentials.User or String. But found: " + t.getClass());
        }
        return username;
    }

    private String getValueAsString(String user, String key) {
        JSONObject credPair = getPairAsJson(user);
        if (credPair != null) {
            return credPair.getString(key);
        } else {
            throw new IllegalStateException("[" + user + "] user not found in JSON file.");
        }
    }

    private JSONObject getPairAsJson(String user) {
        Iterator iterator = jsonCredentials.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (key.equalsIgnoreCase(user)) {
                return jsonCredentials.getJSONObject(key);
            }
        }
        return null;
    }

    /**
     * The User enum holds username constants are mapping to username keys in the JSON file.
     */
    public enum User {
        clinica_sierra_vista
    }
}