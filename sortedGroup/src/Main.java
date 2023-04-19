import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        SortedGroup<Integer> group1 = new SortedGroup<>();

        group1.add(5);
        group1.add(2);
        group1.add(2);
        group1.add(2);
        group1.add(1);
        group1.add(4);

        group1.remove(2);
        SortedGroup<Integer> group2 = Operations.reduce(group1, 2);

        for (Iterator<Integer> it = group2.iterator(); it.hasNext(); ) {
            int i = it.next();
            System.out.println(i);
        }
//        for (Integer it : group1.iterator() ) {
////            int i = it.next();
////            System.out.println(i);
//            System.out.println(it);
//        }
    }
}