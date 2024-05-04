import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileFolder {
    private HashMap<String, ArrayList<Passenger>> folders = new HashMap<String, ArrayList<Passenger>>();;

    public FileFolder(){
        //loop through the alphabet and add each letter to the folders
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            this.folders.put(String.valueOf(letter), new ArrayList<Passenger>());
        }
    }

    public void addFile(Passenger newPassenger) {
        char letter = Character.toUpperCase(newPassenger.getLastName().charAt(0));
        this.folders.get(String.valueOf(letter)).add(newPassenger);
    }

    public Passenger getFile(String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        String[] firstLastName = passengerName.split(" ");
        char letter = Character.toUpperCase(firstLastName[1].charAt(0));
        ArrayList<Passenger> folder = this.folders.get(String.valueOf(letter));
        return searchFolder(folder, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);

    }

    public Passenger searchFolder(ArrayList<Passenger> folder, String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        for (Passenger passenger : folder) {
            if (passenger.getFullName().equals(passengerName) && passenger.getFlightNumber() == flightNumber && passenger.getSeatClass() == seatClass && passenger.getSeatWaitNumber() == seatNumber && passenger.getIsWaitListed() == isWaitListed) {
                return passenger;
            }
        }
        return null;
    }

    public void removeFile(String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed) {
        String[] firstLastName = passengerName.split(" ");
        char letter = Character.toUpperCase(firstLastName[1].charAt(0));
        ArrayList<Passenger> folder = this.folders.get(String.valueOf(letter));
        searchFolderRemoveFile(folder, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
    }

    public void searchFolderRemoveFile(ArrayList<Passenger> folder, String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        Iterator<Passenger> iterator = folder.iterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (passenger.getFullName().equals(passengerName) && passenger.getFlightNumber() == flightNumber && passenger.getSeatClass() == seatClass && passenger.getSeatWaitNumber() == seatNumber && passenger.getIsWaitListed() == isWaitListed) {
                iterator.remove();
            }
        }
    }
}
