package fr.pinguet62.jcar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public final class LedMessengerController extends ManagedController {

    @FXML
    public void bonjourButtonClick(ActionEvent event) {
        System.out.println("Bonjour !");
    }

    @Override
    public void onClose() {
        System.out.println("onClose()");
        super.onClose();
    }

}
