import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    private JLabel passLabel;
    private JTextField passTextField;
    private DefaultListModel model;
    private DefaultComboBoxModel depModel;
    private DefaultComboBoxModel arvModel;

    // Create a graph of airports
    private Graph airportGraph = new Graph("src/airports.csv");
    // Create a list of flights
    public ArrayList<Flight> flights = new ArrayList<Flight>();

    // Create a list of passengers
    public ArrayList<Passenger> passengers = new ArrayList<Passenger>();

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
        generateFlights(200);
        depModel = new DefaultComboBoxModel(airportGraph.getMyNodes().keySet().toArray());
        depComboBox.setModel(depModel);
        arvModel = new DefaultComboBoxModel(airportGraph.getNode(depComboBox.getSelectedItem().toString()).getNeighborNames());
        arvComboBox.setModel(arvModel);
        dateDatePicker.setDateToToday();
        seatClassModel = new DefaultComboBoxModel(Flight.SeatClass.values());
        classComboBox.setModel(seatClassModel);


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
        HashMap<Integer, String> waitHashMap = selectedFlight.getWait(Flight.SeatClass.valueOf(classComboBox.getSelectedItem().toString()));
        // Combine the keys and values of the hashmap into a single string
        String[] waitArray = new String[waitHashMap.size()];
        // store the keys of the hashmap in an array
        Object[] waitKeys = waitHashMap.keySet().toArray();
        // Sort the keys
        Arrays.sort(waitKeys);
        // Add the sorted keys and values to the waitArray
        for (int i = 0; i < waitKeys.length; i++) {
            waitArray[i] = waitKeys[i] + ": " + waitHashMap.get(waitKeys[i]);
        }
        // Set the model of the waitComboBox to the new array of waitlist
        waitModel = new DefaultComboBoxModel(waitArray);
        // Set the model of the waitComboBox to the new model
        waitComboBox.setModel(waitModel);
    }
}
