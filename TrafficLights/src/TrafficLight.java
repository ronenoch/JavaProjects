import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.Callable;

import static javafx.application.Platform.runLater;

public class TrafficLight {
    /* I want it to be like a struct */
    private Circle redCircle;
    private Circle greenCircle;
    private Rectangle redRectangle;
    private Rectangle greenRectangle;
    private BlinkTimer blinkTimer;
    private int timeout;

    public TrafficLight(int x, int y) {
        int radius = 20;

        redCircle = new Circle(x, y, radius, Color.RED);
        greenCircle = new Circle(x, y + radius * 2, radius, Color.TRANSPARENT);
        redRectangle = new Rectangle(x - radius / 2, y + radius * 3, radius, radius);
        greenRectangle = new Rectangle(x - radius / 2, y + radius * 3 + radius, radius, radius);
        redRectangle.setFill(Color.RED);
        greenRectangle.setFill(Color.TRANSPARENT);
        redRectangle.setStroke(Color.BLACK);
        greenRectangle.setStroke(Color.BLACK);
        greenCircle.setStroke(Color.BLACK);
        redCircle.setStroke(Color.BLACK);
        this.timeout = 0;

        this.blinkTimer = new BlinkTimer(this);
//        this.blinkTimer.start();
    }

    public void blink() {
        if (Color.TRANSPARENT == this.greenRectangle.getFill()) {
            this.greenRectangle.setFill(Color.GREEN);
        } else {

            this.greenRectangle.setFill(Color.TRANSPARENT);
        }
    }

    public void vehicleGoGreen() {
        if (this.blinkTimer.isAlive()) {

            this.blinkTimer.cancel();
            try {
                this.blinkTimer.join(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.redCircle.setFill(Color.TRANSPARENT);
        this.greenCircle.setFill(Color.GREEN);
        this.redRectangle.setFill(Color.RED);
        this.greenRectangle.setFill(Color.TRANSPARENT);
    }

    public void vehicleGoRed() {
        this.greenCircle.setFill(Color.TRANSPARENT);
        this.redCircle.setFill(Color.RED);
        this.greenRectangle.setFill(Color.GREEN);
        this.redRectangle.setFill(Color.TRANSPARENT);
        if (!this.blinkTimer.isAlive()) {
            this.blinkTimer = new BlinkTimer(this);
            this.blinkTimer.start();
        }
//        this.blinkTimer.start();
    }

    public void switchState() {
        if (Color.TRANSPARENT == this.greenCircle.getFill()) {
            this.vehicleGoGreen();
        } else {
            this.vehicleGoRed();
//            runLater(() -> blink());
        }
    }

    public Circle getRedCircle() {
        return redCircle;
    }

    public Circle getGreenCircle() {
        return greenCircle;
    }

    public Rectangle getRedRectangle() {
        return redRectangle;
    }

    public Rectangle getGreenRectangle() {
        return greenRectangle;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
