import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileFolder {
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

    public void addFile(Passenger newPassenger) {
        char folder = newPassenger.getLastName().charAt(0);
        switch (folder) {
            case 'A':
                this.A.add(newPassenger);
                break;
            case 'B':
                this.B.add(newPassenger);
                break;
            case 'C':
                this.C.add(newPassenger);
                break;
            case 'D':
                this.D.add(newPassenger);
                break;
            case 'E':
                this.E.add(newPassenger);
                break;
            case 'F':
                this.F.add(newPassenger);
                break;
            case 'G':
                this.G.add(newPassenger);
                break;
            case 'H':
                this.H.add(newPassenger);
                break;
            case 'I':
                this.I.add(newPassenger);
                break;
            case 'J':
                this.J.add(newPassenger);
                break;
            case 'K':
                this.K.add(newPassenger);
                break;
            case 'L':
                this.L.add(newPassenger);
                break;
            case 'M':
                this.M.add(newPassenger);
                break;
            case 'N':
                this.N.add(newPassenger);
                break;
            case 'O':
                this.O.add(newPassenger);
                break;
            case 'P':
                this.P.add(newPassenger);
                break;
            case 'Q':
                this.Q.add(newPassenger);
                break;
            case 'R':
                this.R.add(newPassenger);
                break;
            case 'S':
                this.S.add(newPassenger);
                break;
            case 'T':
                this.T.add(newPassenger);
                break;
            case 'U':
                this.U.add(newPassenger);
                break;
            case 'V':
                this.V.add(newPassenger);
                break;
            case 'W':
                this.W.add(newPassenger);
                break;
            case 'X':
                this.X.add(newPassenger);
                break;
            case 'Y':
                this.Y.add(newPassenger);
                break;
            case 'Z':
                this.Z.add(newPassenger);
                break;
        }
    }

    public Passenger getFile(String passengerName, int flightNumber, Flight.SeatClass seatClass, int seatNumber, boolean isWaitListed){
        String[] firstLastName = passengerName.split(" ");
        char folder = firstLastName[1].charAt(0);
        switch (folder) {
            case 'A':
                return searchFolder(this.A, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'B':
                return searchFolder(this.B, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'C':
                return searchFolder(this.C, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'D':
                return searchFolder(this.D, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'E':
                return searchFolder(this.E, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'F':
                return searchFolder(this.F, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'G':
                return searchFolder(this.G, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'H':
                return searchFolder(this.H, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'I':
                return searchFolder(this.I, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'J':
                return searchFolder(this.J, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'K':
                return searchFolder(this.K, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'L':
                return searchFolder(this.L, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'M':
                return searchFolder(this.M, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'N':
                return searchFolder(this.N, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'O':
                return searchFolder(this.O, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'P':
                return searchFolder(this.P, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'Q':
                return searchFolder(this.Q, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'R':
                return searchFolder(this.R, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'S':
                return searchFolder(this.S, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'T':
                return searchFolder(this.T, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'U':
                return searchFolder(this.U, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'V':
                return searchFolder(this.V, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'W':
                return searchFolder(this.W, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'X':
                return searchFolder(this.X, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'Y':
                return searchFolder(this.Y, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
            case 'Z':
                return searchFolder(this.Z, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
        }
        return null;
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
        char folder = firstLastName[1].charAt(0);
        switch (folder) {
            case 'A':
                searchFolderRemoveFile(this.A, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'B':
                searchFolderRemoveFile(this.B, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'C':
                searchFolderRemoveFile(this.C, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'D':
                searchFolderRemoveFile(this.D, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'E':
                searchFolderRemoveFile(this.E, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'F':
                searchFolderRemoveFile(this.F, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'G':
                searchFolderRemoveFile(this.G, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'H':
                searchFolderRemoveFile(this.H, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'I':
                searchFolderRemoveFile(this.I, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'J':
                searchFolderRemoveFile(this.J, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'K':
                searchFolderRemoveFile(this.K, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'L':
                searchFolderRemoveFile(this.L, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'M':
                searchFolderRemoveFile(this.M, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'N':
                searchFolderRemoveFile(this.N, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'O':
                searchFolderRemoveFile(this.O, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'P':
                searchFolderRemoveFile(this.P, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'Q':
                searchFolderRemoveFile(this.Q, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'R':
                searchFolderRemoveFile(this.R, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'S':
                searchFolderRemoveFile(this.S, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'T':
                searchFolderRemoveFile(this.T, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'U':
                searchFolderRemoveFile(this.U, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'V':
                searchFolderRemoveFile(this.V, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'W':
                searchFolderRemoveFile(this.W, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'X':
                searchFolderRemoveFile(this.X, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'Y':
                searchFolderRemoveFile(this.Y, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
            case 'Z':
                searchFolderRemoveFile(this.Z, passengerName, flightNumber, seatClass, seatNumber, isWaitListed);
                break;
        }
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
