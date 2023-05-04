import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TrafficLight {
    /* I want it to be like a struct */
    private Circle redCircle;
    private Circle greenCircle;
    private Rectangle redRectangle;
    private Rectangle greenRectangle;

    public TrafficLight(int x, int y) {
        int radius = 20;

        redCircle = new Circle(x, y, radius, Color.RED);
        greenCircle = new Circle(x, y + radius * 2, radius, Color.WHITE);
        redRectangle = new Rectangle(x - radius / 2, y + radius * 3, radius, radius);
        greenRectangle = new Rectangle(x - radius / 2, y + radius * 3 + radius, radius, radius);
        redRectangle.setFill(Color.RED);
        greenRectangle.setFill(Color.WHITE);
        redRectangle.setStroke(Color.BLACK);
        greenRectangle.setStroke(Color.BLACK);
        greenCircle.setStroke(Color.BLACK);
        redCircle.setStroke(Color.BLACK);
    }

    public void vehicleGoGreen() {
        this.redCircle.setFill(Color.WHITE);
        this.greenCircle.setFill(Color.GREEN);
        this.redRectangle.setFill(Color.RED);
        this.greenRectangle.setFill(Color.WHITE);
    }

    public void vehicleGoRed() {
        this.greenCircle.setFill(Color.WHITE);
        this.redCircle.setFill(Color.RED);
        this.greenRectangle.setFill(Color.GREEN);
        this.redRectangle.setFill(Color.WHITE);
    }

    public void switchState() {
        if (Color.WHITE == this.greenCircle.getFill()) {
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
}
