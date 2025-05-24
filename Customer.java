/**
 * Represents a customer who can reserve tickets from a shared TicketPool.
 * Implements Runnable so that multiple Customer instances can run in parallel threads.
 */
public class Customer implements Runnable {
    /** The name of this customer. */
    private final String customerName;

    /** Reference to the pool from which tickets will be reserved. */
    private TicketPool ticketPool;

    /**
     * Constructs a new Customer with the given name.
     *
     * @param customerName the name of the customer
     */
    public Customer(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Associates this customer with a TicketPool.
     *
     * @param ticketPool the shared TicketPool instance
     */
    public void addTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    /**
     * Returns the customer's name.
     *
     * @return the name of this customer
     */
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * Attempts to reserve a ticket from the associated TicketPool.
     * If a ticket is successfully reserved, prints the ticket ID to standard output.
     * This method is invoked when the Customer is run as a thread.
     */
    @Override
    public void run() {
        // Try to reserve a ticket for this customer
        Ticket ticket = this.ticketPool.reserveTicket(this.customerName);

        // If a ticket was returned, print out the assigned ticket ID
        if (ticket != null) {
            System.out.println(this.customerName + "'s ticket id is " + ticket.getId());
        }
    }
}