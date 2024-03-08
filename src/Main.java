import javax.swing.*;
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
    private JComboBox arrivalComboBox;
    private DefaultListModel model;

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
        generateFlights(16);




        addFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFlight(flights.size(), "Amsterdam", "Bangkok", LocalDate.now());
                JOptionPane.showMessageDialog(Main.this, "Hello ");
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
        while (flights.size() < amount){
            String[] connectedAirports = airportGraph.getMyNodes().get(airports[i]).getNeighborNames();
            for (int j = 0; j < connectedAirports.length && flights.size() < amount; j++) {
                addFlight(flights.size(), airports[i], connectedAirports[j], LocalDate.now());
            }
            if (i < airports.length-1){
                i++;
            } else {
                i = 0;
            }
        }

    }



}
