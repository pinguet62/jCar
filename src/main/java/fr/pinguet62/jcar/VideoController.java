package fr.pinguet62.jcar;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import fr.pinguet62.jcar.exceptions.JCarException;

public final class VideoController extends ManagedController implements
        Initializable {

    @FXML
    private MediaView mediaView;

    @FXML
    private Button playPause;

    @FXML
    private Slider progress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Media media = new Media(getClass().getResource("/vidéo.mp4")
                    .toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            // XXX La vue ne se réduit pas si la fenêtre se réduit
            AnchorPane anchorPane = (AnchorPane) mediaView.getParent();
            mediaView.fitHeightProperty().bind(anchorPane.heightProperty());
            mediaView.fitWidthProperty().bind(anchorPane.widthProperty());

            mediaPlayer.play();

            System.out.println(mediaPlayer);
        } catch (URISyntaxException exception) {
            throw new JCarException(exception);
        }
    }

}
