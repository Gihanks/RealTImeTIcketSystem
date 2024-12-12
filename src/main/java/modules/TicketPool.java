package modules;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TicketPool {

    private BlockingQueue<Integer> ticketPool;
    private int maxCapacity;

    public TicketPool(int totalTickets, int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.ticketPool = new ArrayBlockingQueue<>(maxCapacity);

        // Add the initial number of tickets to the pool by configuring parameter value
        for (int i = 0; i < totalTickets; i++) {
            ticketPool.offer(1); // Each ticket is represented as a simple integer (1)
        }
    }

    // method to add ticket to the pool
    public void addTickets(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            // Blocking method to ensure thread-safe addition of tickets
            if (ticketPool.size() < maxCapacity) {
                ticketPool.put(1);  // This will block if the queue is full
                System.out.println("Vendor added a ticket successfully. Available ticket count: " + ticketPool.size());
            } else {
                System.out.println("Max capacity has been reached. Vendor is currently waiting...");
            }
        }
    }

    // Buying a ticket from the ticketpool
    public void buyTickets() throws InterruptedException {
        // Blocking method to ensure thread-safe removal of tickets
        if (!ticketPool.isEmpty()) {
            ticketPool.take();  // This will block if the queue is empty
            System.out.println("Customer bought a ticket successfully. Available ticket count: " + ticketPool.size());
        } else {
            System.out.println("No tickets available now. Customer is currently...");
        }
    }

    public int getTicketCount() {
        return ticketPool.size();
    }
}
