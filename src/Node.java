import java.util.HashMap;

public class Node {
    private String name;
    private float x;
    private float y;
    private HashMap<Node, Integer> myNeighbors = new HashMap<>();

    public Node(String name, float x, float y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Function to add a neighbor to the list of neighbors
    public void addNeighbor(Node neighbor){
        this.myNeighbors.put(neighbor, getDistance(neighbor));
    }

    // Function to get the list of neighbors
    public HashMap<Node, Integer> getMyNeighbors() {
        return myNeighbors;
    }

    // Function to get a list of Strings of the neighbors' names
    public String[] getNeighborNames(){
        String[] names = new String[myNeighbors.size()];
        int i = 0;
        for(Node neighbor : myNeighbors.keySet()){
            names[i] = neighbor.getName();
            i++;
        }
        return names;
    }

    // Function to get the distance between two nodes
    public int getDistance(Node neighbor){
        return (int) Math.sqrt(Math.pow(this.x - neighbor.getX(), 2) + Math.pow(this.y - neighbor.getY(), 2));
    }

}
