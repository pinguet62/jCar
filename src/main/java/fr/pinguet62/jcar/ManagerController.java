package fr.pinguet62.jcar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ManagerController implements Initializable {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ManagerController.class);

    /** The {@link Button} used to navigate between screens. */
    @FXML
    private Button back;

    /**
     * The layout on center of screen.
     * <p>
     * All views will be show on top of this {@link StackPane}, and the
     * {@link #back} {@link Button} allows to return to the previous screen.
     */
    @FXML
    private StackPane view;

    // TODO test
    @FXML
    public void addElement(ActionEvent event) {
        view.getChildren().add(new Button("toto"));
    }

    /**
     * Return to the previous screen.
     * <p>
     * Handler for {@link #back} {@link Button} click.
     */
    @FXML
    public void back() {
        LOGGER.debug("Return");
        view.getChildren().remove(view.getChildren().size() - 1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("initialize");

        // Enable back button if the user is not on the home screen
        ListChangeListener<Node> listener = change -> {
            LOGGER.trace("Update \"Return\"");
            back.setDisable(change.getList().size() == 1);
        };
        view.getChildren().addListener(listener);

        showView("/home.fxml");
    }

    protected Parent showView(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

        // Show view
        Parent futureView;
        try {
            futureView = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        view.getChildren().add(futureView);

        // ManagedController
        ManagedController controller = loader.getController();
        controller.setMainController(this);

        return futureView;
    }

}
