public abstract class Person {
    // Instance variable
    private String name;
    private int age;
    private String gender;

    // Default Constructor
    public Person() {
        this.name = "";
        this.age = 0;
        this.gender = "";
    }

    // Constructor with parameters
    public Person(String name, int age, String gender) {
        setName(name);
        setAge(age);
        setGender(gender);
    }

    // Getter  Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) { //trim() to ensure that there are no extra spaces before or after
            throw new IllegalArgumentException("Name can not be empty");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be a non-negative number");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Gender can not be empty");
        }
        this.gender = gender;
    }
}
