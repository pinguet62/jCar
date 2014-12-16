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
    public void backupCamera2ButtonClick() {
        LOGGER.trace(LogUtils.currentMethod());
        mainController.showView("/fxml/backupCamera2.fxml");
    }

    @FXML
    public void backupCameraButtonClick() {
        LOGGER.trace(LogUtils.currentMethod());
        mainController.showView("/fxml/backupCamera.fxml");
    }

    @FXML
    public void ledMessengerButtonClick(ActionEvent event) {
        LOGGER.trace(LogUtils.currentMethod());
        mainController.showView("/fxml/ledMessenger.fxml");
    }

}
