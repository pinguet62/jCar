package fr.pinguet62.jcar.camera;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public final class CameraTest implements ICamera {

    private static final CameraTest INSTANCE = new CameraTest();

    public static CameraTest getInstace() {
        return INSTANCE;
    }

    @Override
    public byte[] get() {
        try {
            return IOUtils.toByteArray(getClass().getResourceAsStream(
                    "/img/" + (int) (5 * Math.random()) + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
