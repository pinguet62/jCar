package fr.pinguet62.jcar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pinguet62.jcar.util.LogUtils;

public final class HomeController extends ManagedController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ManagerController.class);

    @FXML
    public void backupCameraButtonClick() {
        LOGGER.trace(LogUtils.currentMethod());
        mainController.showView("/fxml/backupCamera.fxml");
    }

    @FXML
    public void camerasButtonClick() {
        LOGGER.trace(LogUtils.currentMethod());
        mainController.showView("/fxml/cameras.fxml");
    }

    @FXML
    public void ledMessengerButtonClick(ActionEvent event) {
        LOGGER.trace(LogUtils.currentMethod());
        mainController.showView("/fxml/ledMessenger.fxml");
    }

    @FXML
    public void videoButtonClick() {
        LOGGER.trace(LogUtils.currentMethod());
        mainController.showView("/fxml/video.fxml");
    }

}
