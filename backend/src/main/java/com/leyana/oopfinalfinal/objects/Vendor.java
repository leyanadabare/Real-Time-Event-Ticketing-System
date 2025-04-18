package com.leyana.oopfinalfinal.objects;
import java.math.BigDecimal;

/**
 * Represents a vendor who releases tickets into the ticket pool.
 */
public class Vendor implements Runnable {
    private int vendorId;
    private int ticketsReleaseRate;
    private TicketPool ticketPool;
    private int totalTickets;

    public Vendor(int vendorId, int ticketsReleaseRate, TicketPool ticketPool, int totalTickets) {
        this.vendorId = vendorId;
        this.ticketsReleaseRate = ticketsReleaseRate;
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
    }

    // Getters and setters
    public int getVendorId() {
        return vendorId;
    }
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
    public int getTicketsReleaseRate() {
        return ticketsReleaseRate;
    }
    public void setTicketsReleaseRate(int ticketsReleaseRate) {
        this.ticketsReleaseRate = ticketsReleaseRate;
    }
    public TicketPool getTicketPool() {
        return ticketPool;
    }
    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }
    public int getTotalTickets() {
        return totalTickets;
    }
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    /**
     * The main logic of the vendor thread. Releases tickets at the specified rate.
     */
    @Override
    public void run() {
        for (int i = 1; i <= totalTickets; i++){
            Ticket ticket = new Ticket(i,"Event: " + vendorId, new BigDecimal("1000"));
            ticketPool.addTickets(ticket);
            try{
                // Sleep for the specified release rate
                Thread.sleep(ticketsReleaseRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Vendor Interrupted: " + e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName() + " has finished adding tickets.");
    }
}




