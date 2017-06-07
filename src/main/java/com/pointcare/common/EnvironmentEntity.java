package com.pointcare.common;

/**
 * Created by mandeep on 6/2/17.
 */

/**
 * The EnvironmentEntity class represents an entity that contains all required
 * settings for test environments such as name, URLs, DB and Email connections.
 */

public class EnvironmentEntity {

    private String environmentName;
    private String uiUrl;

    public EnvironmentEntity(EnvironmentEntity entity) {
        environmentName = entity.getEnvironmentName();
        uiUrl = entity.getUiUrl();
    }


    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getUiUrl() {
        return uiUrl;
    }

    public void setUiUrl(String uiUrl) {
        this.uiUrl = uiUrl;
    }
}