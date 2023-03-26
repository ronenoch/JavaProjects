import java.util.Date;

public class Elevator extends Alarm {
    private int floor;

    public Elevator(String address, int floor) {
        super(new Date(), address);
        this.floor = floor;
    }


    @Override
    public void action() {
        System.out.println("floor number='" + String.valueOf(this.floor) + '\'' +
                ", address='" + this.address + '\'' +
                ", start time=" + this.startTime);
    }

    public void reset() {
        this.floor = 0;
    }
}
