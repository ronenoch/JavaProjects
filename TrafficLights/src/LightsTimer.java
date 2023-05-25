import java.util.ArrayList;

public class LightsTimer extends Thread{

    private ArrayList<TrafficLight> trafficLights;
    private int timeout;

    public LightsTimer(ArrayList<TrafficLight> trafficLights, int timeout) {
        this.trafficLights = trafficLights;
        this.timeout = timeout;
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
                this.trafficLights.get(i).switchState();
            }
        }
    }
}
