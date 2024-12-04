package cli;

import java.math.BigDecimal;


public class Ticket {
    private int ticketId;
    private String eventName;
    private BigDecimal ticketPrice;

    public Ticket(int ticketId, String ticketName, BigDecimal ticketPrice) {
        this.ticketId = ticketId;
        this.eventName = ticketName;
        this.ticketPrice = ticketPrice;
    }
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

    @Override
    public String toString(){

        return "Ticket: " + '\n'
                + "Ticket ID = " + ticketId + '\n'
                + "Event Name = " + eventName + '\n'
                + "Ticket Price = " + ticketPrice;
    }
}



