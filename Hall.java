
package element2;
/**
 *
 * @author rabiul
 */

public class Hall {
    
    String name;
    Student student;
    Employee employee;
    String type;

    public Hall(String name,  Employee employee) {
        this.name = name;
        this.employee = employee;
        this.type ="Employee";
    }
    
    public Hall(String name,  Student student) {
        this.name = name;
        this.student = student;
        this.type ="Student";
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Student getStudent() {
        return student;
    }

    public Employee getEmployee() {
        return employee;
    }
   
    
}
