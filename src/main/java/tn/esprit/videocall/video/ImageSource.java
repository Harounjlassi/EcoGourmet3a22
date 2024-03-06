package tn.esprit.videocall.video;

import com.github.sarxos.webcam.Webcam;
import tn.esprit.connection.StreamClient;
import tn.esprit.models.Logs;
import tn.esprit.util.Commons;
import tn.esprit.videocall.VideoFormat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ImageSource extends StreamClient {

    private long lastImageTime;
    private Webcam webcam;
    private boolean isScreen;
    private final ScheduledExecutorService executor;

    public ImageSource() {
        super(5);
        isScreen = false;
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public boolean isOpen() {
        return isScreen ? !executor.isShutdown() : webcam != null && webcam.isOpen();
    }

    public void start() {
        if (!isScreen) {
            openWebcam();
        }
        executor.scheduleAtFixedRate(threadRunner, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        executor.shutdown();
        closeWebcam();
    }

    public void setScreen(boolean screen) {
        if (isScreen == screen) {
            return;
        }
        if (screen) {
            if (webcam != null) {
                webcam.close();
            }
        } else {
            openWebcam();
        }
        isScreen = screen;
    }

    public boolean isScreen() {
        return isScreen;
    }

    public Dimension getSize() {
        return isScreen ? VideoFormat.getViewSize() : webcam != null ? webcam.getViewSize() : null;
    }

    public void setSize(Dimension size) {
        if (webcam != null) {
            webcam.setViewSize(size);
        }
    }

    private void openWebcam() {
        webcam = Webcam.getDefault();
        if (webcam != null) {
            webcam.open(true);
        } else {
            Logs.warning(getClass(), "Webcam not found");
        }
    }

    private void closeWebcam() {
        if (webcam != null) {
            webcam.close();
            webcam = null;
        }
    }

    private final Runnable threadRunner = new Runnable() {
        @Override
        public void run() {
            try {
                if (isScreen) {
                    sendScreen();
                } else {
                    sendWebcam();
                }
            } catch (Exception ex) {
                Logs.error(getName(), "ERROR: {0}", ex);
            }
        }
    };

    private void sendScreen() throws AWTException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        image = Commons.resizeImage(image, VideoFormat.WIDTH, VideoFormat.HEIGHT);
        sendImage(image);
    }

    private void sendWebcam() {
        if (webcam != null) {
            BufferedImage image = webcam.getImage();
            image = Commons.resizeImage(image, VideoFormat.WIDTH, VideoFormat.HEIGHT);
            sendImage(image);
        }
    }

    private void sendImage(BufferedImage image) {
        long time = System.currentTimeMillis();
        if (checkFrameRate(time)) {
            send(new ImageFrame(image));
            lastImageTime = time;
        }
    }

    private boolean checkFrameRate(long time) {
        time -= lastImageTime;
        time *= VideoFormat.FRAME_RATE;
        return time + 50 > 1000;
    }

    @Override
    public String getName() {
        return isScreen ? "ScreenSource" : "WebcamSource";
    }
}
