// Description: This class implements a queue data structure using an array. The queue has a maximum size and can store strings.
public class Queue {
    int size; // Maximum size of the queue
    int currentAmount = 0; // Current number of items in the queue
    String[] queue; // Array to store queue elements

    // Constructor to initialize the queue with a specific size
    public Queue(int size){
        this.size = size;
        queue = new String[size];
    }

    // Method to add a new element to the queue
    public void enqueue(String name){
        if(currentAmount < size) {
            queue[currentAmount] = name;
            currentAmount++;
        } else {
            System.out.println("Queue is full"); // Print message when queue is full
        }
    }

    // Method to remove and return the front element of the queue
    public String dequeue(){
        if(currentAmount > 0) {
            String name = queue[0]; // Store the front element
            // Shift all elements to the left
            for(int i = 0; i < currentAmount - 1; i++){
                queue[i] = queue[i + 1];
            }
            queue[currentAmount - 1] = null; // Nullify the last element
            currentAmount--; // Decrease the count of elements
            return name;
        } else {
            System.out.println("Queue is empty"); // Print message when queue is empty
            return null;
        }
    }

    // Method to remove and return the element at a specific index in the queue
    public String popItem(int index){
        if(index < currentAmount){
            String name = queue[index]; // Store the element to be removed
            // Shift all elements after the index to the left
            for(int i = index; i < currentAmount - 1; i++){
                queue[i] = queue[i + 1];
            }
            queue[currentAmount - 1] = null; // Nullify the last element
            currentAmount--; // Decrease the count of elements
            return name;
        } else {
            System.out.println("Index out of bounds"); // Print message when index is out of bounds
            return null;
        }
    }

    // Method to print all elements in the queue
    public void printQueue(){
        for(int i = 0; i < currentAmount; i++){
            System.out.println(queue[i]);
        }
    }

    // Getter for the queue array
    public String[] getQueue(){
        return queue;
    }

    // Getter for the size of the queue
    public int getSize(){
        return size;
    }

    // Getter for the current number of elements in the queue
    public int getCurrentAmount(){
        return currentAmount;
    }
}

