package fr.pinguet62.jcar.camera;

import java.util.function.Supplier;

public interface ICamera extends Supplier<byte[]> {

    /**
     * Get the instant image of camera.
     *
     * @return A {@code byte} array who contains data.
     */
    @Override
    public byte[] get();

}
