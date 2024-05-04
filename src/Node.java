import java.util.HashMap;

// Class to represent a node in a graph
public class Node {
    private String name; // Name of the node
    private float x; // X-coordinate of the node
    private float y; // Y-coordinate of the node
    private HashMap<Node, Integer> myNeighbors = new HashMap<>(); // Map of neighbors and their distances

    // Constructor to initialize node with name and coordinates
    public Node(String name, float x, float y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // Getters and setters for the name of the node
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters and setters for the x-coordinate
    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // Getters and setters for the y-coordinate
    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Method to add a neighbor node with automatically calculated distance
    public void addNeighbor(Node neighbor){
        this.myNeighbors.put(neighbor, getDistance(neighbor));
    }

    // Getter for the map of neighbors
    public HashMap<Node, Integer> getMyNeighbors() {
        return myNeighbors;
    }

    // Method to get an array of neighbor names for easy access
    public String[] getNeighborNames(){
        String[] names = new String[myNeighbors.size()];
        int i = 0;
        for(Node neighbor : myNeighbors.keySet()){
            names[i] = neighbor.getName();
            i++;
        }
        return names;
    }

    // Method to calculate the Euclidean distance to a neighbor node
    public int getDistance(Node neighbor){
        return (int) Math.sqrt(Math.pow(this.x - neighbor.getX(), 2) + Math.pow(this.y - neighbor.getY(), 2));
    }

}

