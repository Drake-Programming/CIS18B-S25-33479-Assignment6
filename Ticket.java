/**
 * Represents a ticket for a specific event.
 */
public class Ticket {
    /** Unique identifier for this ticket. */
    private final int id;
    /** Name of the event associated with this ticket. */
    private final String event;

    /**
     * Constructs a new Ticket with the given identifier and event name.
     *
     * @param id    the unique identifier for this ticket
     * @param event the name of the event
     */
    public Ticket(int id, String event) {
        this.id = id;
        this.event = event;
    }

    /**
     * Returns the unique identifier of this ticket.
     *
     * @return the ticket ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the event for which this ticket was issued.
     *
     * @return the event name
     */
    public String getEvent() {
        return event;
    }
}