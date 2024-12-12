package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modules.TicketPool;
import configuration.Configuration;

public class ApplicationController {

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField ticketReleaseRateField;

    @FXML
    private TextField customerRetrievalRateField;

    @FXML
    private TextField maxTicketCapacityField;

    @FXML
    private TextField totalTicketsField;

    @FXML
    private TextArea logArea;

    private TicketPool backend;
    private Configuration config;

    public ApplicationController() {
        this.backend = new TicketPool(0, 0);  // Example values for totalTickets and maxCapacity
        this.config = new Configuration();
    }

    @FXML
    private void startSystem() {
        try {
            // Validate and set the configuration
            int ticketReleaseRate = Integer.parseInt(ticketReleaseRateField.getText());
            int customerRetrievalRate = Integer.parseInt(customerRetrievalRateField.getText());
            int maxTicketCapacity = Integer.parseInt(maxTicketCapacityField.getText());
            int totalTickets = Integer.parseInt(totalTicketsField.getText());

            // Set configuration
            config.setTicketReleaseRate(ticketReleaseRate);
            config.setCustomerRetrievalRate(customerRetrievalRate);
            config.setMaxTicketCapacity(maxTicketCapacity);
            config.setTotalTickets(totalTickets);

            // Initialize the TicketPool with configuration values
            backend = new TicketPool(totalTickets, maxTicketCapacity);

            logArea.appendText("System Started\n");

            // Optionally, start vendor and customer threads if needed
            // backend.startSystem();  // Uncomment if you want to start threads (not implemented here)

        } catch (NumberFormatException e) {
            logArea.appendText("Invalid input. Please enter valid numbers.\n");
        }
    }

    @FXML
    private void stopSystem() {
        // Stop the system, stop threads if applicable
        // backend.stopSystem();  // Uncomment if you have a stop system method implemented

        logArea.appendText("System Stopped\n");
    }

    @FXML
    private void exitSystem() {
        logArea.appendText("Exiting the system...\n");
        System.exit(0);
    }
}
