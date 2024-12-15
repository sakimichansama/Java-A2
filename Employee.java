public class Employee extends Person {
    // Example variables suitable for theme park employees
    private String position;
    private double salary;

    // Default Constructor
    public Employee() {
        super(); // Call the default constructor of the parent class
        this.position = "";
        this.salary = 0.0;
    }

    // Constructor with parameters
    public Employee(String name, int age, String gender, String position, double salary) {
        super(name, age, gender); // Calling a parent class constructor with parameters
        this.position = position;
        this.salary = salary;
    }

    // Getter  Setter
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position == null || position.trim().isEmpty()) { //trim() to ensure that there are no extra spaces before or after
            throw new IllegalArgumentException("Position cannot be null or empty.");
        }
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary must be a non-negative number.");
        }
        this.salary = salary;
    }
}

