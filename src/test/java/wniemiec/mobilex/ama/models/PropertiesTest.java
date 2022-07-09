package wniemiec.mobilex.ama.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PropertiesTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Properties properties;
    private String name;
    private Set<String> platforms;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        properties = new Properties();
        name = null;
        platforms = new HashSet<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testGetName() {
        withName("foo-app");
        buildProperties();
        assertGetAppNameIsCorrect();
    }

    @Test
    void testGetPlatformsWithOnePlatform() {
        withPlatforms("android");
        buildProperties();
        assertGetPlatformsIsCorrect();
    }

    @Test
    void testGetPlatformsWithTwoPlatforms() {
        withPlatforms("android", "ios");
        buildProperties();
        assertGetPlatformsIsCorrect();
    }

    @Test
    void testGetNameAndGetPlatforms() {
        withName("foo-app");
        withPlatforms("android", "ios");
        buildProperties();
        assertGetAppNameIsCorrect();
        assertGetPlatformsIsCorrect();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withName(String appName) {
        name = appName;
    }

    private void buildProperties() {
        properties.setAppName(name);
        platforms.forEach(properties::addPlatform);
    }

    private void assertGetAppNameIsCorrect() {
        Assertions.assertEquals(name, properties.getApplicationName());
    }

    private void withPlatforms(String... appPlatforms) {
        Arrays.stream(appPlatforms).forEach(platforms::add);
    }

    private void assertGetPlatformsIsCorrect() {
        Assertions.assertEquals(platforms, properties.getTargetPlatforms());
    }
}
