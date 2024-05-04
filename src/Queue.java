public class Queue {
    int size;
    int currentAmount = 0;
    String[] queue;

    public Queue(int size){
        this.size = size;
        queue = new String[size];
    }

    public void enqueue(String name){
        if(currentAmount < size) {
            queue[currentAmount] = name;
            currentAmount++;
        } else {
            System.out.println("Queue is full");
        }
    }

    public String dequeue(){
        if(currentAmount > 0) {
            String name = queue[0];
            for(int i = 0; i < currentAmount - 1; i++){
                queue[i] = queue[i + 1];
            }
            queue[currentAmount - 1] = null;
            currentAmount--;
            return name;
        } else {
            System.out.println("Queue is empty");
            return null;
        }
    }

    public String popItem(int index){
        if(index < currentAmount){
            String name = queue[index];
            for(int i = index; i < currentAmount - 1; i++){
                queue[i] = queue[i + 1];
            }
            queue[currentAmount - 1] = null;
            currentAmount--;
            return name;
        } else {
            System.out.println("Index out of bounds");
            return null;
        }
    }

    public void printQueue(){
        for(int i = 0; i < currentAmount; i++){
            System.out.println(queue[i]);
        }
    }

    public String[] getQueue(){
        return queue;
    }

    public int getSize(){
        return size;
    }

    public int getCurrentAmount(){
        return currentAmount;
    }
}
