import java.util.Iterator;

public class Operations {
    public static <T extends Comparable<T>> SortedGroup<T> reduce(SortedGroup<T> sGroup, T comparedObject) {
        SortedGroup<T> newGroup = new SortedGroup<T>();

        for (Iterator<T> it = sGroup.iterator(); it.hasNext(); ) {
            T node = it.next();
            if (node.compareTo(comparedObject) > 0) {
                newGroup.add(node);
            }
        }

        return newGroup;
    }
}
