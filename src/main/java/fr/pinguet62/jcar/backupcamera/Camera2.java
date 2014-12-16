package fr.pinguet62.jcar.backupcamera;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSourceException;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Processor;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.util.BufferToImage;

import fr.pinguet62.jcar.exception.JCarException;

public final class Camera2 {

    private static final Camera2 instance = new Camera2();

    public static Camera2 getInstance() {
        return instance;
    }

    private final Player player;

    private Camera2() {
        CaptureDeviceInfo info = CaptureDeviceManager
                .getDevice("vfw:Microsoft WDM Image Capture (Win32):0");
        MediaLocator mediaLocator = info.getLocator();
        DataSource dataSource = null;
        try {
            dataSource = Manager.createDataSource(mediaLocator);
            Processor processor = Manager.createProcessor(dataSource);
            DataSource dataOutput = processor.getDataOutput();
            dataOutput.start();
            player = Manager.createRealizedPlayer(dataSource);
            processor.start();
            player.start();
        } catch (NoDataSourceException | IOException | NoPlayerException
                | CannotRealizeException exception) {
            throw new JCarException(exception);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        player.close();
        player.deallocate();
    };

    public byte[] getImage() {
        FrameGrabbingControl frameGrabbingControl = (FrameGrabbingControl) player
                .getControl("javax.media.control.FrameGrabbingControl");
        Buffer buffer = frameGrabbingControl.grabFrame();
        BufferToImage bufferToImage = new BufferToImage(
                (VideoFormat) buffer.getFormat());
        BufferedImage image = (BufferedImage) bufferToImage.createImage(buffer);
        if (image == null)
            return null;
        // Convert
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpeg", out);
            out.flush();
        } catch (IOException exception) {
            throw new JCarException(exception);
        }
        return out.toByteArray();
    }

}
