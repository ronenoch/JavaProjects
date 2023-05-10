import java.util.ArrayList;

public class MyTimer extends Thread{

    private ArrayList<TrafficLight> trafficLights;

    public MyTimer(ArrayList<TrafficLight> trafficLights) {
        this.trafficLights = trafficLights;
    }

    @Override
    public void run() {
        super.run();
        while (true)
        {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < this.trafficLights.size(); i++) {
                this.trafficLights.get(i).switchState();
            }
        }
    }
}
