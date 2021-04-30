package element2;
/**
 *
 * @author rabiul
 */
public class Student extends Person{
    String course;
    String year;
    

    public Student(String name, String gender, String course, String year) {
        super(name, gender);
        this.course = course;
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public String getYear() {
        return year;
    }
    
}
