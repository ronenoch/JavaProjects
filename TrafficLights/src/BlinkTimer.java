import java.util.concurrent.Callable;

import static javafx.application.Platform.runLater;

public class BlinkTimer extends Thread {
//    private Callable func;
    private TrafficLight light;

    public BlinkTimer(TrafficLight light) {
        this.light = light;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            runLater(() -> {
                this.light.blink();
            });

        }
    }

}
