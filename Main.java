import java.util.ArrayList;
import java.util.List;

/**
 * Entry point for the ticket reservation simulation.
 *
 * Creates a pool of tickets and spawns multiple virtual threads,
 * each representing a customer attempting to reserve a ticket.
 */
public class Main {

    /**
     * Main method: sets up the ticket pool and customer threads.
     *
     * @param args command-line arguments (unused)
     * @throws InterruptedException if the current thread is interrupted while waiting for others
     */
    public static void main(String[] args) throws InterruptedException {
        // Total number of tickets available in the pool
        int ticketTotal = 10;
        // Total number of customers that will attempt to reserve tickets
        int customerTotal = 25;

        // Initialize the shared TicketPool with the specified number of tickets
        TicketPool ticketPool = new TicketPool(ticketTotal);

        // List to keep track of all customer threads for later joining
        List<Thread> threads = new ArrayList<>();

        // Create and start a virtual thread for each customer
        for (int i = 1; i < customerTotal; i++) {
            // Instantiate a new Customer with a unique name
            Customer customer = new Customer("Customer " + i);
            // Provide the shared TicketPool to the customer
            customer.addTicketPool(ticketPool);

            // Start a new virtual thread to run the customer's reservation logic
            Thread thread = Thread.startVirtualThread(customer);
            threads.add(thread);
        }

        // Wait for each customer thread to complete before exiting main
        for (Thread thread : threads) {
            try {
                thread.join();  // Block until this thread finishes
            } catch (InterruptedException e) {
                // Handle potential interruption while waiting
                System.out.println(thread.getName() + " interrupted: " + e.getMessage());
                // Optionally restore the interrupt status:
                Thread.currentThread().interrupt();
            }
        }

        // All customer threads have finished reservation attempts
        System.out.println("All threads finished.");
    }
}