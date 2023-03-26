import java.util.Date;
public class Smoke extends Alarm {
    private String operatorName;
    public Smoke(String address, String operatorName) throws BadAlarm {
        super(new Date(), address);
        if (null == address) {
            throw new BadAlarm("-1", "address is null");
        }
        this.operatorName = operatorName;
    }
    @Override
    public void action() {
        System.out.println(("Smoke{" +
                "operatorName='" + this.operatorName + '\'' +
                ", address='" + this.address + '\'' +
                ", start time=" + this.startTime +
                '}'));
    }

    public String getOperatorName() {
        return this.operatorName;
    }

}
