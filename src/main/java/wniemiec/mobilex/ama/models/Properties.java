package wniemiec.mobilex.ama.models;

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
        name = "mobilang-application";
        platforms = new HashSet<>();
    }


    //-------------------------------------------------------------------------
    //		Getters & Setters
    //-------------------------------------------------------------------------
    public String getApplicationName() {
        return name;
    }

    public void setApplicationName(String name) {
        if (isEmpty(name)) {
            throw new IllegalArgumentException("Application name cannot be empty");
        }

        this.name = name;
    }

    private boolean isEmpty(String text) {
        return ((name == null) || name.isBlank());
    }

    public Set<String> getTargetPlatforms() {
        return platforms;
    }

    public void addPlatform(String platform) {
        if (isEmpty(platform)) {
            throw new IllegalArgumentException("Platform cannot be empty");
        }

        platforms.add(platform);
    }
}
