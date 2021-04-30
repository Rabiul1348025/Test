package element2;

/**
 *
 * @author rabiul
 */

public class Employee extends Person{
    
    int salary;
    String role;

    public Employee(String name, String gender, String Role, int Salary) {
        super(name, gender);
        this.role = Role;
        this.salary = Salary;
    }

    public int getSalary() {
        return salary;
    }

    public String getRole() {
        return role;
    }
    
}
