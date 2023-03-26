import java.util.Date;

public class Fire extends Alarm {
    private boolean active;
    private String operatorName;
    public Fire(String address, String operatorName) {
        super(new Date(), address);
        this.active = true;
        this.operatorName = operatorName;
    }

    @Override
    public void action() {
        this.active = false;
        System.out.println("operatorName='" + this.operatorName + '\'' +
                ", address='" + this.address + '\'' +
                ", start time=" + this.startTime);
    }
}
