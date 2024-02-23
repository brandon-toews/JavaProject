import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private HashMap<String, Node> myNodes = new HashMap<>();

    // Constructor takes in a csv file and creates a node from each row
    public Graph(String csvFile){
        // Read the csv file
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

        // For each row, create a node
        for(int i = 1; i < records.size(); i++){
            addNode(records.get(i));
        }

        // For each node, add an edge to neighboring nodes
        for(int i = 1; i < records.size(); i++){
            for(int j = 3; j < records.get(i).size(); j++){
                if(records.get(i).get(j) != ""){
                    addEdge(this.myNodes.get(records.get(i).get(0)), this.myNodes.get(records.get(i).get(j)));
                }
            }
        }

    }

    // Function to add a node to list of nodes
    public void addNode(List<String> records){
        // Create a node from records
        float x = Float.valueOf(records.get(1));
        float y = Float.valueOf(records.get(2));

        // Add node to hashmap
        this.myNodes.put(records.get(0), new Node(records.get(0), x, y));

    }

    // Function to add an edge between two nodes
    public void addEdge(Node node1, Node node2){
        // Add an edge between node1 and node2
        node1.addNeighbor(node2);
    }

    // Function to get the list of nodes
    public HashMap<String, Node> getMyNodes() {
        return myNodes;
    }

    // Function to get node in list of nodes
    public Node getNode(String name){
        return myNodes.get(name);
    }


}
