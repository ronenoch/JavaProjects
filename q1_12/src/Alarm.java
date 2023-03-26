import java.util.Date;

public abstract class Alarm {
    protected final String address;
    protected final Date startTime;
    public Alarm(Date date, String address) {
        this.startTime = date;
        this.address = address;
    }

    public abstract void action();

//    public String getAddress() {
//        return address;
//    }
//    public Date getStartTime() {
//        return startTime;
//    }
}