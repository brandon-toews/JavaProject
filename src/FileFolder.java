import java.util.*;
// Class to represent a folder of files
public class FileFolder {
    // HashMap to store folders with the first letter of the last name as the key
    private HashMap<String, ArrayList<Passenger>> folders = new HashMap<String, ArrayList<Passenger>>();;

    // Constructor to initialize folders
    public FileFolder(){
        //loop through the alphabet and add each letter to the folders
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            this.folders.put(String.valueOf(letter), new ArrayList<Passenger>());
        }
    }

    // Add a file to the folder and sort it
    public void addFile(Passenger newPassenger) {
        // Get the first letter of the last name
        char letter = Character.toUpperCase(newPassenger.getLastName().charAt(0));
        // Get the folder corresponding to the first letter of the last name
        ArrayList<Passenger> folder = this.folders.get(String.valueOf(letter));
        // Add the new passenger to the folder
        folder.add(newPassenger);
        // Call the insertionSortFolder method on the folder
        insertionSortFolder(folder);
    }

    // Get a file from the folder
    public Passenger getFile(String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        // Split the passenger name into first and last names
        String[] firstLastName = passengerName.split(" ");
        // Get the first letter of the last name
        char letter = Character.toUpperCase(firstLastName[1].charAt(0));
        // Get the folder corresponding to the first letter of the last name
        ArrayList<Passenger> folder = this.folders.get(String.valueOf(letter));
        // Call the searchFolder method
        return searchFolder(folder, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
    }

    // Insertion sort for the folder
    private void insertionSortFolder(ArrayList<Passenger> folder){
        // Iterate through the folder
        for (int i = 1; i < folder.size(); i++) {
            // Get the passenger at the current index
            Passenger key = folder.get(i);
            // Initialize the index before the current index
            int j = i - 1;
            // Move elements of the folder that are greater than the key
            // to one position ahead of their current position
            // Compare last names first, then first names
            // to maintain alphabetical order
            while (j >= 0 && (folder.get(j).getLastName().compareTo(key.getLastName()) > 0
                    || (folder.get(j).getLastName().compareTo(key.getLastName()) == 0
                    && folder.get(j).getFirstName().compareTo(key.getFirstName()) > 0))) {
                // Move the element to the next position
                folder.set(j + 1, folder.get(j));
                // Update the index
                j = j - 1;
            }
            // Insert the key at the correct position
            folder.set(j + 1, key);
        }
    }

    // Linear search for a passenger in the folder
    private Passenger searchFolder(ArrayList<Passenger> folder, String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        // Split the passenger name into first and last names
        String[] firstLastName = passengerName.split(" ");
        // Use the binarySearchFolder method to get all passengers with the same first and last names
        ArrayList<Passenger> foundPassengers = binarySearchFolder(folder, firstLastName[0], firstLastName[1]);
        // Iterate through the found passengers
        for (Passenger passenger : foundPassengers) {
            // Return the passenger if all attributes match
            if (passenger.getFullName().equals(passengerName) && passenger.getFlightNumber() == flightNumber && passenger.getSeatClass() == seatClass && passenger.getSeatWaitNumber() == seatNumber && passenger.getIsWaitListed() == isWaitListed) {
                return passenger;
            }
        }
        // Return null if no passenger is found
        return null;
    }

    // Get all files with the same first and last names
    public String[] getFiles(String firstName, String lastName){
        // Get the first letter of the last name
        char letter = Character.toUpperCase(lastName.charAt(0));
        // Get the folder corresponding to the first letter of the last name
        ArrayList<Passenger> folder = this.folders.get(String.valueOf(letter));
        // Call the binarySearchFolder method to get all passengers with the same first and last names
        // in the folder
        ArrayList<Passenger> foundPassengers = binarySearchFolder(folder, firstName, lastName);
        // Extract the necessary information from all the passengers
        // match the first and last names given to form a String[]
        String[] passengerStrings = new String[foundPassengers.size()];
        for (int i = 0; i < foundPassengers.size(); i++) {
            Passenger passenger = foundPassengers.get(i);
            passengerStrings[i] = "Name: "+passenger.getFullName()+
                    ", Flight #: "+passenger.getFlightNumber()+
                    ", Seat Class: "+passenger.getSeatClass()+
                    ", Seat #: "+passenger.getSeatWaitNumber()+
                    ", Waitlisted: "+passenger.getIsWaitListed();
        }
        return passengerStrings;
    }

    // Binary search for a passenger in the folder
    private ArrayList<Passenger> binarySearchFolder(ArrayList<Passenger> folder, String firstName, String lastName){
        // Create an array list to store passengers
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        // Initialize left and right pointers
        int left = 0;
        int right = folder.size() - 1;
        // Perform binary search
        while (left <= right) {
            // Calculate the middle index
            int mid = left + (right - left) / 2;
            // Get the passenger at the middle index
            Passenger passenger = folder.get(mid);
            // Add the passenger to the list if the first and last names match
            if (passenger.getFirstName().equals(firstName) && passenger.getLastName().equals(lastName)) {
                // Add the passenger to the list
                passengers.add(passenger);
                // Add all passengers with the same first and last names
                // Add passengers before the middle index
                int i = mid - 1;
                while (i >= 0 && folder.get(i).getFirstName().equals(firstName) && folder.get(i).getLastName().equals(lastName)) {
                    passengers.add(folder.get(i));
                    i--;
                }
                // Add passengers after the middle index
                i = mid + 1;
                while (i < folder.size() && folder.get(i).getFirstName().equals(firstName) && folder.get(i).getLastName().equals(lastName)) {
                    passengers.add(folder.get(i));
                    i++;
                }
                // Return the list of passengers
                return passengers;
                // Update the left and right pointers
                // based on the comparison of first and last names
            } else if (passenger.getLastName().compareTo(lastName) < 0 || passenger.getLastName().equals(lastName)
                    && (passenger.getFirstName().compareTo(firstName) < 0 || passenger.getFirstName().equals(firstName))) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // Return an empty array if no passengers are found
        return passengers;
    }

    // Removes a passenger from the folder
    public void removeFile(String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed) {
        String[] firstLastName = passengerName.split(" ");
        // Get the first letter of the last name
        char letter = Character.toUpperCase(firstLastName[1].charAt(0));
        // Get the folder corresponding to the first letter of the last name
        ArrayList<Passenger> folder = this.folders.get(String.valueOf(letter));
        // Call the searchFolderRemoveFile method
        searchFolderRemoveFile(folder, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
    }

    // Removes a passenger from the folder
    private void searchFolderRemoveFile(ArrayList<Passenger> folder, String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        // Split the passenger name into first and last names
        String[] firstLastName = passengerName.split(" ");
        // Use the binarySearchFolder method to get all passengers with the same first and last names
        ArrayList<Passenger> foundPassengers = binarySearchFolder(folder, firstLastName[0], firstLastName[1]);
        // Iterate through the found passengers
        for (Passenger passenger : foundPassengers) {
            // If all attributes match, remove the passenger from the original folder
            if (passenger.getFullName().equals(passengerName) && passenger.getFlightNumber() == flightNumber && passenger.getSeatClass() == seatClass && passenger.getSeatWaitNumber() == seatNumber && passenger.getIsWaitListed() == isWaitListed) {
                folder.remove(passenger);
                break;  // Exit the loop once the passenger is found and removed
            }
        }
    }
}
