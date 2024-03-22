import java.util.Arrays;

public class BinaryTree {

    private BinaryNode rootNode ;



    public BinaryTree(BinaryNode root) {
        this.setRootNode(root);
    }

    public BinaryTree(int rootValue) {
        this.setRootNode(new BinaryNode(rootValue));
    }

    public BinaryTree(int[] numArray) {
        this.setRootNode(new BinaryNode(numArray[0], String.valueOf(numArray[0])));

        if (numArray.length > 1) {

            // set the children
            this.getRootNode().setChildren(Arrays.copyOfRange(numArray, 1, numArray.length));
        }
    }

    private void setRootNode(BinaryNode rootNode) {
        this.rootNode = rootNode;
    }

    public BinaryNode getRootNode() {
        return this.rootNode;
    }

    public void printTree(){
        printTree(this.getRootNode());
    }

    private void printTree(BinaryNode node){
        if (node == null){
            return;
        }
        printTree(node.getLeftNode());
        System.out.println(node.getValue());
        printTree(node.getRightNode());
    }

}
