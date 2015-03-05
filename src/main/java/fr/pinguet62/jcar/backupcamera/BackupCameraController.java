package fr.pinguet62.jcar.backupcamera;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pinguet62.jcar.ManagedController;
import fr.pinguet62.jcar.camera.Webcam;

public final class BackupCameraController extends ManagedController implements
        Initializable {

    // TODO FPS to config file
    private static final int FPS = 1;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(BackupCameraController.class);

    @FXML
    private ImageView imageView;

    private Timeline timeline;

    @Override
    public void close() {
        LOGGER.debug("Stopping scheduler");
        timeline.stop();

        super.close();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
        super.finalize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // FIXME La vue ne se réduit pas si la fenêtre se réduit
        AnchorPane anchorPane = (AnchorPane) imageView.getParent();
        imageView.fitHeightProperty().bind(anchorPane.heightProperty());
        imageView.fitWidthProperty().bind(anchorPane.widthProperty());

        double intervalle = 1.0 / FPS;
        LOGGER.info("Frequence: " + FPS + "ips, intervalle of " + intervalle
                + "s");
        timeline = new Timeline(new KeyFrame(Duration.seconds(intervalle),
                ae -> refresh()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    void refresh() {
        byte[] bytes = Webcam.getInstance().get();

        InputStream is;
        if (bytes == null)
            is = getClass().getResourceAsStream("/camera_default.png");
        else
            is = new ByteArrayInputStream(bytes);

        Image image = new Image(is);
        imageView.setImage(image);
    }

}