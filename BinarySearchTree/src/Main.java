public class Main {
    public static void main(String[] args){
        BinarySearchTree<Integer> BST = new BinarySearchTree<>();

        BST.insert(1);
        BST.insert(-1);
        BST.insert(0);
        BST.insert(2);
        BST.insert(3);

        System.out.println(BST.isBST());

    }
}
