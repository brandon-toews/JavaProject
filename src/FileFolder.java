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
        ArrayList<Passenger> folder = this.folders.get(String.valueOf(letter));
        folder.add(newPassenger);
        insertionSortFolder(folder);
    }

    public Passenger getFile(String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        String[] firstLastName = passengerName.split(" ");
        char letter = Character.toUpperCase(firstLastName[1].charAt(0));
        ArrayList<Passenger> folder = this.folders.get(String.valueOf(letter));
        return searchFolder(folder, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);

    }

    public void insertionSortFolder(ArrayList<Passenger> folder){
        for (int i = 1; i < folder.size(); i++) {
            Passenger key = folder.get(i);
            int j = i - 1;
            while (j >= 0 && (folder.get(j).getLastName().compareTo(key.getLastName()) > 0
                    || (folder.get(j).getLastName().compareTo(key.getLastName()) == 0
                    && folder.get(j).getFirstName().compareTo(key.getFirstName()) > 0))) {
                folder.set(j + 1, folder.get(j));
                j = j - 1;
            }
            folder.set(j + 1, key);
        }
    }

    public Passenger searchFolder(ArrayList<Passenger> folder, String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        for (Passenger passenger : folder) {
            if (passenger.getFullName().equals(passengerName) && passenger.getFlightNumber() == flightNumber && passenger.getSeatClass() == seatClass && passenger.getSeatWaitNumber() == seatNumber && passenger.getIsWaitListed() == isWaitListed) {
                return passenger;
            }
        }
        return null;
    }

    public String[] getFiles(String firstName, String lastName){
        char letter = Character.toUpperCase(lastName.charAt(0));
        ArrayList<Passenger> folder = this.folders.get(String.valueOf(letter));
        return binarySearchFolder(folder, firstName, lastName);
    }

    public String[] binarySearchFolder(ArrayList<Passenger> folder, String firstName, String lastName){
        ArrayList<String> passengers = new ArrayList<String>();
        int left = 0;
        int right = folder.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Passenger passenger = folder.get(mid);
            if (passenger.getFirstName().equals(firstName) && passenger.getLastName().equals(lastName)) {
                passengers.add("Name: "+passenger.getFullName()+
                        ", Flight #: "+passenger.getFlightNumber()+
                        ", Seat Class: "+passenger.getSeatClass()+
                        ", Seat #: "+passenger.getSeatWaitNumber()+
                        ", Waitlisted: "+passenger.getIsWaitListed());
                int i = mid - 1;
                while (i >= 0 && folder.get(i).getFirstName().equals(firstName) && folder.get(i).getLastName().equals(lastName)) {
                    passengers.add("Name: "+folder.get(i).getFullName()+
                            ", Flight #: "+folder.get(i).getFlightNumber()+
                            ", Seat Class: "+folder.get(i).getSeatClass()+
                            ", Seat #: "+folder.get(i).getSeatWaitNumber()+
                            ", Waitlisted: "+folder.get(i).getIsWaitListed());
                    i--;
                }
                i = mid + 1;
                while (i < folder.size() && folder.get(i).getFirstName().equals(firstName) && folder.get(i).getLastName().equals(lastName)) {
                    passengers.add("Name: "+folder.get(i).getFullName()+
                            ", Flight #: "+folder.get(i).getFlightNumber()+
                            ", Seat Class: "+folder.get(i).getSeatClass()+
                            ", Seat #: "+folder.get(i).getSeatWaitNumber()+
                            ", Waitlisted: "+folder.get(i).getIsWaitListed());
                    i++;
                }
                return passengers.toArray(new String[0]);
            } else if (passenger.getLastName().compareTo(lastName) < 0 || passenger.getLastName().equals(lastName)
            && (passenger.getFirstName().compareTo(firstName) < 0 || passenger.getFirstName().equals(firstName))) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return passengers.toArray(new String[0]);
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
