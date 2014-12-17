package fr.pinguet62.jcar.backupcamera;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import fr.pinguet62.jcar.ManagedController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

// JMFDIR = C:\Program Files (x86)\JMF2.1.1e
// PATH = %PATH%;%JMFDIR%\lib
public final class BackupCameraController extends ManagedController implements
Initializable {

    // private static final Logger LOGGER = LoggerFactory
    // .getLogger(ManagerController.class);

    @FXML
    private ImageView imageView;

    private Timeline timeline;

    @Override
    protected void finalize() throws Throwable {
        System.err.println("finalize");
        super.finalize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // XXX La vue ne se réduit pas si la fenêtre se réduit
        AnchorPane anchorPane = (AnchorPane) imageView.getParent();
        imageView.fitHeightProperty().bind(anchorPane.heightProperty());
        imageView.fitWidthProperty().bind(anchorPane.widthProperty());

        timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                ae -> showImage()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void onClose() {
        timeline.stop();

        super.onClose();
    }

    private void showImage() {
        // Get
        byte[] bytes = Camera.getInstance().getImage();
        System.err.println(bytes);
        if (bytes == null) {
            System.err.println("No image");
            return;
        }

        // Show
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Image image = new Image(inputStream);
        imageView.setImage(image);
    }

}
