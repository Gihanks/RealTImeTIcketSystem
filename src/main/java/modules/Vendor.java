package modules;

public class Vendor implements Runnable {

    //Declaring variables for vendor class
    private TicketPool ticketPool;
    private int ticketReleaseRate;
    private volatile boolean running = true;  // Volatile ensures the flag is visible to other threads

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        while (running) {
            try {
                // Add tickets to the ticketpool with configured release rate
                ticketPool.addTickets(1);  // Add one ticket
                Thread.sleep(ticketReleaseRate * 500L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("!!!Vendor thread has been stopped.!!!");
    }

    // Method to stop the thread
    public void vendThreadStop() {
        running = false;
    }
}
