/**
 * Manages a pool of tickets for an event, allowing customers to reserve tickets
 * in a thread-safe manner.
 */
public class TicketPool {
    /** The number of tickets currently available for reservation. */
    private int availableTickets;

    /**
     * Initializes the TicketPool with a given number of total tickets.
     *
     * @param totalTickets the initial count of tickets available
     */
    public TicketPool(int totalTickets) {
        this.availableTickets = totalTickets;
    }

    /**
     * Attempts to reserve a ticket for the specified customer.
     * <p>
     * This method is synchronized to ensure that only one thread can decrement
     * the available ticket count at a time, preventing overselling.
     *
     * @param customerName the name of the customer attempting to reserve a ticket
     * @return a new Ticket object if successful; null if no tickets remain
     */
    public synchronized Ticket reserveTicket(String customerName) {
        // Check if tickets are still available
        if (availableTickets > 0) {
            // Decrement the count to reflect reservation
            availableTickets--;
            // Log the reservation and remaining count
            System.out.println(customerName
                    + " reserved a ticket. Tickets left: "
                    + availableTickets);
            // Create and return a Ticket with the current ID and event name
            // Here, we use the remaining tickets count as the ticket ID for simplicity
            return new Ticket(availableTickets, "Concert");
        } else {
            // Inform that no tickets are left for this customer
            System.out.println(customerName
                    + " tried to reserve but no tickets are left.");
            return null;
        }
    }
}