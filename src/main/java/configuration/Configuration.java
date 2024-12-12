package configuration;


public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Getters and setters methods for encapsulation

    //Getter method for totaltickets
    public int getTotalTickets() {
        return totalTickets;
    }

    //Setter method for totaltickets
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    //Getter method for release rate
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    //Setter method for release rate
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    //Getter method for retrieval rate
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    //Setter method for retrieval rate
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    //Getter method for maximum ticket capacity of the pool
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    //Setter method for maximum ticket capacity of the pool
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
}