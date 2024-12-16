import java.util.Comparator;
// The Comparator interface is another interface for comparing objects that allows for custom sorting rules, often used when multiple sorting criteria are required.
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) { // compare is an operation used to compare objects, and is usually found in the Comparator or Comparable interfaces.
        // Firstly, a comparison is made according to the type of ticket: VIP is better than Normal.
        int ticketTypeCompare = Integer.compare(getTicketPriority(v1.getTicketType()), getTicketPriority(v2.getTicketType()));

        if (ticketTypeCompare != 0) {
            return ticketTypeCompare; // If tickets are not equal, sorted by ticket type
        } else {
            // If tickets are equal, sorted by length of visit from smallest to largest
            return Integer.compare(v1.getVisitDuration(), v2.getVisitDuration());
        }
    }

    // Define priority for ticket types, VIP > Normal
    private int getTicketPriority(String ticketType) {
        switch (ticketType) {
            case "VIP":
                return 1;
            case "Normal":
                return 2;
            default:
                throw new IllegalArgumentException("Unknown ticket type: " + ticketType);
        }
    }
}
