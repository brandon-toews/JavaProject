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

public class Main extends JFrame {
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
    private JTextField passSeatNumbertextField;
    private JCheckBox onWaitListCheckBox;
    private JLabel passWaitListNumberLabel;
    private JTextField passWaitListTextField;
    private DefaultListModel model;
    private DefaultComboBoxModel depModel;
    private DefaultComboBoxModel arvModel;

    // Create a graph of airports
    private Graph airportGraph = new Graph("src/airports.csv");
    // Create a list of flights
    public ArrayList<Flight> flights = new ArrayList<Flight>();

    // Create a list of passengers
    //public ArrayList<Passenger> passengers = new ArrayList<Passenger>();

    // Create a file folder to store passengers
    public FileFolder passengers = new FileFolder();

    public Main(){
        setContentPane(mainPanel);
        setTitle("Flight Scheduler");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);
        setVisible(true);
        model = new DefaultListModel();
        listFlights.setModel(model);
        //listFlights = new JComboBox();
        generateFlights(20);
        generatePassengers("src/passengers.csv");
        depModel = new DefaultComboBoxModel(airportGraph.getMyNodes().keySet().toArray());
        depComboBox.setModel(depModel);
        arvModel = new DefaultComboBoxModel(airportGraph.getNode(depComboBox.getSelectedItem().toString()).getNeighborNames());
        arvComboBox.setModel(arvModel);
        dateDatePicker.setDateToToday();
        seatClassModel = new DefaultComboBoxModel(Flight.SeatClass.values());
        classComboBox.setModel(seatClassModel);


        int [] numArray = {5, 1, 5, 4, 2, 3, 0};

        // Create a binary tree
        BinaryTree tree = new BinaryTree(numArray);

        // Print the tree
        tree.printTree();


        addFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFlight(flights.size(), depComboBox.getSelectedItem().toString(), arvComboBox.getSelectedItem().toString(), dateDatePicker.getDate());
                //JOptionPane.showMessageDialog(Main.this, "Hello ");
            }
        });

        depComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arvModel = new DefaultComboBoxModel(airportGraph.getNode(depComboBox.getSelectedItem().toString()).getNeighborNames());
                arvComboBox.setModel(arvModel);
            }
        });
        listFlights.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = listFlights.getSelectedIndex();
                if (index != -1){
                    Flight selectedFlight = flights.get(index);
                    flightTextField.setText(selectedFlight.getNumber() + "");
                    departureTextField.setText(selectedFlight.getDeparture());
                    dateTextField.setText(selectedFlight.getDepartureDate() + "");
                    arvTextField.setText(selectedFlight.getArrival());
                    updateSeats(index);
                    updateWaitList(index);

                }
            }
        });
        classComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                if (seatsComboBox.getSelectedItem() != null){
                    String seat = seatsComboBox.getSelectedItem().toString();
                    String[] seatArray = seat.split(": ");
                    updatePassengerStatus(seatArray[1], Integer.parseInt(flightTextField.getText()), Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString()), Integer.parseInt(seatArray[0]), false);

                    //JOptionPane.showMessageDialog(Main.this, seatArray[1]);
                }
            }
        });
        waitComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wait = waitComboBox.getSelectedItem().toString();
                //String[] waitArray = wait.split(": ");
                updatePassengerStatus(wait, Integer.parseInt(flightTextField.getText()), Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString()), waitComboBox.getSelectedIndex()+1, true);
            }
        });
        cancelWaitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight selectedFlight = flights.get(listFlights.getSelectedIndex());
                String wait = waitComboBox.getSelectedItem().toString();
                //String[] waitArray = wait.split(": ");
                if (wait.length() != 1){
                    int waitNumber = waitComboBox.getSelectedIndex()+1;
                    Flight.SeatClass currentSeatClass = Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString());
                    int currentFlightNumber = selectedFlight.getNumber();
                    selectedFlight.RemovePassengerFromWait(currentSeatClass, waitNumber);
                    passengers.removeFile(wait, currentFlightNumber, currentSeatClass, waitNumber, true);
                    for (int i = waitNumber; i < waitComboBox.getItemCount(); i++){
                        String selectedPassengerName = waitComboBox.getItemAt(i).toString();
                        if (selectedPassengerName.length() != 1){
                            Passenger selectedPassenger = passengers.getFile(selectedPassengerName, currentFlightNumber, currentSeatClass, i+1, true);
                            selectedPassenger.setSeatWaitNumber(i);
                        }
                    }
                    updateWaitList(listFlights.getSelectedIndex());
                    //wait = waitComboBox.getSelectedItem().toString();

                    updatePassengerStatus(" ", currentFlightNumber, currentSeatClass, waitComboBox.getSelectedIndex()+1, true);
                }
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }

    // Function to instantiate flight objects in flights and add them to the listFlights
    public void addFlight(int num, String departure, String arrival, LocalDate date){
        flights.add(new Flight(num, departure, arrival, date));
        model.add(num, "#: " + flights.get(num).getNumber() + ", From: " + flights.get(num).getDeparture() + ", To: " + flights.get(num).getArrival() + ", On: " + flights.get(num).getDepartureDate());
    }

    // Function to generate an amount of flights using all cities in Airports
    public void generateFlights(int amount){
        String[] airports = airportGraph.getMyNodes().keySet().toArray(new String[0]);
        int i = 0;
        LocalDate dateTime = LocalDate.now();
        while (flights.size() < amount){
            String[] connectedAirports = airportGraph.getMyNodes().get(airports[i]).getNeighborNames();
            for (int j = 0; j < connectedAirports.length && flights.size() < amount; j++) {
                addFlight(flights.size(), airports[i], connectedAirports[j], dateTime);
            }
            if (i < airports.length-1){
                i++;
            } else {
                i = 0;
                dateTime = dateTime.plusDays(1);
            }
        }

    }

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
                if (seatNumber < 5) {
                    selectedFlight.AddPassenger(Flight.SeatClass.First, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.First, seatNumber, false);
                    passengers.addFile(newPassenger);
                    seatNumber++;
                } else if (seatNumber == 5 && waitNumber == 0){
                    selectedFlight.AddPassenger(Flight.SeatClass.First, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.First, seatNumber, false);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                } else {
                    selectedFlight.AddPassengerToWait(Flight.SeatClass.First, waitNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.First, waitNumber, true);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                    if (waitNumber > 5){
                        seatNumber = 6;
                        waitNumber = 0;
                    }
                }
            } else if (seatNumber>5 && seatNumber<=15 && waitNumber<=10){
                if (seatNumber < 15){
                    selectedFlight.AddPassenger(Flight.SeatClass.Business, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Business, seatNumber, false);
                    passengers.addFile(newPassenger);
                    seatNumber++;
                } else if (seatNumber == 15 && waitNumber == 0){
                    selectedFlight.AddPassenger(Flight.SeatClass.Business, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Business, seatNumber, false);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                } else {
                    selectedFlight.AddPassengerToWait(Flight.SeatClass.Business, waitNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Business, waitNumber, true);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                    if (waitNumber > 10){
                        seatNumber = 16;
                        waitNumber = 1;
                    }
                }
            } else {
                if (seatNumber < 36){
                    selectedFlight.AddPassenger(Flight.SeatClass.Economy, seatNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Economy, seatNumber, false);
                    passengers.addFile(newPassenger);
                    seatNumber++;
                } else {
                    selectedFlight.AddPassengerToWait(Flight.SeatClass.Economy, waitNumber, records.get(i).get(0) + " " + records.get(i).get(1));
                    Passenger newPassenger = new Passenger(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), flight, Flight.SeatClass.Economy, waitNumber, true);
                    passengers.addFile(newPassenger);
                    waitNumber++;
                    if (waitNumber > 20){
                        flight++;
                        seatNumber = 1;
                        waitNumber = 0;
                    }
                }
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
        // Combine the keys and values of the hashmap into a single string
        /*String[] waitArray = new String[waitHashMap.getSize()];
        // store the keys of the hashmap in an array
        Object[] waitKeys = waitHashMap.keySet().toArray();
        // Sort the keys
        Arrays.sort(waitKeys);
        // Add the sorted keys and values to the waitArray
        for (int i = 0; i < waitKeys.length; i++) {
            waitArray[i] = waitKeys[i] + ": " + waitHashMap.get(waitKeys[i]);
        }*/
        // Set the model of the waitComboBox to the new array of waitlist
        waitModel = new DefaultComboBoxModel(waitHashMap.getQueue());
        // Set the model of the waitComboBox to the new model
        waitComboBox.setModel(waitModel);
    }

    public void updatePassengerStatus(String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        switch (passengerName) {
            case "Available":
            case " ":
                firstNameTextField.setText(" ");
                lastNameTextField.setText(" ");
                passportTextField.setText(" ");
                passFlightTextField.setText(" ");
                passSeatClassTextField.setText(" ");
                onWaitListCheckBox.setSelected(false);
                passWaitListTextField.setText(" ");
                passSeatNumbertextField.setText(" ");
                break;
            default:
                //Passenger selectedPassenger = passengers.getFile(passengerName, flightNumber, seatClass, seatNumber
                Passenger selectedPassenger = passengers.getFile(passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                firstNameTextField.setText(selectedPassenger.getFirstName());
                lastNameTextField.setText(selectedPassenger.getLastName());
                passportTextField.setText(selectedPassenger.getPassport());
                passFlightTextField.setText(selectedPassenger.getFlightNumber() + "");
                passSeatClassTextField.setText(selectedPassenger.getSeatClass().toString());
                onWaitListCheckBox.setSelected(selectedPassenger.getIsWaitListed());
                if (onWaitListCheckBox.isSelected()) {
                    passWaitListTextField.setText(selectedPassenger.getSeatWaitNumber() + "");
                    passSeatNumbertextField.setText(" ");
                } else {
                    passSeatNumbertextField.setText(selectedPassenger.getSeatWaitNumber() + "");
                    passWaitListTextField.setText(" ");
                }
        }
    }
}
