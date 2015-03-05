package fr.pinguet62.jcar.backupcamera;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.function.Supplier;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ImageDisplayer {

    /** NUmber of frames per second. */
    private static final int FPS = 1;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ImageDisplayer.class);

    private final Supplier<byte[]> supplier;

    private final Timeline timeline = new Timeline(new KeyFrame(
            Duration.seconds(1.0 / FPS), ae -> refresh()));

    private final ImageView view;

    /**
     * Initialize the {@link Timeline} and run it.
     *
     * @see Timeline#play()
     */
    public ImageDisplayer(ImageView view, Supplier<byte[]> supplier) {
        this.view = view;
        this.supplier = supplier;

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Show next image of {@link ImageView}.
     * <p>
     * If an {@link RuntimeException} is thrown, the {@link Timeline} is
     * {@link Timeline#stop() stoped}.
     */
    private void refresh() {
        try {
            byte[] bytes = supplier.get();
            InputStream is = new ByteArrayInputStream(bytes);
            Image image = new Image(is);
            view.setImage(image);
        } catch (RuntimeException exception) {
            LOGGER.error("Error with camera, stoping scheduler", exception);
            stop();
            throw exception;
        }
    }

    /**
     * Stop the {@link Timeline}.
     *
     * @see Timeline#stop()
     */
    public void stop() {
        timeline.stop();
    }

}
