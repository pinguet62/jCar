package fr.pinguet62.jcar;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HomeController extends ManagedController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ManagerController.class);

    @FXML
    public void ledMessengerButtonClick(ActionEvent event) throws IOException {
        LOGGER.trace("ledMessengerButtonClick");

        mainController.showView("/ledMessenger.fxml");
    }

}
