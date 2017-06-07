package com.pointcare.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by mandeep.
 */
public class Properties {
    private static Properties instance;

    public String defaultEnvironmentName(){
        return FrameworkConstants.ENVIRONMENT_STAGING;
    }





   /* public enum Browsers {
        CHROME, FIREFOX
    }

    private Browsers browser = Browsers.FIREFOX;


    public Browsers getBrowser() {
        return browser;
    }

    public void setBrowser(Browsers browser) {
        this.browser = browser;
    }

    /*public static Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
        }
        return instance;
    }*/


  /*  public void setBrowser(String browser) {
        try {
            this.browser = Browsers.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("wrong browser type: " + browser);
        }
    }*/
}
