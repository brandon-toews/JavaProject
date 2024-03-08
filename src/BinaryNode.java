import java.util.Arrays;

public class BinaryNode {
    private BinaryNode leftNode = null;
    private BinaryNode rightNode = null;

    private int value;
    private String title;

    public BinaryNode(int val){
        this.setValue(val);
    }

    public BinaryNode(int val, String title){
        this.setValue(val);this.setTitle(title);
    }

    public BinaryNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryNode rightNode) {
        this.rightNode = rightNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setChildren(BinaryNode left){
        setLeftNode(left);
    }

    public void setChildren(BinaryNode left, BinaryNode right){
        setLeftNode(left);
        setRightNode(right);
    }

    public void setChildren(int[] numArray){

        int depth = (int)(Math.log(numArray.length) / Math.log(2));

        setLeftNode(new BinaryNode(numArray[0], String.valueOf(numArray[0])));
        setRightNode(new BinaryNode(numArray[1], String.valueOf(numArray[1])));
        System.out.println("Level: " + depth);
        System.out.println("Left Node: " + getLeftNode().value + " Right Node: " + getRightNode().value);


        if (depth > 1){
            getLeftNode().setChildren(Arrays.copyOfRange(numArray, 2, numArray.length));
            getRightNode();
        }

    }
}
