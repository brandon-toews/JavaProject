public class Passenger {
    // Attributes
    private String firstName;
    private String lastName;
    private String passportNumber;

    private int flightNumber;

    private Flight.SeatClass seatClass;

    // Seat number or number on wait list
    private int seatWaitNumber;

    private boolean isWaitListed;



    // Constructor
    public Passenger(String firstName, String lastName, String passportNumber, int flightNumber, Flight.SeatClass seatClass, int seatWaitNumber, boolean isWaitListed){
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.flightNumber = flightNumber;
        this.seatClass = seatClass;
        this.seatWaitNumber = seatWaitNumber;
        this.isWaitListed = isWaitListed;
    }

    // Getters and Setters
    // Function to get the first name of the passenger
    public String getFirstName() {
        return this.firstName;
    }

    // Function to set the first name of the passenger
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Function to get the last name of the passenger
    public String getLastName() {
        return this.lastName;
    }

    // Function to set the last name of the passenger
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Function to get the passport number of the passenger
    public String getPassport(){
        return this.passportNumber;
    }

    // Function to set the passport number of the passenger
    public void setPassport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    // Function to get the full name of the passenger
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    // Function to get the flight number of the passenger
    public int getFlightNumber(){
        return this.flightNumber;
    }

    // Function to set the flight number of the passenger
    public void setFlightNumber(int flightNumber){
        this.flightNumber = flightNumber;
    }

    // Function to get the seat class of the passenger
    public Flight.SeatClass getSeatClass(){
        return this.seatClass;
    }

    // Function to set the seat class of the passenger
    public void setSeatClass(Flight.SeatClass seatClass){
        this.seatClass = seatClass;
    }

    // Function to get the seat number of the passenger
    public int getSeatWaitNumber(){
        return this.seatWaitNumber;
    }

    // Function to set the seat number of the passenger
    public void setSeatWaitNumber(int seatWaitNumber){
        this.seatWaitNumber = seatWaitNumber;
    }

    // Function to get the wait list status of the passenger
    public boolean getIsWaitListed(){
        return this.isWaitListed;
    }

    // Function to set the wait list status of the passenger
    public void setIsWaitListed(boolean isWaitListed){
        this.isWaitListed = isWaitListed;
    }

    // Function to get the full information of the passenger
    public String getFullInfo(){
        return "Name: " + this.getFullName() + ", Passport: " + this.getPassport() + ", Flight: " + this.getFlightNumber() + ", Seat Class: " + this.getSeatClass() + ", Seat Number: " + this.getSeatWaitNumber() + ", Wait List: " + this.getIsWaitListed();
    }

}
