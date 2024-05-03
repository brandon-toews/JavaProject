import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileFolder {
    private HashMap<String, ArrayList<Passenger>> folders = new HashMap<String, ArrayList<Passenger>>();;
    private ArrayList<Passenger> A = new ArrayList<Passenger>();
    private ArrayList<Passenger> B = new ArrayList<Passenger>();
    private ArrayList<Passenger> C = new ArrayList<Passenger>();
    private ArrayList<Passenger> D = new ArrayList<Passenger>();
    private ArrayList<Passenger> E = new ArrayList<Passenger>();
    private ArrayList<Passenger> F = new ArrayList<Passenger>();
    private ArrayList<Passenger> G = new ArrayList<Passenger>();
    private ArrayList<Passenger> H = new ArrayList<Passenger>();
    private ArrayList<Passenger> I = new ArrayList<Passenger>();
    private ArrayList<Passenger> J = new ArrayList<Passenger>();
    private ArrayList<Passenger> K = new ArrayList<Passenger>();
    private ArrayList<Passenger> L = new ArrayList<Passenger>();
    private ArrayList<Passenger> M = new ArrayList<Passenger>();
    private ArrayList<Passenger> N = new ArrayList<Passenger>();
    private ArrayList<Passenger> O = new ArrayList<Passenger>();
    private ArrayList<Passenger> P = new ArrayList<Passenger>();
    private ArrayList<Passenger> Q = new ArrayList<Passenger>();
    private ArrayList<Passenger> R = new ArrayList<Passenger>();
    private ArrayList<Passenger> S = new ArrayList<Passenger>();
    private ArrayList<Passenger> T = new ArrayList<Passenger>();
    private ArrayList<Passenger> U = new ArrayList<Passenger>();
    private ArrayList<Passenger> V = new ArrayList<Passenger>();
    private ArrayList<Passenger> W = new ArrayList<Passenger>();
    private ArrayList<Passenger> X = new ArrayList<Passenger>();
    private ArrayList<Passenger> Y = new ArrayList<Passenger>();
    private ArrayList<Passenger> Z = new ArrayList<Passenger>();

    public FileFolder(){
        //loop through the alphabet and add each letter to the folders
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            this.folders.put(String.valueOf(letter), new ArrayList<Passenger>());
        }
    }

    public void addFile(Passenger newPassenger) {
        char letter = newPassenger.getLastName().charAt(0);
        this.folders.get(String.valueOf(letter)).add(newPassenger);
    }

    public Passenger getFile(String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        String[] firstLastName = passengerName.split(" ");
        char letter = firstLastName[1].charAt(0);
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
        char letter = firstLastName[1].charAt(0);
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
