package fr.pinguet62.jcar;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pinguet62.jcar.exceptions.JCarException;
import fr.pinguet62.jcar.util.LogUtils;

/**
 * The main controller used to manage the application.<br>
 * All sub-view are managed by this controller.
 */
public class ManagerController implements Initializable {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ManagerController.class);

    /** The {@link Button} used to navigate between screens. */
    @FXML
    private Button back;

    @FXML
    private Label clock;

    /** Keep reference to each controller, to execute some actions on views. */
    private final Deque<ManagedController> controllers = new LinkedList<>();

    /**
     * The layout on center of screen.
     * <p>
     * All views will be show on top of this {@link StackPane}, and the
     * {@link #back} {@link Button} allows to return to the previous screen.
     */
    @FXML
    private StackPane view;

    /**
     * Handler for {@link #back} {@link Button} click.
     * <p>
     * Return to the previous screen.<br>
     * Call {@link ManagedController#onClose()} during return.
     */
    @FXML
    public void back() {
        LOGGER.trace(LogUtils.currentMethod());

        // Controller
        ManagedController controller = controllers.getLast();
        if (controller != null)
            controller.onClose();

        int index = view.getChildren().size() - 1;
        // Remove
        view.getChildren().remove(index);
        controllers.removeLast();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.trace(LogUtils.currentMethod());

        // Enable back button if the user is not on the home screen
        ListChangeListener<Node> listener = change -> {
            LOGGER.trace("Update \"Return\"");
            back.setDisable(change.getList().size() == 1);
        };
        view.getChildren().addListener(listener);

        // Clock
        // 0,5 sec
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500),
                ae -> clock.setText(new SimpleDateFormat("hh:mm:ss")
                .format(Calendar.getInstance().getTime()))));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        showView("/fxml/home.fxml");
    }

    /**
     * Show the view in the foreground.
     *
     * @param fxml
     *            The path to the FXML resource file.
     * @return The new view.
     */
    protected Node showView(String fxml) {
        LOGGER.trace("Load view: " + fxml);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

        // View
        Node futureView;
        try {
            futureView = loader.load();
        } catch (IOException e) {
            throw new JCarException(e);
        }
        view.getChildren().add(futureView);

        // Controller
        ManagedController controller = loader.getController();
        if (controller != null)
            controller.setMainController(this);
        controllers.addLast(controller);

        return futureView;
    }

}
