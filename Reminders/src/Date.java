import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.day, this.month, this.year);
    }

    @Override
    public boolean equals(Object o)
    {
        Date otherDate = (Date) o;
        return (this.year == otherDate.year &&
                this.month == otherDate.month &&
                this.day == otherDate.day);
    }
}
