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

        SortedGroup<Student> groupStudent = new SortedGroup<>();

        groupStudent.add(new Student("ron1", "123", 100));
        groupStudent.add(new Student("ron2", "1234", 60));
        groupStudent.add(new Student("ron3", "12345", 59));
        groupStudent.add(new Student("ron4", "123456", 61));

        Student someStudent = new Student("new", "54321", 78);
        Student comparedStudent = new Student("compared", "4321", 60);

        groupStudent.add(someStudent);

        for (Iterator<Student> it = groupStudent.iterator(); it.hasNext(); ) {
            Student i = it.next();
            System.out.println(i);
        }

        groupStudent.remove(someStudent);

        System.out.println("after remove");
        for (Iterator<Student> it = groupStudent.iterator(); it.hasNext(); ) {
            Student i = it.next();
            System.out.println(i);
        }

        SortedGroup<Student> groupReduce = Operations.reduce(groupStudent, comparedStudent);

        System.out.println("after reduce");
        for (Iterator<Student> it = groupReduce.iterator(); it.hasNext(); ) {
            Student i = it.next();
            System.out.println(i);
        }


//        for (Integer it : group1.iterator() ) {
////            int i = it.next();
////            System.out.println(i);
//            System.out.println(it);
//        }
    }
}