package cli;

public class TicketManException extends RuntimeException {
    public TicketManException(String message){
        super(message);
    }
    public TicketManException (String message, Throwable cause){
        super(message, cause);

    }
}
