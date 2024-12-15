public class Visitor extends Person {
    private String ticketType;
    private int visitDuration;

    // Default Constructor
    public Visitor() {
        super(); // Calls the default constructor of the Person class
        this.ticketType = "Normal";
        this.visitDuration = 0;
    }

    // Constructor with parameters
    public Visitor(String name, int age, String gender, String ticketType, int visitDuration) {
        super(name, age, gender); // Calls the constructor of the Person class
        this.ticketType = ticketType;
        this.visitDuration = visitDuration;
    }

    // Getter Setter
    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        if (ticketType == null || ticketType.trim().isEmpty()) { //trim() to ensure that there are no extra spaces before or after
            throw new IllegalArgumentException("Ticket type cannot be null or empty.");
        } // throw new IllegalArgumentException and in the employee class has been added to the comments, will not be repeated
        this.ticketType = ticketType;
    }

    public int getVisitDuration() {
        return visitDuration;
    }

    public void setVisitDuration(int visitDuration) {
        if (visitDuration < 0) {
            throw new IllegalArgumentException("Visit duration must be a non-negative number.");
        }
        this.visitDuration = visitDuration;
    }
    // Override the toString method to provide more meaningful output, otherwise it will output a hash code
    @Override
    public String toString() {
        return "Visitor: Name = " + getName() + ", Age = " + getAge() + ", Gender = " + getGender() +
                ", Ticket Type = " + ticketType + ", Visit Duration = " + visitDuration + " minutes";
    }
}