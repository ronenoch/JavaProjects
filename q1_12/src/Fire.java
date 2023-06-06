import java.util.Date;

public class Fire extends Smoke {
    private boolean active;
    public Fire(String address, String operatorName) throws BadAlarm {
        super(address, operatorName);
        this.active = true;
    }

    @Override
    public void action() {
        this.active = false;
        /* I am not using the super method because I wanted to add 'Fire' or 'smoke' at
        the beginning of the print. The code duplication is small and I prefer to have my own print.*/
        System.out.println("Fire: operatorName='" + this.operatorName + '\'' +
                ", address='" + this.address + '\'' +
                ", start time=" + this.startTime);
    }
}
