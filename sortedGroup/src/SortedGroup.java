import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class SortedGroup<T extends Comparable<T>> {
    private ArrayList<T> arrayList;

    public SortedGroup() {
        this.arrayList = new ArrayList<T>();
    }

    public ArrayList<T> getArrayList() {
        return this.arrayList;
    }

    public void add(T newNode) {
        int compareValue = 0;
        int i = 0;

        for (T node : this.arrayList) {
            compareValue = newNode.compareTo(node);
            if (compareValue <= 0) {
                this.arrayList.add(i, newNode);
                return;
            }
            i++;
        }

        this.arrayList.add(newNode);

    }

    public void remove(T node) {
        int compareValue = 0;

        for (T currentNode : this.arrayList) {
            compareValue = currentNode.compareTo(node);
            if (compareValue == 0) {
                this.arrayList.remove(currentNode);
                break;
            }
        }
    }
    public Iterator<T> iterator() {
        return this.arrayList.iterator();
    }

}
