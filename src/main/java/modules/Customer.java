package modules;

public class Customer implements Runnable {

    //Declaring variables for customer class
    private TicketPool ticketPool;
    private int customerRetrievalRate;  // in seconds
    private volatile boolean running = true;


    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (running) {
            try {
                // Remove a ticket from the pool
                ticketPool.buyTickets();
                // Thread sleep (by milliseconds)
                Thread.sleep(customerRetrievalRate * 500L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("!!!Customer thread has been stopped.!!!");

    }

    //To stop the customer thread running
    public void custThreadStop() {
        running = false;
    }
}


