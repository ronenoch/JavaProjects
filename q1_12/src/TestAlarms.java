import java.util.ArrayList;
public class TestAlarms {
    public static void main(String[] args) throws BadAlarm {
        System.out.println("Hello world!");
        ArrayList<Alarm> alarms = new ArrayList<Alarm>();

        alarms.add(new Smoke("address 1", "Name1"));
        alarms.add(new Elevator("address 1", 3));
        alarms.add(new Fire("address 2", "Name2"));

        process(alarms);
    }

    public static void process(ArrayList<Alarm> alarms) {
        int smoke_alarms_counter = 0;
        for (Alarm alarm : alarms) {
            alarm.action();
            if (alarm instanceof Smoke) {
                smoke_alarms_counter++;
            }
            else if (alarm instanceof Elevator) {
                ((Elevator) alarm).reset();
            }
        }
        System.out.println("there were " + String.valueOf(smoke_alarms_counter) + " Smoke alarms");
    }
}