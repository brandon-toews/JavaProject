import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

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
    private String[] firstClass = new String[5];
    // Business class seats (10 seats, seats 6 - 15)
    private String[] businessClass = new String[10];
    // Economy class seats (20 seats, seats 16 - 35)
    private String[] economyClass = new String[20];

    public Flight(int num, String departure, String arrival, LocalDate date) {
        setNumber(num);
        setDeparture(departure);
        setArrival(arrival);
        setDepartureDate(date);
        Arrays.fill(firstClass,"Available");
        Arrays.fill(businessClass,"Available");
        Arrays.fill(economyClass,"Available");
    }

    public void AddPassenger(SeatClass seatClass, int seatNumber, String name){
        switch(seatClass) {
            case First:
                setFirstClass(seatNumber, name);
                break;
            case Business:
                setBusinessClass(seatNumber, name);
                break;
            default:
                setEconomyClass(seatNumber, name);
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
        this.firstClass[index-1] = firstClass;
    }

    public void setBusinessClass(int index, String businessClass) {
        this.businessClass[index-1] = businessClass;
    }

    public void setEconomyClass(int index, String economyClass) {
        this.economyClass[index-1] = economyClass;
    }
}
