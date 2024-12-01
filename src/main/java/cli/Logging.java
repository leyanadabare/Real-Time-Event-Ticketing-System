package cli;

import lombok.Getter;

import java.util.logging.*;
import java.io.IOException;

public class Logging {
    @Getter
    private static final Logger logger = Logger.getLogger("Ticket System Logger");

    static {
        try{
            FileHandler fileHandler = new FileHandler("ticketing_system.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);

            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers){
                rootLogger.removeHandler(handler);
            }

            rootLogger.addHandler(fileHandler);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.WARNING);
            consoleHandler.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(consoleHandler);

            System.out.println("Logging initialized successfully");

        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize logger" + e.getMessage());

        }
    }

}
