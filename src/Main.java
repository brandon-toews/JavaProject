// Import the necessary libraries
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Create the Main class
public class Main extends JFrame {
    // Create the GUI components
    private JLabel jlLabel;
    private JButton addFlight;
    private JPanel mainPanel;
    private JList listFlights;
    private JPanel viewFlights;
    private JPanel addFlights;
    private JSplitPane viewAddSplit;
    private JLabel depLabel;
    private JComboBox depComboBox;
    private JLabel arvLabel;
    private JComboBox arvComboBox;
    private DatePicker dateDatePicker;
    private JScrollPane scrollFlights;
    private JPanel viewFlightInfo;
    private JLabel infoLabel;
    private JLabel dateLabel;
    private JTextField departureTextField;
    private JLabel depInfoLabel;
    private JTextField arvTextField;
    private JLabel arvInfoLabel;
    private JTextField flightTextField;
    private JLabel flightLabel;
    private JTextField dateTextField;
    private JLabel dateInfoLabel;
    private JComboBox classComboBox;
    private DefaultComboBoxModel seatClassModel;
    private JLabel seatClassLabel;
    private JComboBox seatsComboBox;
    private DefaultComboBoxModel seatsModel;
    private JLabel seatLabel;
    private JComboBox waitComboBox;
    private DefaultComboBoxModel waitModel;
    private JLabel waitLabel;
    private JButton cancelPassButton;
    private JButton cancelWaitButton;
    private JPanel viewInfo;
    private JLabel passStatusLabel;
    private JPanel viewPassStatus;
    private JLabel fNameLabel;
    private JTextField firstNameTextField;
    private JLabel lNameLabel;
    private JTextField lastNameTextField;
    private JLabel passportLabel;
    private JTextField passportTextField;
    private JTextField passFlightTextField;
    private JLabel passFlightLabel;
    private JLabel passSeatClassLabel;
    private JTextField passSeatClassTextField;
    private JLabel passSeatNumberLabel;
    private JTextField passSeatNumberTextField;
    private JCheckBox onWaitListCheckBox;
    private JLabel passWaitListNumberLabel;
    private JTextField passWaitListTextField;
    private JButton schedPassButton;
    private JLabel searchLabel;
    private JTextField firstNameSearchTextBox;
    private JLabel firstNameSearchLabel;
    private JLabel lastNameSearchLabel;
    private JTextField lastNameSearchTextBox;
    private JButton searchButton;
    private JList searchedList;
    private JScrollPane scrollSearchedPassengers;
    private JLabel schedPassLabel;
    private JComboBox schedPassFlightComboBox;
    private JLabel schedPassFlightLabel;
    private JLabel schedPassSeatClassLabel;
    private JComboBox schedPassSeatClassComboBox;
    private DefaultComboBoxModel schedPassSeatClassModel;
    private JLabel schedPassportLabel;
    private JTextField schedPassportTextField;
    private JLabel schedLastNameLabel;
    private JTextField schedLastNameTextField;
    private JLabel schedFirstNameLabel;
    private JTextField schedFirstNameTextField;
    private DefaultListModel flightListModel;
    private DefaultListModel searchModel;
    private DefaultComboBoxModel depModel;
    private DefaultComboBoxModel arvModel;
    private DefaultComboBoxModel schedPassFlightModel;
    private String[] flightIndices;

    // Create a graph of airports
    private Graph airportGraph = new Graph("src/airports.csv");
    // Create a list of flights
    public ArrayList<Flight> flights = new ArrayList<Flight>();

    // Create a file folder to store passengers
    public FileFolder passengers = new FileFolder();

    // Create the Main constructor
    public Main(){
        // Set the main panel
        setContentPane(mainPanel);
        // Set the title of the window
        setTitle("Flight Scheduler");
        // Set the default close operation
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Set the size of the window
        setSize(1100,600);
        // Set the location of the window
        setLocationRelativeTo(null);
        // Set the visibility of the window
        setVisible(true);
        // Create a default list model for the list of flights
        flightListModel = new DefaultListModel();
        // Set the model of the list of flights to the default list model
        listFlights.setModel(flightListModel);
        // Create a default list model for the searched list of passengers
        searchModel = new DefaultListModel();
        // Set the model of the searched list of passengers to the default list model
        searchedList.setModel(searchModel);
        // Generate flights and passengers
        generateFlights(20);
        generatePassengers("src/passengers.csv");
        // Add the airports to the departure combobox
        depModel = new DefaultComboBoxModel(airportGraph.getMyNodes().keySet().toArray());
        depComboBox.setModel(depModel);
        // Add the neighbors of the selected airport to the arrival combobox
        arvModel = new DefaultComboBoxModel(airportGraph.getNode(depComboBox.getSelectedItem().toString()).getNeighborNames());
        arvComboBox.setModel(arvModel);
        // Set the date picker to today's date
        dateDatePicker.setDateToToday();
        // Set the seat class model to the default combo box model
        seatClassModel = new DefaultComboBoxModel(Flight.SeatClass.values());
        // Set the model of the class combobox to the seat class model for the flight information
        classComboBox.setModel(seatClassModel);
        // Set the model of the class combobox to the seat class model
        schedPassSeatClassModel = new DefaultComboBoxModel(Flight.SeatClass.values());
        // Set the model of the class combobox to the seat class model for the scheduled passenger
        schedPassSeatClassComboBox.setModel(schedPassSeatClassModel);
        // Set listFlights to the first index
        listFlights.setSelectedIndex(0);
        // Update the flight information
        updateFlightInfo(0);
        // Update the Flight ComboBox for the scheduled passenger
        updateScheduleFlightComboBox();

        // Add action listeners to the buttons and comboboxes
        addFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add a flight to the list of flights
                addFlight(flights.size(), depComboBox.getSelectedItem().toString(), arvComboBox.getSelectedItem().toString(), dateDatePicker.getDate());
            }
        });
        depComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the arrival combobox based on the selected departure airport
                arvModel = new DefaultComboBoxModel(airportGraph.getNode(depComboBox.getSelectedItem().toString()).getNeighborNames());
                arvComboBox.setModel(arvModel);
            }
        });
        listFlights.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Update the flight information based on the selected flight
                int index = listFlights.getSelectedIndex();
                updateFlightInfo(index);
            }
        });
        classComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the seats and waitlist based on the selected seat class of the flight
                int index = listFlights.getSelectedIndex();
                if (index != -1) {
                    updateSeats(index);
                    updateWaitList(index);

                }
            }
        });
        seatsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the passenger status based on the selected seat
                if (seatsComboBox.getSelectedItem() != null){
                    String seat = seatsComboBox.getSelectedItem().toString();
                    String[] seatArray = seat.split(": ");
                    updatePassengerStatus(seatArray[1], Integer.parseInt(flightTextField.getText()), Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString()), Integer.parseInt(seatArray[0]), false);
                }
            }
        });
        waitComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the passenger status based on the selected waitlist number
                // If the waitlist number is not null, update the passenger status
                if (waitComboBox.getSelectedItem() != null) {
                    String wait = waitComboBox.getSelectedItem().toString();
                    updatePassengerStatus(wait, Integer.parseInt(flightTextField.getText()), Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString()), waitComboBox.getSelectedIndex() + 1, true);
                } else {
                    // If the waitlist number is null, update the passenger status with an empty string
                    updatePassengerStatus(" ", Integer.parseInt(flightTextField.getText()), Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString()), waitComboBox.getSelectedIndex() + 1, true);
                }
            }
        });
        cancelWaitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the passenger from the waitlist
                // If the waitlist number is not null, remove the passenger from the waitlist
                if (waitComboBox.getSelectedItem() != null){
                    // Get the selected flight
                    Flight selectedFlight = flights.get(listFlights.getSelectedIndex());
                    // Get the selected waitlist number
                    String wait = waitComboBox.getSelectedItem().toString();
                    // Get the selected waitlist number index
                    int waitNumber = waitComboBox.getSelectedIndex()+1;
                    // Get the selected seat class
                    Flight.SeatClass currentSeatClass = Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString());
                    // Get the selected flight number
                    int currentFlightNumber = selectedFlight.getNumber();
                    // Remove the passenger from the waitlist of the selected flight
                    selectedFlight.RemovePassengerFromWait(currentSeatClass, waitNumber);
                    // Remove the passenger from the file folder
                    passengers.removeFile(wait, currentFlightNumber, currentSeatClass, waitNumber, true);
                    // Move the waitlist numbers up on the passenger files
                    // from the specified index that the passenger was removed
                    moveWaitSeatsUp(waitNumber, currentFlightNumber, currentSeatClass);
                    // Update the waitlist combobox
                    updateWaitList(listFlights.getSelectedIndex());
                    // Update passenger status
                    updatePassengerStatus(" ", currentFlightNumber, currentSeatClass, waitComboBox.getSelectedIndex()+1, true);
                }
            }
        });
        cancelPassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected flight
                Flight selectedFlight = flights.get(listFlights.getSelectedIndex());
                // Get the selected seat
                String seat = seatsComboBox.getSelectedItem().toString();
                // Get the selected seat index
                int index = seatsComboBox.getSelectedIndex();
                // split the seat string, ie "1: Available" into an array
                String[] seatArray = seat.split(": ");
                // If the seat is not equal to "Available", remove the passenger from the seat
                if (!seatArray[1].equals("Available")) {
                    // Get the seat number
                    int seatNumber = Integer.parseInt(seatArray[0]);
                    // Get the selected seat class
                    Flight.SeatClass currentSeatClass = Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString());
                    // Get the selected flight number
                    int currentFlightNumber = selectedFlight.getNumber();
                    // Remove the passenger from the seat of the selected flight
                    selectedFlight.RemovePassenger(currentSeatClass, seatNumber);
                    // Remove the passenger from the file folder
                    passengers.removeFile(seatArray[1], currentFlightNumber, currentSeatClass, seatNumber, false);
                    // Move the waitlist numbers up on the passenger files if there is a passenger on the waitlist
                    if (selectedFlight.getWait(currentSeatClass).getQueue()[0] != null){
                        // Get response from user to move passenger from waitlist to seat
                        int response = JOptionPane.showConfirmDialog(Main.this,
                            // Ask user if they would like to move the passenger from the waitlist to the seat
                            "Would you like to move "+waitComboBox.getItemAt(0)+" from the waitlist to the available seat?",
                            "Move Passenger", JOptionPane.YES_NO_OPTION);
                        // If the user selects yes, move the passenger from the waitlist to the seat
                        if (response == 0){
                            // Move the passenger from the waitlist to the seat
                            selectedFlight.movePassengerFromWaitToSeat(currentSeatClass, seatNumber);
                            // Get the selected passenger moving from the waitlist to the seat
                            Passenger selectedPassenger = passengers.getFile(waitComboBox.getItemAt(0).toString(), currentFlightNumber, currentSeatClass, 1, true);
                            // Set the new seat number of the selected passenger
                            selectedPassenger.setSeatWaitNumber(seatNumber);
                            // Set the passenger status to not on the waitlist
                            selectedPassenger.setIsWaitListed(false);
                            // Move the waitlist numbers up on the passenger files
                            // from the front of the queue
                            moveWaitSeatsUp(1, currentFlightNumber, currentSeatClass);
                            // Update passenger status
                            updateWaitList(listFlights.getSelectedIndex());
                        }
                    }
                    // Update the seat combobox
                    updateSeats(listFlights.getSelectedIndex());
                    // Set seat combobox to the selected index, where the passenger was removed
                    seatsComboBox.setSelectedIndex(index);
                }
            }
        });
        schedPassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected flight
                Flight selectedFlight = flights.get(schedPassFlightComboBox.getSelectedIndex());
                // Get the selected seat class
                Flight.SeatClass selectedSeatClass = Flight.SeatClass.valueOf(schedPassSeatClassComboBox.getSelectedItem().toString());
                // Get the selected seats of the selected flight
                HashMap<Integer, String> selectedSeats = selectedFlight.getSeats(selectedSeatClass);
                // Create an array list to store available seats
                ArrayList<Integer> availableSeats = new ArrayList<Integer>();
                // Iterate through the selected seats and add available seats to the available seats array list
                for (int i = 1; i <= selectedSeats.size(); i++){
                    // If the seat is available, add the seat number to the available seats array list
                    if (selectedSeats.get(i) == "Available"){
                        availableSeats.add(i);
                    }
                }
                // If there are available seats,
                // present the user with a dialog to reserve one of the available seats
                if (availableSeats.size() > 0){
                    // Get the user response for the seat number
                    Object response = JOptionPane.showInputDialog(Main.this, "Which seat would you like to reserve?",
                            "Reserve Seat", JOptionPane.PLAIN_MESSAGE, null, availableSeats.toArray(), availableSeats.toArray()[0]);
                    // If the user selects a seat, reserve the seat
                    if (response != null){
                        // Get the seat number from the user response
                        int seatNumber = (int) response;
                        // Get the passenger information
                        String firstName = schedFirstNameTextField.getText();
                        // Format the first name
                        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
                        String lastName = schedLastNameTextField.getText();
                        // Format the last name
                        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
                        // Add the passenger to the seat to the selected flight
                        selectedFlight.AddPassenger(selectedSeatClass, seatNumber, firstName + " " + lastName);
                        // Create passenger and passenger to the file folder
                        Passenger newPassenger = new Passenger(firstName, lastName, schedPassportTextField.getText(), selectedFlight.getNumber(), selectedSeatClass, seatNumber, false);
                        passengers.addFile(newPassenger);
                        // Update the seats of the selected flight if the selected flight is the same as the flight being viewed
                        if (selectedFlight.getNumber() == listFlights.getSelectedIndex()) {
                            updateSeats(schedPassFlightComboBox.getSelectedIndex());
                        }
                    }
                    // If there are no available seats,
                } else {
                    // Get the waitlist of the selected flight
                    String[] selectedWaitList = selectedFlight.getWait(selectedSeatClass).getQueue();
                    // Initialize a boolean to check if there are no seats available
                    boolean noSeatsAvailable = true;
                    // Iterate through the waitlist and add the passenger to the waitlist if there are no available seats
                    for (int i = 0; i < selectedWaitList.length; i++){
                        // If a spot on the waitlist is available, add the passenger to the waitlist
                        if (selectedWaitList[i] == null){
                            // Get user response to add the passenger to the waitlist
                            int response = JOptionPane.showConfirmDialog(Main.this,
                                    "All seats for in this seat class are already reserved, would you like to be put on the wait list?",
                                    "Join Wait List", JOptionPane.YES_NO_OPTION);
                            // If the user selects yes, add the passenger to the waitlist
                            if (response == 0){
                                // Get the passenger information
                                String firstName = schedFirstNameTextField.getText();
                                // Format the first name
                                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
                                String lastName = schedLastNameTextField.getText();
                                // Format the last name
                                lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
                                // Add the passenger to the waitlist of the selected flight
                                selectedFlight.AddPassengerToWait(selectedSeatClass, i+1, firstName + " " + lastName);
                                // Create passenger and add passenger to the file folder
                                Passenger newPassenger = new Passenger(firstName, lastName, schedPassportTextField.getText(), selectedFlight.getNumber(), selectedSeatClass, i+1, true);
                                passengers.addFile(newPassenger);
                                // Update the waitlist of the selected flight if the selected flight is the same as the flight being viewed
                                if (selectedFlight.getNumber() == listFlights.getSelectedIndex()) {
                                    updateWaitList(schedPassFlightComboBox.getSelectedIndex());
                                }
                                // Inform the user what position they are on the waitlist
                                JOptionPane.showMessageDialog(Main.this, "You are number "+(i+1)+" on the wait list.");
                            }
                            // Set the boolean to false if user doesn't want to be added to the waitlist
                            // This will prevent the "All seats are reserved" message from showing
                            noSeatsAvailable = false;
                            // Break the loop
                            break;
                        }
                    }
                    // If there are no available seats and the waitlist is full, inform the user
                    if (noSeatsAvailable) {
                        // Inform the user that all seats are reserved and the waitlist is full
                        JOptionPane.showMessageDialog(Main.this,
                                "All seats for in this seat class are already reserved and the wait list is full! Please try another seat class or flight.");
                    }
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the search model
                searchModel.clear();
                // Get the first name and last name from the search textboxes
                String firstName = firstNameSearchTextBox.getText();
                // Format the first name
                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
                String lastName = lastNameSearchTextBox.getText();
                // Format the last name
                lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
                // Get array of strings of information
                // of all the passengers that match the specified first and last names
                String[] foundPassengers = passengers.getFiles(firstName, lastName);
                // If there are passengers found, add them to the search model
                JOptionPane.showMessageDialog(Main.this, "Found "+foundPassengers.length+" passengers.");
                // If there are passengers found, add them to the search model
                for (int i = 0; i < foundPassengers.length; i++){
                    searchModel.add(i, foundPassengers[i]);
                }
            }
        });
        searchedList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Get the selected passenger index
                int index = searchedList.getSelectedIndex();
                // If the selected passenger is not null, update the passenger status
                if (index != -1){
                    // Get the selected passenger information from the searched list
                    String selectedPassenger = searchedList.getSelectedValue().toString();
                    String[] selectedPassengerArray = selectedPassenger.split(", ");
                    String[] selectedPassengerName = selectedPassengerArray[0].split(": ");
                    String[] selectedFlightNumber = selectedPassengerArray[1].split(": ");
                    String[] selectedSeatClass = selectedPassengerArray[2].split(": ");
                    String[] selectedSeatNumber = selectedPassengerArray[3].split(": ");
                    String[] selectedWaitListNumber = selectedPassengerArray[4].split(": ");
                    // Update the passenger status
                    updatePassengerStatus(selectedPassengerName[1], Integer.parseInt(selectedFlightNumber[1]), Flight.SeatClass.valueOf(selectedSeatClass[1]), Integer.parseInt(selectedSeatNumber[1]), Boolean.parseBoolean(selectedWaitListNumber[1]));
                }
            }
        });
    }

    // Main method to run the program
    public static void main(String[] args) {
        new Main();
    }

    // Function to update flight information GUI components
    public void updateFlightInfo(int index){
        // If the index is not -1, update the flight information
        if (index != -1){
            // Get the selected flight
            Flight selectedFlight = flights.get(index);
            // Set the text of the flight number text field to the selected flight number
            flightTextField.setText(selectedFlight.getNumber() + "");
            // Set the text of the departure text field to the selected flight departure
            departureTextField.setText(selectedFlight.getDeparture());
            // Set the text of the date text field to the selected flight departure date
            dateTextField.setText(selectedFlight.getDepartureDate() + "");
            // Set the text of the arrival text field to the selected flight arrival
            arvTextField.setText(selectedFlight.getArrival());
            // Update the seats and waitlist based on the selected seat class
            updateSeats(index);
            updateWaitList(index);

        }
    }

    // Function to update the sheduled passenger flight number combobox
    public void updateScheduleFlightComboBox(){
        // Get indices of all flights and add them to the schedPassFlightComboBox
        flightIndices = new String[flights.size()];
        // Add the indices of all flights to the flight indices array
        for (int i = 0; i < flights.size(); i++) {
            flightIndices[i] = String.valueOf(i);
        }
        // Set the model of the scheduled passenger flight combobox to the flight indices
        schedPassFlightModel = new DefaultComboBoxModel(flightIndices);
        schedPassFlightComboBox.setModel(schedPassFlightModel);
    }

    // Function to instantiate flight objects in flights and add them to the listFlights
    public void addFlight(int num, String departure, String arrival, LocalDate date){
        // Add the flight to the list of flights
        flights.add(new Flight(num, departure, arrival, date));
        // Add the flight to the list of flights in the GUI
        flightListModel.add(num, "#: " + flights.get(num).getNumber() + ", From: " + flights.get(num).getDeparture() + ", To: " + flights.get(num).getArrival() + ", On: " + flights.get(num).getDepartureDate());
        // Update the flight information on the scheduled passenger panel
        updateScheduleFlightComboBox();
    }

    // Function to generate an amount of flights using all cities in Airports
    public void generateFlights(int amount){
        // Get all the airports
        String[] airports = airportGraph.getMyNodes().keySet().toArray(new String[0]);
        // Initialize the index
        int i = 0;
        // Get the current date
        LocalDate dateTime = LocalDate.now();
        // Generate flights until the amount is reached
        while (flights.size() < amount){
            // Get the connected airports to the current airport
            String[] connectedAirports = airportGraph.getMyNodes().get(airports[i]).getNeighborNames();
            // Add flights to the connected airports
            for (int j = 0; j < connectedAirports.length && flights.size() < amount; j++) {
                addFlight(flights.size(), airports[i], connectedAirports[j], dateTime);
            }
            // As long as there are more airports, increment the index
            if (i < airports.length-1){
                i++;
                // Else, reset the index and increment the date
            } else {
                i = 0;
                dateTime = dateTime.plusDays(1);
            }
        }
    }

    // Function to generate passengers from a csv file
    public void generatePassengers(String csvFile){
        // Read the csv file
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Initialize variables
        int flight = 0;
        int seatNumber = 1;
        int waitNumber = 0;
        Flight.SeatClass seatClass;

        // For each row, create a passenger
        for(int i = 1; i < records.size(); i++){
            Flight selectedFlight = flights.get(flight);
            // If the seat number is greater than 35, move to the next flight
            if (seatNumber > 35 && waitNumber > 20){
                flight++;
                seatNumber = 1;
            }
            // Determine the seat class based on the seat number
            if (seatNumber<=5 && waitNumber<=5) {
                // If the seat number is less than 5, add the passenger to the first class
                if (seatNumber < 5) {
                    // Add the passenger to the seat
                    selectedFlight.AddPassenger(Flight.SeatClass.First, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.First, seatNumber, false);
                    passengers.addFile(newPassenger);
                    seatNumber++;
                    // If the seat number is 5 and the wait number is 0,
                    // add the passenger to first spot of the first class waitlist
                } else if (seatNumber == 5 && waitNumber == 0){
                    // Add the passenger to the waitlist
                    selectedFlight.AddPassenger(Flight.SeatClass.First, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.First, seatNumber, false);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                    // Else, start adding passengers to the first class waitlist
                } else {
                    // Add the passenger to the waitlist
                    selectedFlight.AddPassengerToWait(Flight.SeatClass.First, waitNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.First, waitNumber, true);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                    // If the wait number is greater than 5, move to the next seat
                    if (waitNumber > 5){
                        seatNumber = 6;
                        // Reset the wait number
                        waitNumber = 0;
                    }
                }
                // If the seat number is greater than 5 and less than or equal to 15 and the wait number is less than or equal to 10,
            } else if (seatNumber>5 && seatNumber<=15 && waitNumber<=10){
                // Add the passenger to the business class
                if (seatNumber < 15){
                    selectedFlight.AddPassenger(Flight.SeatClass.Business, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Business, seatNumber, false);
                    passengers.addFile(newPassenger);
                    seatNumber++;
                    // If the seat number is 15 and the wait number is 0,
                    // add the passenger to the first spot of the business class waitlist
                } else if (seatNumber == 15 && waitNumber == 0){
                    selectedFlight.AddPassenger(Flight.SeatClass.Business, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Business, seatNumber, false);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                    // Else, start adding passengers to the business class waitlist
                } else {
                    selectedFlight.AddPassengerToWait(Flight.SeatClass.Business, waitNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Business, waitNumber, true);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                    // If the wait number is greater than 10, move to the next seat
                    if (waitNumber > 10){
                        seatNumber = 16;
                        // Reset the wait number
                        waitNumber = 1;
                    }
                }
                // Else, if the seat number is greater than 15 and less than or equal to 35,
                // add the passenger to the economy class
            } else {
                // Add the passenger to the economy class
                if (seatNumber < 36){
                    selectedFlight.AddPassenger(Flight.SeatClass.Economy, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Economy, seatNumber, false);
                    passengers.addFile(newPassenger);
                    seatNumber++;
                    // If the seat number is 35 and the wait number is 0,
                    // add the passenger to the economy class waitlist
                } else {
                    selectedFlight.AddPassengerToWait(Flight.SeatClass.Economy, waitNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Economy, waitNumber, true);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                    // If the wait number is greater than 20, move to the next flight
                    if (waitNumber > 20){
                        // Move to the next flight
                        flight++;
                        // Reset the seat number
                        seatNumber = 1;
                        // Reset the wait number
                        waitNumber = 0;
                    }
                }
            }
        }
    }
    // Function to move the waitlist numbers up on the passenger files
    public void moveWaitSeatsUp(int index, int currentFlightNumber, Flight.SeatClass currentSeatClass){
        for (int i = index; i < waitComboBox.getItemCount(); i++){
            if (waitComboBox.getItemAt(i) != null){
                Passenger selectedPassenger = passengers.getFile(waitComboBox.getItemAt(i).toString(), currentFlightNumber, currentSeatClass, i+1, true);
                selectedPassenger.setSeatWaitNumber(i);
            }
        }
    }

    // Function to update the seats combobox
    public void updateSeats(int index) {
        Flight selectedFlight = flights.get(index);
        // Store available spots in seats
        HashMap<Integer, String> seatHashMap = selectedFlight.getSeats(Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString()));
        // Combine the keys and values of the hashmap into a single string
        String[] seatArray = new String[seatHashMap.size()];
        // store the keys of the hashmap in an array
        Object[] keys = seatHashMap.keySet().toArray();
        // Sort the keys
        Arrays.sort(keys);
        // Add the sorted keys and values to the seatArray
        for (int i = 0; i < keys.length; i++) {
            seatArray[i] = keys[i] + ": " + seatHashMap.get(keys[i]);
        }
        // Set the model of the seatsComboBox to the new array of seats
        seatsModel = new DefaultComboBoxModel(seatArray);
        // Set the model of the seatsComboBox to the new model
        seatsComboBox.setModel(seatsModel);
    }

    // Function to update waitlist combobox
    public void updateWaitList(int index) {
        Flight selectedFlight = flights.get(index);
        // Store available spots in waitlist
        Queue waitHashMap = selectedFlight.getWait(Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString()));
        waitModel = new DefaultComboBoxModel(waitHashMap.getQueue());
        // Set the model of the waitComboBox to the new model
        waitComboBox.setModel(waitModel);
    }
    // Function to update passenger status GUI components
    public void updatePassengerStatus(String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        // Update the passenger status based on the selected passenger
        switch (passengerName) {
            // If the passenger name is "Available" or " ", set the text fields to empty strings
            case "Available":
            case " ":
                firstNameTextField.setText(" ");
                lastNameTextField.setText(" ");
                passportTextField.setText(" ");
                passFlightTextField.setText(" ");
                passSeatClassTextField.setText(" ");
                passWaitListTextField.setText(" ");
                passSeatNumberTextField.setText(" ");
                break;
                // If the passenger name is not "Available" or " ", set the text fields to the selected passenger information
            default:
                Passenger selectedPassenger = passengers.getFile(passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                firstNameTextField.setText(selectedPassenger.getFirstName());
                lastNameTextField.setText(selectedPassenger.getLastName());
                passportTextField.setText(selectedPassenger.getPassport());
                passFlightTextField.setText(selectedPassenger.getFlightNumber() + "");
                passSeatClassTextField.setText(selectedPassenger.getSeatClass().toString());
                // Set the onWaitListCheckBox to the selected passenger waitlist status
                onWaitListCheckBox.setSelected(selectedPassenger.getIsWaitListed());
                // If the passenger is on the waitlist, set the waitlist text field to the selected passenger waitlist number
                if (onWaitListCheckBox.isSelected()) {
                    passWaitListTextField.setText(selectedPassenger.getSeatWaitNumber() + "");
                    passSeatNumberTextField.setText(" ");
                    // If the passenger is not on the waitlist, set the seat number text field to the selected passenger seat number
                } else {
                    passSeatNumberTextField.setText(selectedPassenger.getSeatWaitNumber() + "");
                    passWaitListTextField.setText(" ");
                }
        }
    }
}
