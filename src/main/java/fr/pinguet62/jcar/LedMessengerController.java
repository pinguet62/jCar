package fr.pinguet62.jcar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public final class LedMessengerController extends ManagedController {

    @FXML
    public void bonjourButtonClick(ActionEvent event) {
        System.out.println("Bonjour !");
    }

}
