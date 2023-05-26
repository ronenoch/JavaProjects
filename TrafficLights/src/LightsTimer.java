import javafx.scene.paint.Color;

import java.util.ArrayList;

public class LightsTimer extends Thread{

    private ArrayList<TrafficLight> trafficLights;
    private int timeout;
    private int greenTimeout;
    private int redTimeout;

    public LightsTimer(ArrayList<TrafficLight> trafficLights, int timeout, int redTimeout, int greenTimeout) {
        this.trafficLights = trafficLights;
        this.timeout = timeout;
        this.greenTimeout = greenTimeout;
        this.redTimeout = redTimeout;
    }

    @Override
    public void run() {
        super.run();
        while (true)
        {
            try {
                Thread.sleep(this.timeout);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < this.trafficLights.size(); i++) {
                TrafficLight light = this.trafficLights.get(i);
                /* reducing the timeout of the light and then checking if it got timeout (when it is 0) */
                light.setTimeout(light.getTimeout() - this.timeout);
                if (0 >= light.getTimeout()) {

                    light.switchState();
                    if (Color.GREEN == light.getGreenCircle().getFill()) {
                        light.setTimeout(greenTimeout);
                    } else {
                        light.setTimeout(redTimeout);
                    }
                }
            }
        }
    }
}
