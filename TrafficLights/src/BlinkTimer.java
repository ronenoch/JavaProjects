import java.util.concurrent.Callable;

import static javafx.application.Platform.runLater;

public class BlinkTimer extends Thread {
//    private Callable func;
    private TrafficLight light;
    private boolean active;

    public BlinkTimer(TrafficLight light) {
        this.light = light;
    }

    @Override
    public void run() {
        super.run();
        this.active = true;
        while (this.active) {
            runLater(() -> {
                this.light.blink();
            });
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void cancel() {
        this.active = false;
    }

}
