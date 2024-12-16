import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Ride implements RideInterface {
    private String facilityName;
    private boolean isOpen;
    private int maxCapacity;
    private Employee operator;
    private Queue<Visitor> queue;   // waiting queue
    private LinkedList<Visitor> history;  // ride history
    private int numOfCycles; // Number of runs

    // Default Constructor
    public Ride() {
        this.facilityName = "";
        this.isOpen = true;
        this.maxCapacity = 0;
        this.operator = null;
        this.queue = new LinkedList<>();
        this.history = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // Constructor with parameters
    public Ride(String facilityName, boolean isOpen, int maxCapacity, Employee operator) {
        this.facilityName = facilityName;
        this.isOpen = isOpen;
        this.maxCapacity = maxCapacity;
        this.operator = operator;
        this.queue = new LinkedList<>();
        this.history = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // Getter Setter
    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        if (facilityName == null || facilityName.trim().isEmpty()) {
            throw new IllegalArgumentException("Facility name cannot be null or empty.");
        }
        this.facilityName = facilityName;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity < 0) {
            throw new IllegalArgumentException("最大容量不能为负数。");
        }
        this.maxCapacity = maxCapacity;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    // Implementation of interface methods
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (!isOpen) {  // !isOpen: ‘if isOpen is not true’, i.e., this expression returns true when isOpen is false, and vice versa.
            System.out.println("This facility is currently closed and visitors cannot be added.");
            return;
        }
        queue.add(visitor);
        System.out.println(visitor.getName() + " has joined the waiting queue.");
    }

    @Override
    public void removeVisitorFromQueue(Visitor visitor) {
        if (queue.remove(visitor)) {
            addVisitorToHistory(visitor); // Adding Visitors to Ride History
            System.out.println(visitor.getName() + " has been removed from the queue.");
        } else {
            System.out.println(visitor.getName() + " Not in the queue.");
        }
    }

    @Override
    public void printQueue() {
        System.out.println("Current visitor queue: ");
        for (Visitor visitor : queue) {
            System.out.println(visitor.getName());
        }
    }

    @Override
    public void runOneCycle() {
        if (!isOpen) {
            System.out.println("This facility is currently closed and not operational.");
            return;
        }
        if (queue.isEmpty()) {
            System.out.println("There are no visitors in the queue.");
            return;
        }


        int ridersToBoard = Math.min(maxCapacity, queue.size()); // Determination of the number of persons on board based on maximum capacity

        for (int i = 0; i < ridersToBoard; i++) {
            Visitor visitor = queue.poll(); // poll() is a method of the Queue interface in Java that removes an element from a queue and returns it to the head of the queue
            history.add(visitor);
            System.out.println(visitor.getName() + " is enjoying the rides.");
        }

        numOfCycles++; // Increased number of runs
        System.out.println("Current number of runs: " + numOfCycles);
    }

    @Override
    public void addVisitorToHistory(Visitor visitor) {
        history.add(visitor);
        System.out.println(visitor.getName() + " has been added to the ride history.");
    }

    // Added method to sort ride history
    public void sortRideHistory() {
        Collections.sort(history, new VisitorComparator());
        System.out.println("Ride history has been sorted by ticket type and length of visit.");
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) { // found will be true if the history contains visitors, and false if it does not.
        boolean found = history.contains(visitor); // contains is part of the Collection interface in Java and is used to check whether an element exists in a collection.
        System.out.println(found ? visitor.getName() + " in the ride history record." : visitor.getName() + " not in the ride history record.");
        return found; //? and : (ternary operators).The ternary operator is a concise conditional statement with the syntax: condition ? if_true : if_false.
    }

    @Override
    public int numberOfVisitors() {
        int count = history.size();
        System.out.println("Number of tourists in the ride history: " + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        if (history.isEmpty()) {
            System.out.println("Ride History has no current visitors.");
            return;
        }

        System.out.println("Ride history record: ");
        Iterator<Visitor> iterator = history.iterator(); // Iterator is an interface in Java for iterating over the elements of a collection
        while (iterator.hasNext()) {  // Fetches each Visitor object in the collection one by one in a loop until the entire collection is traversed.
            Visitor visitor = iterator.next();
            System.out.println(visitor.getName() + ", Ticket Type: " + visitor.getTicketType() + ", Visit Duration: " + visitor.getVisitDuration());
        }
    }
    // New method to export ride history to file
    public void exportRideHistory(String fileName) {
        BufferedWriter writer = null; // Declares a variable writer of type BufferedWriter and initialises the writer variable to null.
        try {
            writer = new BufferedWriter(new FileWriter(fileName)); // Creating a File Write Stream
            for (Visitor visitor : history) {
                writer.write(visitor.toString());
                writer.newLine(); // A separate line for each visitor's information
            }
            System.out.println("Ride history has been successfully written to the file: " + fileName);
        } catch (IOException e) { // If an IOException occurs in a try block, the program jumps to this catch block. e is an object of type IOException, which contains information about the exception.
            System.out.println("An error occurred while exporting the ride history: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close(); // Turning off the write stream
                }
            } catch (IOException e) {
                System.out.println("An error occurred while exporting the ride history: " + e.getMessage());
            }
        }
    }
    // Updating the method of importing ride history
    public void importRideHistory(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName)); // FileReader is the class used to read the contents of a file. fileName is the path to the file passed to it, and creates the file reading stream.
            String line; // This method reads a line of text from the BufferedReader and returns it as a string. If the end of the file is reached, null is returned.
            while ((line = reader.readLine()) != null) { // ! = null: this expression is used to check if an object reference is not null.
                try {
                    Visitor visitor = parseVisitorInfo(line);
                    if (visitor != null) {
                        history.add(visitor);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The data was formatted incorrectly and the rows could not be parsed: " + line);
                } catch (Exception e) {
                    System.out.println("An unknown error occurred while importing history: " + e.getMessage());
                }
            }
            System.out.println("The ride history has been successfully imported from the file.");

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file. " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    // Secondary method: extracting data from rows and creating visitor objects
    private Visitor parseVisitorInfo(String line) { // Used to parse the read row data and convert it into Visitor objects.
        // Split each item by comma first
        String[] parts = line.split(", "); // split() is a method of the String class that splits a string into parts based on a specified regular expression.
        // Declares an array of strings, parts, to store the substrings generated by the split() method.
        if (parts.length < 5) {
            System.out.println("Incomplete data format, skip the line: " + line);
            return null;  // Returns null if the data format is incorrect
        }

        try {
            // Extract the value of each item
            String name = extractValue(parts[0]);
            int age = Integer.parseInt(extractValue(parts[1])); // Integer.parseInt converts the string returned by extractValue(parts[1]) to an integer
            String gender = extractValue(parts[2]);
            String ticketType = extractValue(parts[3]);
            int visitDuration = Integer.parseInt(extractValue(parts[4]).replace(" minutes", "")); // The replace method replaces ‘ minutes’ in a string with an empty string ‘’.

            // Creates and returns a visitor object
            return new Visitor(name, age, gender, ticketType, visitDuration);
        } catch (NumberFormatException e) {
            System.out.println("Data format error, unable to parse rows: " + line);
            return null; // return null
        }
    }

    // Extract the value of each item
    private String extractValue(String field) {
        // The format of each field is ‘Key = Value’, split by ‘= ’
        return field.split("= ")[1].trim();
    }

    // Get ride history
    public LinkedList<Visitor> getHistory() {
        return history;
    }
}

