package wniemiec.mobilang.ama.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Responsible for representing data from properties tag.
 */
public class Properties {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String name;
    private Set<String> platforms;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public Properties() {
        name = "unknown";
        platforms = new HashSet<>();
    }


    //-------------------------------------------------------------------------
    //		Getters & Setters
    //-------------------------------------------------------------------------
    public String getAppName() {
        return name;
    }

    public void setAppName(String name) {
        this.name = name;
    }

    public Set<String> getPlatforms() {
        return platforms;
    }

    public void addPlatform(String platform) {
        platforms.add(platform);
    }
}