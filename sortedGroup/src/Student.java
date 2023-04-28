public class Student implements Comparable<Student> {
    private String name;
    private String id;
    private int grade;

    public Student(String name, String id, int grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    @Override
    public int compareTo(Student other) {
        if (this.grade == other.grade) {
            return 0;
        }
        return (this.grade > other.grade) ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Student {" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", grade=" + grade +
                '}';
    }
}
