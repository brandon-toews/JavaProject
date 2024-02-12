import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main extends JFrame {
    private JLabel jlLabel;
    private JButton addFlight;
    private JPanel mainPanel;
    private JList listFlights;
    private DefaultListModel model;

    private int numberFlights = 0;

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
                flights.add(new Flight(numberFlights, Airports.Amsterdam, Airports.Bangkok, LocalDate.now()));
                //listFlights.addItem(flights.get(numberFlights).getNumber());
                //listFlights = new JList(flightNumbers.toArray(new String[0]));
                numberFlights++;
                //mainPanel.add(listFlights);
                JOptionPane.showMessageDialog(Main.this, "Hello ");
            }
        });

    }

    public static void main(String[] args) {
        new Main();
    }

    // Function to instantiate flight objects in flights and add them to the listFlights
    public void addFlight(int num, Airports departure, Airports arrival, LocalDate date){
        flights.add(new Flight(num, departure, arrival, date));
        model.add(num, "#: " + flights.get(num).getNumber() + ", From: " + flights.get(num).getDeparture() + ", To: " + flights.get(num).getArrival() + ", On: " + flights.get(num).getDepartureDate());
    }

    // Function to generate an amount of flights using all cities in Airports
    public void generateFlights(int amount){
        for(int i = 0; i < amount; i++){
            addFlight(i, Airports.values()[i], Airports.values()[i+1], LocalDate.now());
        }
    }



}
