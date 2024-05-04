// Class for Passenger details
public class Passenger {
    // Attributes for Passenger details
    private String firstName;
    private String lastName;
    private String passportNumber;
    private int flightNumber; // Associated flight number
    private Flight.SeatClass seatClass; // Class of the seat (Enum: First, Business, Economy)
    private int seatWaitNumber; // Seat number or wait list number
    private boolean isWaitListed; // Status whether the passenger is on the wait list

    // Constructor initializes passenger with detailed information
    public Passenger(String firstName, String lastName, String passportNumber, int flightNumber, Flight.SeatClass seatClass, int seatWaitNumber, boolean isWaitListed){
        // Format names and passport number
        this.firstName = formatName(firstName);
        this.lastName = formatName(lastName);
        this.passportNumber = passportNumber.toUpperCase();
        this.flightNumber = flightNumber;
        this.seatClass = seatClass;
        this.seatWaitNumber = seatWaitNumber;
        this.isWaitListed = isWaitListed;
    }

    // Helper method to capitalize the first letter and make others lowercase
    private String formatName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    // Getters and setters for passenger attributes
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = formatName(firstName);
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = formatName(lastName);
    }

    public String getPassport() {
        return this.passportNumber;
    }

    public void setPassport(String passportNumber) {
        this.passportNumber = passportNumber.toUpperCase();
    }

    // Returns full name by concatenating first and last names
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public int getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Flight.SeatClass getSeatClass() {
        return this.seatClass;
    }

    public void setSeatClass(Flight.SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public int getSeatWaitNumber() {
        return this.seatWaitNumber;
    }

    public void setSeatWaitNumber(int seatWaitNumber) {
        this.seatWaitNumber = seatWaitNumber;
    }

    public boolean getIsWaitListed() {
        return this.isWaitListed;
    }

    public void setIsWaitListed(boolean isWaitListed) {
        this.isWaitListed = isWaitListed;
    }

}

