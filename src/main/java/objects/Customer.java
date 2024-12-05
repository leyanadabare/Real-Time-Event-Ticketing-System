package objects;

public class Customer implements Runnable {
    private int customerId;
    private TicketPool ticketPool;
    private int customerRetivelRate;
    private int quantity;

    //@Autowired
    public Customer(int customerId, TicketPool ticketPool, int customerRetivelRate, int quantity) {
        this.customerId = customerId;
        this.ticketPool = ticketPool;
        this.customerRetivelRate = customerRetivelRate;
        this.quantity = quantity;
    }


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

    //@Scheduled(fixedRateString = "#{@configuration.customerRetrieveRate * 1000}")
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


