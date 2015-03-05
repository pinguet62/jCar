package fr.pinguet62.jcar.backupcamera;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pinguet62.jcar.ManagedController;
import fr.pinguet62.jcar.camera.CameraTest;

public final class CamerasController extends ManagedController implements
Initializable {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CamerasController.class);

    @FXML
    private ZoomableImageView frontCamera;

    private ImageDisplayer frontCameraDisplayer;

    @FXML
    private ZoomableImageView frontLeftCamera;

    @FXML
    private ZoomableImageView frontRightCamera;

    @FXML
    private ZoomableImageView rearCamera;

    @FXML
    private ZoomableImageView rearLeftCamera;

    @FXML
    private ZoomableImageView rearRightCamera;

    @Override
    public void close() {
        LOGGER.debug("Stopping schedulers");
        frontCameraDisplayer.stop();

        super.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Checking cameras");
        assert frontCamera != null;
        assert frontLeftCamera != null;
        assert frontRightCamera != null;
        assert rearCamera != null;
        assert rearLeftCamera != null;
        assert rearRightCamera != null;

        frontCameraDisplayer = new ImageDisplayer(frontCamera,
                CameraTest.getInstace());
    }

}
