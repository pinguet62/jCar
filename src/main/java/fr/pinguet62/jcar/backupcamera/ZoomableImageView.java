package fr.pinguet62.jcar.backupcamera;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ZoomableImageView extends ImageView {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ZoomableImageView.class);

    private Double initialHeight;
    private Double initialWidth;

    private boolean zoomed = false;

    {
        setPreserveRatio(false);
        setOnMouseClicked(this::mouseClicked);

        setFitHeight(30);
        setFitWidth(100);
    }

    public void mouseClicked(MouseEvent event) {
        if (zoomed) {
            LOGGER.debug("Return to initial zoom");
            // Position
            setTranslateX(0);
            setTranslateY(0);
            // Size
            setFitHeight(initialHeight);
            setFitWidth(initialWidth);
        } else {
            // TODO show at top level
            LOGGER.debug("Zoom");
            // Position
            setTranslateX(-1 * getLayoutX());
            setTranslateY(-1 * getLayoutY());
            // Size
            initialHeight = getFitHeight();
            initialWidth = getFitWidth();
            fitHeightProperty().set(getParent().getLayoutBounds().getHeight());
            fitWidthProperty().set(getParent().getLayoutBounds().getWidth());
        }
        zoomed = !zoomed;
    }

}
