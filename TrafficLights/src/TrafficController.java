import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class TrafficController {


    @FXML
    private BorderPane pane;

    public void initialize() {

        String[] args = System.getProperty("javafx.application.args").split(",");

        int lightTimeout = Integer.parseInt(args[0]);


        TrafficLight a1 = new TrafficLight((int)(150), 40);
        a1.vehicleGoGreen();

        TrafficLight a2 = new TrafficLight((int)(40), 150);
        a2.vehicleGoRed();

        TrafficLight a3 = new TrafficLight((int)(150), 300);
        a3.vehicleGoGreen();

        TrafficLight a4 = new TrafficLight((int)(300 - 40), 150);
        a4.vehicleGoRed();

        ArrayList<TrafficLight> lights = new ArrayList<>();
        lights.add(a1);
        lights.add(a2);
        lights.add(a3);
        lights.add(a4);

        for (TrafficLight light : lights) {
            pane.getChildren().add(light.getGreenCircle());
            pane.getChildren().add(light.getRedCircle());
            pane.getChildren().add(light.getRedRectangle());
            pane.getChildren().add(light.getGreenRectangle());
        }

        LightsTimer timer = new LightsTimer(lights, lightTimeout);
        timer.start();

//        a1.vehicleGoGreen();
    }

}