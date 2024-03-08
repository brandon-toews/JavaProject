import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private JComboBox seatComboBox;
    private JLabel seatClassLabel;
    private DefaultListModel model;
    private DefaultComboBoxModel depModel;
    private DefaultComboBoxModel arvModel;

    private Graph airportGraph = new Graph("src/airports.csv");

    public ArrayList<Flight> flights = new ArrayList<Flight>();
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
                    flightTextField.setText(flights.get(index).getNumber() + "");
                    departureTextField.setText(flights.get(index).getDeparture());
                    dateTextField.setText(flights.get(index).getDepartureDate() + "");
                    arvTextField.setText(flights.get(index).getArrival());
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
}
