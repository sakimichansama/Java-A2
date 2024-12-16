import java.util.Queue;
import java.util.LinkedList;

public class AssignmentTwo {
    public static void main(String[] args) {
        partThree();  //Only static methods can be called
        partFourA();
        partFourB();
        partFive();
        partSix();
        partSeven();
    }
    public static void partThree(){
        Ride ride = new Ride();

        // Create and add at least 5 visitors
        ride.addVisitorToQueue(new Visitor("Jack", 25, "Male", "Normal", 120));
        ride.addVisitorToQueue(new Visitor("Sharon", 20, "Male", "VIP", 90));
        ride.addVisitorToQueue(new Visitor("Benny", 22, "Female", "Normal", 60));
        ride.addVisitorToQueue(new Visitor("Leo", 20, "Male", "VIP", 150));
        ride.addVisitorToQueue(new Visitor("Lee", 18, "Female", "Normal", 45));

        // Print current queue
        ride.printQueue();

        // Remove
        ride.removeVisitorFromQueue(new Visitor("Lee", 18, "Female", "Normal", 45));

        // Print again
        ride.printQueue();
    }
    public static void partFourA(){
        Ride ride = new Ride();

        // Create visitor and add to queue
        ride.addVisitorToHistory(new Visitor("Nehemia", 25, "Female", "Normal", 120));
        ride.addVisitorToHistory(new Visitor("Jason", 30, "Male", "VIP", 180));
        ride.addVisitorToHistory(new Visitor("Tom", 22, "Male", "Normal", 90));
        ride.addVisitorToHistory(new Visitor("Sherly", 27, "Male", "Normal", 150));
        ride.addVisitorToHistory(new Visitor("Ben", 29, "Female", "Normal", 60));

        // Check if certain visitors are in the ride history
        Visitor searchVisitor = new Visitor("Nehemia", 25, "Female", "Normal", 120);
        boolean isPresent = ride.checkVisitorFromHistory(searchVisitor);
        System.out.println(searchVisitor.getName() + " in Ride History: " + isPresent);

        // Print the number of visitors in the current ride history
        ride.numberOfVisitors();

        // Print all visitors in the collection
        ride.printRideHistory();
    }
    public static void partFourB(){
        // Creating a Ride Object
        Ride ride = new Ride("Roller Coaster", true, 10, new Employee("Noah", 20, "Male", "Operator", 50000));

        ride.addVisitorToHistory(new Visitor("Nehemia", 25, "Female", "Normal", 120));
        ride.addVisitorToHistory(new Visitor("Jason", 30, "Male", "VIP", 180));
        ride.addVisitorToHistory(new Visitor("Tom", 22, "Male", "Normal", 90));
        ride.addVisitorToHistory(new Visitor("Sherly", 27, "Male", "Normal", 150));
        ride.addVisitorToHistory(new Visitor("Ben", 29, "Female", "Normal", 60));

        // Print Unsorted Ride History
        System.out.println("Ride history (unsorted): ");
        ride.printRideHistory();

        // Sort Ride History
        ride.sortRideHistory();

        // Print the ride history again to show that it is sorted
        System.out.println("Ride history (sorted): ");
        ride.printRideHistory();
    }
    public static void partFive(){
        // Creating a Ride Object
        Ride ride = new Ride("Roller Coaster", true, 2, new Employee("Noah", 20, "Male", "Operator", 50000));

        // Add at least 10 visitors to the queue
        ride.addVisitorToQueue(new Visitor("Nehemia", 25, "Female", "Normal", 120));
        ride.addVisitorToQueue(new Visitor("Jason", 30, "Male", "VIP", 180));
        ride.addVisitorToQueue(new Visitor("Tom", 22, "Male", "Normal", 90));
        ride.addVisitorToQueue(new Visitor("Sherly", 27, "Male", "Normal", 150));
        ride.addVisitorToQueue(new Visitor("Ben", 29, "Female", "Normal", 60));
        ride.addVisitorToQueue(new Visitor("Jack", 25, "Male", "Normal", 120));
        ride.addVisitorToQueue(new Visitor("Sharon", 20, "Male", "VIP", 90));
        ride.addVisitorToQueue(new Visitor("Benny", 22, "Female", "Normal", 60));
        ride.addVisitorToQueue(new Visitor("Leo", 20, "Male", "VIP", 150));
        ride.addVisitorToQueue(new Visitor("Lee", 18, "Female", "Normal", 45));

        // Print all visitors in the queue
        ride.printQueue();

        // Run a cycle
        ride.runOneCycle();

        // Prints all visitors in the queue after a cycle has run
        System.out.println("Visitor queue after running a cycle: ");
        ride.printQueue();

        // Print Ride History
        ride.printRideHistory();
    }
    public static void partSix(){
        // Creating a Ride Object
        Ride ride = new Ride("Roller Coaster", true, 10, new Employee("Noah", 20, "Male", "Operator", 50000));

        ride.addVisitorToHistory(new Visitor("Nehemia", 25, "Female", "Normal", 120));
        ride.addVisitorToHistory(new Visitor("Jason", 30, "Male", "VIP", 180));
        ride.addVisitorToHistory(new Visitor("Tom", 22, "Male", "Normal", 90));
        ride.addVisitorToHistory(new Visitor("Sherly", 27, "Male", "Normal", 150));
        ride.addVisitorToHistory(new Visitor("Ben", 29, "Female", "Normal", 60));

        // Export Ride History to File
        ride.exportRideHistory("ride_history.txt");
    }
    public static void partSeven(){

        Ride ride = new Ride("Roller Coaster", true, 10, new Employee("Noah", 20, "Male", "Operator", 50000));

        ride.importRideHistory("ride_history.txt");

        ride.numberOfVisitors();

        ride.printRideHistory();
    }
}
