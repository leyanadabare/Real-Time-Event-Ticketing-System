package com.leyana.oopfinalfinal.objects;
import java.math.BigDecimal;

/**
 * Represents a single ticket with an ID, event name, and price.
 */
public class Ticket {
    private int ticketId;
    private String eventName;
    private BigDecimal ticketPrice;
    public Ticket(int ticketId, String eventName, BigDecimal ticketPrice) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    //Getters and Setters
    public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public String getEventNameName() {
        return eventName;
    }
    public void setEventNametName(String ticketName) {
        this.eventName = ticketName;
    }
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * Returns a string representation of the ticket, including its ID, event name, and price.
     * @return a string representation of the ticket
     */
    @Override
    public String toString(){
        return "Ticket: " + '\n'
                + "Ticket ID = " + ticketId + '\n'
                + "Event Name = " + eventName + '\n'
                + "Ticket Price = " + ticketPrice;
    }
}



