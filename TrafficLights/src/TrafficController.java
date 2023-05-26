import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.util.ArrayList;

public class TrafficController {


    @FXML
    private BorderPane pane;

    public void initialize() {

        String[] args = System.getProperty("javafx.application.args").split(",");

        int redLightTimeout = Integer.parseInt(args[0]);
        int greenLightTimeout = Integer.parseInt(args[1]);

        /* according to the forum, we can assume that the green timeout will be smaller than the red's
         * because if not, the traffic lights will be green at the same time as their neighbor. */
        if (redLightTimeout < greenLightTimeout) {
            JOptionPane.showMessageDialog(null, "the green light timeout must be smaller than the red's");
        }

        TrafficLight a1 = new TrafficLight((int)(150), 40);
        a1.vehicleGoGreen();
        a1.setTimeout(greenLightTimeout);

        TrafficLight a2 = new TrafficLight((int)(40), 150);
        a2.vehicleGoRed();
        a2.setTimeout(redLightTimeout);

        TrafficLight a3 = new TrafficLight((int)(150), 300);
        a3.vehicleGoGreen();
        a3.setTimeout(greenLightTimeout);

        TrafficLight a4 = new TrafficLight((int)(300 - 40), 150);
        a4.vehicleGoRed();
        a4.setTimeout(redLightTimeout);

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

        LightsTimer timer = new LightsTimer(lights, 100, redLightTimeout, greenLightTimeout);
        timer.start();
//        a1.vehicleGoGreen();
    }

}