package com.leyana.oopfinalfinal.objects;

/**
 * Represents a customer who purchases tickets from the ticket pool.
 */
public class Customer implements Runnable {
    private int customerId;
    private TicketPool ticketPool;
    private int customerRetivelRate;
    private int quantity;

    public Customer(int customerId, TicketPool ticketPool, int customerRetivelRate, int quantity) {
        this.customerId = customerId;
        this.ticketPool = ticketPool;
        this.customerRetivelRate = customerRetivelRate;
        this.quantity = quantity;
    }

    //Getters and Setters
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public TicketPool getTicketPool() {
        return ticketPool;
    }
    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }
    public int getCustomerRetivelRate() {
        return customerRetivelRate;
    }
    public void setCustomerRetivelRate(int customerRetivelRate) {
        this.customerRetivelRate = customerRetivelRate;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * The main logic of the customer thread. Attempts to purchase tickets at the specified rate.
     */
    @Override
    public void run() {
        for (int i = 0; i < quantity; i ++){
            Ticket ticket = ticketPool.buyTickets(customerId);
            if (ticket != null){
                System.out.println("Customer: " + customerId + " retrieved " + ticket);
            }
            try{
                Thread.sleep(customerRetivelRate * 1000L);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                throw new RuntimeException("Customer interrupted: " + e.getMessage());
            }
        }
        System.out.println("Customer: " + customerId + " has finished purchasing tickets.");
    }
}


