import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TrafficLight {
    /* I want it to be like a struct */
    private Circle redCircle;
    private Circle greenCircle;
    private Rectangle redRectangle;
    private Rectangle greenRectangle;
    private BlinkTimer blinkTimer;
    private int timeUntilTimeout;

    public TrafficLight(int x, int y) {
        final int radius = 20;
        /* made these variable to be able to change them easily */
        final int rectangleWidth = radius;
        final int rectangleHeight = radius;

        redCircle = new Circle(x, y, radius, Color.RED);
        /* Adding 2 radius value to the vertical point to make it be just below the red circle */
        greenCircle = new Circle(x, y + radius * 2, radius, Color.TRANSPARENT);
        /* the width is radius, and it's middle should be right below the circles. */
        redRectangle = new Rectangle(x - rectangleWidth / 2, y + radius * 3, rectangleWidth, rectangleHeight);
        greenRectangle = new Rectangle(x - rectangleWidth / 2, y + radius * 3 + rectangleHeight, rectangleWidth, rectangleHeight);
        redRectangle.setFill(Color.RED);
        greenRectangle.setFill(Color.TRANSPARENT);
        redRectangle.setStroke(Color.BLACK);
        greenRectangle.setStroke(Color.BLACK);
        greenCircle.setStroke(Color.BLACK);
        redCircle.setStroke(Color.BLACK);
        /* will be set outside the class, because the trafficLight class should not know the red/green timeout */
        this.timeUntilTimeout = 0;

        this.blinkTimer = new BlinkTimer(this);
    }

    /* The instinct is making this function a synchronized function,
     * but it does not need to, because when the blinkTimer thread is running,
     * no other thread touches the shapes */
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
                /* Waiting enough time so that the blinking operation will finish, before setting the shapes colors */
                this.blinkTimer.join(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        /* Actually, the operations do not need to be 'synchronized', because at this time, the only thread
         * that can approach to the shapes is this (because the blink-thread has ended or
         * will not blink anymore). */
        this.redCircle.setFill(Color.TRANSPARENT);
        this.greenCircle.setFill(Color.GREEN);
        this.redRectangle.setFill(Color.RED);
        this.greenRectangle.setFill(Color.TRANSPARENT);
    }

    public void vehicleGoRed() {
        /* Like vehicleGoGreen, the operations do not need to be synchronized, because at this time, the only thread
         * that can approach to the shapes is this. */
        this.greenCircle.setFill(Color.TRANSPARENT);
        this.redCircle.setFill(Color.RED);
        this.greenRectangle.setFill(Color.GREEN);
        this.redRectangle.setFill(Color.TRANSPARENT);
        if (!this.blinkTimer.isAlive()) {
            this.blinkTimer = new BlinkTimer(this);
            this.blinkTimer.start();
        }
    }

    public void switchState() {
        if (Color.TRANSPARENT == this.greenCircle.getFill()) {
            this.vehicleGoGreen();
        } else {
            this.vehicleGoRed();
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

    public int getTimeUntilTimeout() {
        return this.timeUntilTimeout;
    }

    public void setTimeUntilTimeout(int timeUntilTimeout) {
        this.timeUntilTimeout = timeUntilTimeout;
    }
}
