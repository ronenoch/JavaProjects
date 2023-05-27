
import static javafx.application.Platform.runLater;

public class BlinkTimer extends Thread {
    private TrafficLight light;
    private boolean active;

    public BlinkTimer(TrafficLight light) {
        this.light = light;
    }

    @Override
    public void run() {
        super.run();
        this.active = true;
        while (this.getIsActive()) {
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

    /* an internal function, to check the boolean safely inside the loop */
    private synchronized boolean getIsActive() {
        return this.active;
    }

    public synchronized void cancel() {
        this.active = false;
    }

}
