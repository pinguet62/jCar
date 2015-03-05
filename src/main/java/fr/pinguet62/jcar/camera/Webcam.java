package fr.pinguet62.jcar.camera;

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
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

import fr.pinguet62.jcar.exceptions.JCarException;

//JMFDIR = C:\Program Files (x86)\JMF2.1.1e
//PATH = %PATH%;%JMFDIR%\lib

public final class Webcam implements ICamera {

    private static final Webcam instance = new Webcam();

    public static Webcam getInstance() {
        return instance;
    }

    private final Player player;

    private Webcam() {
        CaptureDeviceInfo info = CaptureDeviceManager
                .getDevice("vfw:Microsoft WDM Image Capture (Win32):0");
        MediaLocator mediaLocator = info.getLocator();
        try {
            player = Manager.createRealizedPlayer(mediaLocator);
        } catch (NoPlayerException | CannotRealizeException | IOException exception) {
            throw new JCarException(exception);
        }
        player.start();
    }

    @Override
    protected void finalize() throws Throwable {
        player.close();
        player.deallocate();
    };

    @Override
    public byte[] get() {
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
