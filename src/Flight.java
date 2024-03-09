import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;

public class Flight {
    //A flight number (integer 0- 999)
    private int number;
    //A departure airport
    private String departure;
    //A departure date
    private LocalDate departureDate;
    //An arrival airport
    private String arrival;

    enum SeatClass {
        First,
        Business,
        Economy
    }


    // A seating lists
    // First class seats (5 seats, seats 1, 2, 3, 4, and 5).
    // private String[] firstClass = new String[5];
    private HashMap<Integer, String> firstClass= new HashMap<Integer, String>();
    // A waiting list for first class, 5 spots
    private HashMap<Integer, String> firstWait= new HashMap<Integer, String>();
    // Business class seats (10 seats, seats 6 - 15)
    private HashMap<Integer, String> businessClass= new HashMap<Integer, String>();
    // A waiting list for business class, 10 spots
    private HashMap<Integer, String> businessWait= new HashMap<Integer, String>();
    // Economy class seats (20 seats, seats 16 - 35)
    private HashMap<Integer, String> economyClass= new HashMap<Integer, String>();
    // A waiting list for economy class, 20 spots
    private HashMap<Integer, String> economyWait= new HashMap<Integer, String>();

    public Flight(int num, String departure, String arrival, LocalDate date) {
        setNumber(num);
        setDeparture(departure);
        setArrival(arrival);
        setDepartureDate(date);
        CreateSeats();
    }

    // Function to create seats
    private void CreateSeats(){
        for (int i = 1; i <= 5; i++){
            firstClass.put(i, "Available");
            firstWait.put(i, " ");
        }
        for (int i = 6; i <= 15; i++){
            businessClass.put(i, "Available");
            businessWait.put(i-5, " ");
        }
        for (int i = 16; i <= 35; i++){
            economyClass.put(i, "Available");
            economyWait.put(i-15, " ");
        }
    }

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

    public void setFirstClass(int index, String firstClass) {
        this.firstClass.put(index, firstClass);
    }

    public void setBusinessClass(int index, String businessClass) {
        this.businessClass.put(index, businessClass);
    }

    public void setEconomyClass(int index, String economyClass) {
        this.economyClass.put(index, economyClass);
    }

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

    public HashMap<Integer, String> getWait(SeatClass seatClass){
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
