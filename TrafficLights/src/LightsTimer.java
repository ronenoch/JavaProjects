import javafx.scene.paint.Color;

import java.util.ArrayList;

public class LightsTimer extends Thread{

    private ArrayList<TrafficLight> trafficLights;
    private int timeInterval;
    private int greenTimeout;
    private int redTimeout;

    public LightsTimer(ArrayList<TrafficLight> trafficLights, int timeInterval, int redTimeout, int greenTimeout) {
        this.trafficLights = trafficLights;
        this.timeInterval = timeInterval;
        this.greenTimeout = greenTimeout;
        this.redTimeout = redTimeout;
    }

    @Override
    public void run() {
        super.run();
        while (true)
        {
            /* The idea is to sleep an interval time, and then reduce the interval time from all
            * the traffic light's time-to-timeout. After that, it checks if the time-to-timeout is 0 or below,
            * and if so, it switches the state. Every traffic light has its own timing. */
            try {
                Thread.sleep(this.timeInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < this.trafficLights.size(); i++) {
                TrafficLight light = this.trafficLights.get(i);
                /* reducing the time-till-timeout of the light and then checking if it got timeout (when it is 0) */
                light.setTimeUntilTimeout(light.getTimeUntilTimeout() - this.timeInterval);
                if (0 >= light.getTimeUntilTimeout()) {

                    light.switchState();
                    if (Color.GREEN == light.getGreenCircle().getFill()) {
                        light.setTimeUntilTimeout(greenTimeout);
                    } else {
                        light.setTimeUntilTimeout(redTimeout);
                    }
                }
            }
        }
    }
}
