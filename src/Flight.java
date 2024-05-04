import java.time.LocalDate;
import java.util.HashMap;

public class Flight {
    // Attributes defining the flight
    private int number; // Flight number, an integer between 0 and 999
    private String departure; // Departure airport code
    private LocalDate departureDate; // Date of departure
    private String arrival; // Arrival airport code

    // Enumeration for seat classes
    enum SeatClass {
        First,
        Business,
        Economy
    }

    // HashMaps for seat assignments in each class
    private HashMap<Integer, String> firstClass = new HashMap<>(); // First class seats
    private HashMap<Integer, String> businessClass = new HashMap<>(); // Business class seats
    private HashMap<Integer, String> economyClass = new HashMap<>(); // Economy class seats

    // Queues for wait lists in each seat class
    private Queue firstWait = new Queue(5); // First class wait list
    private Queue businessWait = new Queue(10); // Business class wait list
    private Queue economyWait = new Queue(20); // Economy class wait list

    // Constructor to initialize flight details
    public Flight(int num, String departure, String arrival, LocalDate date) {
        setNumber(num);
        setDeparture(departure);
        setArrival(arrival);
        setDepartureDate(date);
        CreateSeats(); // Initialize seats as available upon flight creation
    }

    // Initializes all seats as "Available"
    private void CreateSeats(){
        for (int i = 1; i <= 5; i++){
            firstClass.put(i, "Available");
        }
        for (int i = 6; i <= 15; i++){
            businessClass.put(i, "Available");
        }
        for (int i = 16; i <= 35; i++){
            economyClass.put(i, "Available");
        }
    }

    // Adds a passenger to a specific seat
    public void AddPassenger(SeatClass seatClass, int seatNumber, String name){
        switch(seatClass) {
            case First:
                setFirstClass(seatNumber, name);
                break;
            case Business:
                setBusinessClass(seatNumber, name);
                break;
            case Economy:
                setEconomyClass(seatNumber, name);
                break;
            default:
                break;
        }
    }

    // Adds a passenger to the wait list
    public void AddPassengerToWait(SeatClass seatClass, int waitNumber, String name){
        switch(seatClass) {
            case First:
                firstWait.enqueue(name);
                break;
            case Business:
                businessWait.enqueue(name);
                break;
            case Economy:
                economyWait.enqueue(name);
                break;
            default:
                break;
        }
    }

    // Removes a passenger from a seat
    public void RemovePassenger(SeatClass seatClass, int seatNumber){
        switch(seatClass) {
            case First:
                firstClass.put(seatNumber, "Available");
                break;
            case Business:
                businessClass.put(seatNumber, "Available");
                break;
            case Economy:
                economyClass.put(seatNumber, "Available");
                break;
            default:
                break;
        }
    }

    // Moves a passenger from wait list to a seat
    public void movePassengerFromWaitToSeat(SeatClass seatClass, int seatNumber){
        switch(seatClass) {
            case First:
                firstClass.put(seatNumber, firstWait.dequeue());
                break;
            case Business:
                businessClass.put(seatNumber, businessWait.dequeue());
                break;
            case Economy:
                economyClass.put(seatNumber, economyWait.dequeue());
                break;
            default:
                break;
        }
    }

    // Removes a passenger from the wait list
    public void RemovePassengerFromWait(Flight.SeatClass seatClass, int waitNumber){
        switch(seatClass) {
            case First:
                firstWait.popItem(waitNumber-1);
                break;
            case Business:
                businessWait.popItem(waitNumber-1);
                break;
            case Economy:
                economyWait.popItem(waitNumber-1);
                break;
            default:
                break;
        }
    }

    // Getters and setters for flight details
    public int getNumber() {
        return number;
    }

    private void setNumber(int number) {
        this.number = number;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    // Methods to manage seat assignments
    public void setFirstClass(int index, String passenger) {
        firstClass.put(index, passenger);
    }

    public void setBusinessClass(int index, String passenger) {
        businessClass.put(index, passenger);
    }

    public void setEconomyClass(int index, String passenger) {
        economyClass.put(index, passenger);
    }

    // Getter for seats by class
    public HashMap<Integer, String> getSeats(SeatClass seatClass){
        switch(seatClass) {
            case First:
                return firstClass;
            case Business:
                return businessClass;
            case Economy:
                return economyClass;
            default:
                return null;
        }
    }

    // Getter for wait list by class
    public Queue getWait(SeatClass seatClass){
        switch(seatClass) {
            case First:
                return firstWait;
            case Business:
                return businessWait;
            case Economy:
                return economyWait;
            default:
                return null;
        }
    }
}
