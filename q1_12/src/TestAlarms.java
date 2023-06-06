import java.util.ArrayList;
public class TestAlarms {
    public static void main(String[] args) throws BadAlarm {
        System.out.println("Hello world!");
        ArrayList<Alarm> alarms = new ArrayList<Alarm>();

        alarms.add(new Smoke("address 1", "Name1"));
        alarms.add(new Fire("address 3", "Name4"));
        alarms.add(new Elevator("address 1", 3));
        alarms.add(new Fire("address 2", "Name2"));
        alarms.add(new Smoke("address 3", "Name1"));
        alarms.add(new Elevator("address 1", 4));

        process(alarms);
    }

    /**
     * process the alarms, one by one and handles every one correctly.
     * @param alarms the alarms to process
     */
    public static void process(ArrayList<Alarm> alarms) {
        int smokeAlarmsCounter = 0;
        for (Alarm alarm : alarms) {
            alarm.action();
            if (alarm instanceof Smoke) {
                smokeAlarmsCounter++;
            }
            else if (alarm instanceof Elevator) {
                ((Elevator) alarm).reset();
            }
        }
        System.out.println("there were " + String.valueOf(smokeAlarmsCounter) + " Smoke alarms");
    }
}