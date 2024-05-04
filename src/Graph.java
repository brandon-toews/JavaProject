import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// Class to represent a graph
public class Graph {
    private HashMap<String, Node> myNodes = new HashMap<>(); // Hashmap to store nodes identified by their names

    // Constructor reads a CSV file and initializes nodes and edges
    public Graph(String csvFile){
        // Read the csv file to construct nodes and edges
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a node for each record starting from the second line
        for(int i = 1; i < records.size(); i++){
            addNode(records.get(i));
        }

        // Establish edges between nodes based on csv records
        for(int i = 1; i < records.size(); i++){
            for(int j = 3; j < records.get(i).size(); j++){
                if(!records.get(i).get(j).isEmpty()){
                    addEdge(this.myNodes.get(records.get(i).get(0)), this.myNodes.get(records.get(i).get(j)));
                }
            }
        }
    }

    // Adds a node from a list of strings, where the first element is the node name and the rest are coordinates
    public void addNode(List<String> records){
        float x = Float.valueOf(records.get(1));
        float y = Float.valueOf(records.get(2));
        this.myNodes.put(records.get(0), new Node(records.get(0), x, y));
    }

    // Establishes an edge between two nodes
    public void addEdge(Node node1, Node node2){
        node1.addNeighbor(node2);
    }

    // Getter for the hashmap of nodes
    public HashMap<String, Node> getMyNodes() {
        return myNodes;
    }

    // Returns a node by its name
    public Node getNode(String name){
        return myNodes.get(name);
    }
}
