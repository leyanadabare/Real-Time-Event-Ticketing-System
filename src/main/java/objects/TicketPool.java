package objects;
import java.util.LinkedList;
import java.util.Queue;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Component
public class TicketPool {
    private Queue<Ticket> tickets = new LinkedList<>();
    private int maxCapacity;
    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        logger.info("TicketPool initialized with max capacity: " + maxCapacity);
    }

    public Queue<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(Queue<Ticket> tickets) {
        this.tickets = tickets;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public synchronized int getSize() {
        return tickets.size();
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Async
    public synchronized void addTickets(Ticket ticket) {
        try {
            while (tickets.size() >= maxCapacity) {
                logger.warn("Ticket pool is full. Vendor is waiting to add tickets.");
                System.out.println("Ticket pool is full. Vendor is waiting to add tickets");
                wait();
            }
            tickets.add(ticket);
            logger.info("Vendor added a ticket: " + ticket.getTicketId());
            System.out.println("Vendor added a ticket: " + ticket.getTicketId() + " Ticket pool size: " + tickets.size());
            notifyAll();
        } catch (InterruptedException e) {
            logger.error("Error while adding tickets" + e);
            System.out.println("Error while adding tickets: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    @Async
    public synchronized Ticket buyTickets(int customerId) {
        try {
            while (tickets.isEmpty()){
                System.out.println("Ticket pool is empty. Customer is waiting for tickets to be available");
                logger.warn("Ticket pool is empty. Customer " + customerId + " is waiting.");
                wait();
            }
            Ticket ticket = tickets.poll();
            System.out.println("Customer purchased ticket: " + '\n' +
                    "Ticket ID = " + ticket.getTicketId() + '\n' +
                    "Event Name: " + ticket.getEventNameName() + '\n' +
                    "Ticket Price: " + ticket.getTicketPrice() + '\n' +
                    "Remaining Tickets: " + tickets.size());
            logger.info("Customer " + customerId + " purchased ticket: " + ticket.getTicketId());
            notifyAll();
            return ticket;
        } catch (InterruptedException e){
            System.out.println("Error while buying tickets: " + e.getMessage());
            logger.error("Error while buying tickets", e);
            Thread.currentThread().interrupt();
            return null;
        }
    }
}







