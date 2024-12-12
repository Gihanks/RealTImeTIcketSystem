package InterfaceRun;

import exceptionHandling.InputValidate_Exception;
import modules.Customer;
import modules.TicketPool;
import modules.Vendor;
import configuration.Configuration;
import java.util.Scanner;

public class Main {

    //Defining variabled to run the main class
    private static TicketPool ticketPool;
    private static boolean isRun = false;
    private static Vendor vendor;
    private static Customer customer;
    private static Thread vendorThread;
    private static Thread customerThread;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        // Initializing paramters into fixed values
        int totalTickets = 0;
        int ticketReleaseRate = 0;
        int customerRetrievalRate = 0;
        int maxTicketCapacity = 0;


        // Welcome to note for the system
        System.out.println("                                                       ");
        System.out.println("------------------------------------------------------");
        System.out.println(".....Welcome to Real-time Ticket Management SYstem....");
        System.out.println("------------------------------------------------------");
        System.out.println("                                                       ");


        //Configuring the input value for Ticket release rate using get and set methods in configuration class
        while (config.getTicketReleaseRate() <= 0) {
            try {

                //Used custom exception methods to validate inputs
                int tempTicketReleaseRate = InputValidate_Exception.Positiveinputvalidation(scanner, "Please enter the ticket release rate here: ");
                config.setTicketReleaseRate(tempTicketReleaseRate); // Using setter to store the value in Configuration class
            } catch (InputValidate_Exception e) {
                System.out.println(e.getMessage());
            }
        }

        //Configuring the input value for Ticket retrieval rate using get and set methods in configuration class
        while (config.getCustomerRetrievalRate() <= 0) {
            try {

                //Used custom exception methods to validate inputs
                int tempCustomerRetrievalRate = InputValidate_Exception.Positiveinputvalidation(scanner, "Please enter the customer retrieval rate here: ");
                config.setCustomerRetrievalRate(tempCustomerRetrievalRate); // Using setter to store the value in Configuration class
            } catch (InputValidate_Exception e) {
                System.out.println(e.getMessage());
            }
        }



        //Configuring the input value for Maximum ticket capacity of ticketpool using get and set methods in configuration class
        while (config.getMaxTicketCapacity() <= 0) {
            try {

                //Used custom exception methods to validate inputs
                int maxCapacity = InputValidate_Exception.Positiveinputvalidation(scanner, "Please enter the max ticket capacity: ");
                config.setMaxTicketCapacity(maxCapacity);

            } catch (InputValidate_Exception e) {
                System.out.println(e.getMessage());
            }
        }

        //Configuring the input value for total ticket count using get and set methods in configuration class
        while (config.getTotalTickets() <= 0) {
            try {

                //Used custom exception methods to validate inputs
                int inputTickets = InputValidate_Exception.Positiveinputvalidation(scanner, "Please enter the total tickets: ");
                if (inputTickets > config.getMaxTicketCapacity()) {
                    System.out.println("Total tickets cannot be greater than max ticket capacity. Please enter a valid number.");
                } else {
                    config.setTotalTickets(inputTickets);// Pass value to Configuration
                }
            } catch (InputValidate_Exception e) {
                System.out.println(e.getMessage());
            }
        }


        //Displayed configured parameter values entered by the user.
        System.out.println("                                                       ");
        System.out.println("------------------------------------------------------");
        System.out.println("------------------------------------------------------");
        System.out.println("Maximum ticket capacity is " + config.getMaxTicketCapacity());
        System.out.println("Total ticket count is " + config.getTotalTickets());
        System.out.println("Ticket release rate is " + config.getTicketReleaseRate());
        System.out.println("Ticket retrieval rate is " + config.getCustomerRetrievalRate());
        System.out.println("------------------------------------------------------");
        System.out.println("------------------------------------------------------");


        // Initialize the ticketpool with configured total ticket count and maximum ticket capacity
        ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());


        //Display the menu to engage with ticket management system
        while (true) {
            System.out.println("\nReal Time Ticket Management System");
            System.out.println("1. Press 1 to start the System");
            System.out.println("2. Press 2 to stop the System");
            System.out.println("3. Press 3 to Show Ticket Status");
            System.out.println("4. Press 3 to exit the system");
            System.out.print("Please choose an option: ");
            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    if (!isRun) {
                        startSystem(config.getTicketReleaseRate(), config.getCustomerRetrievalRate());
                    } else {
                        System.out.println("System is already running.Please select an other option.");
                    }
                    break;
                case 2:
                    if (isRun) {
                        stopSystem();
                    } else {
                        System.out.println("System is not running .Please select an other option.");
                    }
                    break;
                case 3:
                    showTicketStatus();
                    break;
                case 4:
                    if (isRun) {
                        stopSystem();
                    }
                    System.out.println("------------------------------------------------------------");
                    System.out.println("---Thank you for using Real-time Ticket Management System---");
                    System.out.println("------------------------------------------------------------");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection. Please select a valid option.");
            }
        }
    }

    //Method to start the system
    private static void startSystem(int ticketReleaseRate, int customerRetrievalRate) {
        System.out.println("-----Real-Time Ticket System started----");

        // Create and start Vendor and Customer threads
        vendor = new Vendor(ticketPool, ticketReleaseRate);
        customer = new Customer(ticketPool, customerRetrievalRate);

        vendorThread = new Thread(vendor);
        customerThread = new Thread(customer);

        vendorThread.start();
        customerThread.start();

        // Mark the system as running
        isRun = true;
    }

    //Method to stop the system
    private static void stopSystem() {
        System.out.println(".....Ticket system has been stopped.....");

        // Stop the customer and vendor threads
        vendor.vendThreadStop();
        customer.custThreadStop();

        // Wait for threads to finish
        try {
            vendorThread.join();
            customerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Preserve the interrupt status
        }

        // System is stopped
        isRun = false;
    }

    //Method to show the balance ticket count of the ticketpool
    private static void showTicketStatus() {
        System.out.println("  " + ticketPool.getTicketCount() + " number of tickets are remaining.");
    }
}
