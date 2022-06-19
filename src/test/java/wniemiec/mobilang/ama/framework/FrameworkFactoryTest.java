package wniemiec.mobilang.ama.framework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wniemiec.mobilang.ama.framework.ionic.IonicFramework;
import wniemiec.mobilang.ama.framework.reactnative.ReactNativeFramework;
import wniemiec.mobilang.ama.parser.exception.FactoryException;


class FrameworkFactoryTest {

    @Test
    void testReactNativeInstance() throws FactoryException {
        Framework framework = FrameworkFactory.getInstance("react-native");

        Assertions.assertTrue(framework instanceof ReactNativeFramework);
    }

    @Test
    void testIonicInstance() throws FactoryException {
        Framework framework = FrameworkFactory.getInstance("ionic");

        Assertions.assertTrue(framework instanceof IonicFramework);
    }

    @Test
    void testNonExistentInstance() {
        Assertions.assertThrows(FactoryException.class, () -> {
            FrameworkFactory.getInstance("i2onic");
        });
    }
}
