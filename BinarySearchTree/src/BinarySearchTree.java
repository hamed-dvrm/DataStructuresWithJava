import javafx.geometry.Pos;
import sun.reflect.generics.tree.Tree;

import java.awt.image.BandedSampleModel;
import java.sql.PseudoColumnUsage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>>  {
    TreeNode<E> root;


    BinarySearchTree(){
        root = null;
    }

    BinarySearchTree(TreeNode<E> node){
        this.root = node;
    }

    public boolean search(E value){
       TreeNode<E> curr = root;
       TreeNode<E> searchNode = new TreeNode<>(value);

       while (curr!= null){
           int result = curr.compareTo(searchNode);

           if (result == 0 ) return true;
           else if ( result < 0 ) curr = curr.rightChild;
           else if (result > 0 ) curr = curr.leftChild;

       }
       return false;

    }
    public boolean insert(E value){
        TreeNode<E> insertNode = new TreeNode<>(value);
        TreeNode<E> curr = root;
        if (root == null){
            root = insertNode;
            return true;
        }
        while(true){
            int result = curr.compareTo(insertNode);
            if (result > 0 ){
                if (curr.leftChild == null) {
                    curr.leftChild = insertNode;
                    return true;
                }
                curr = curr.leftChild;
            }
            else if (result < 0 ) {
                if (curr.rightChild == null) {
                    curr.rightChild = insertNode;
                    return true;
                }
                curr = curr.rightChild;
            }
            else
                return false;
        }

    }

    private void inOrderTraversal(TreeNode<E> node){
        if (node == null){
            return;
        }
        inOrderTraversal(node.leftChild);
        System.out.print(node.value + ",");
        inOrderTraversal(node.rightChild);


    }

    private void preOrderTraversal(TreeNode<E> node){
        if (node == null){
            return;
        }
        System.out.print(node.value + ",");
        preOrderTraversal(node.leftChild);
        preOrderTraversal(node.rightChild);
    }

    private void postOrderTraversal(TreeNode<E> node){
        if (node == null){
            return;
        }
        postOrderTraversal(node.leftChild);
        postOrderTraversal(node.rightChild);
        System.out.print(node.value + ",");
    }

    private void levelOrderTraversal(TreeNode<E> node){
        Queue<TreeNode<E>> eQueue = new LinkedList<>();
        eQueue.add(node);
        while (!eQueue.isEmpty()){
            TreeNode<E> leftChild = eQueue.peek().leftChild;
            TreeNode<E> rightChild = eQueue.peek().rightChild;
            if (leftChild != null){
                eQueue.add(leftChild);
            }
            if (rightChild != null){
                eQueue.add(rightChild);
            }
            System.out.print(eQueue.poll() + ",");

        }

    }

    public boolean delete(E value){
        this.root = deleteNode(value,this.root);
        return true;

    }

    private TreeNode<E> deleteNode(E value,TreeNode<E> root){
        TreeNode<E> curr = root;
        TreeNode<E> prev = null;
        TreeNode<E> deletedNode = new TreeNode<E>(value);

        if (!search(value)){
            return null;
        }

        while (curr != null){
            int comp = curr.compareTo(deletedNode);
            if (comp > 0 ){
                prev = curr;
                curr = curr.leftChild;
            }
            else if (comp < 0 ){
                prev = curr;
                curr = curr.rightChild;
            }
            else {
                if (curr.rightChild == null && curr.leftChild == null){   // The node that we want to delete has no child (a leaf node )
                    if (prev == null){      // The node that we are deleting is the root.
                        curr = null;
                        return root;

                    }
                    if (prev.leftChild == curr){
                        prev.leftChild = null;
                        return root;
                    }
                    else if (prev.rightChild == curr){
                        prev.rightChild = null;
                        return root;
                    }
                    else {
                        System.out.println("There is a problem");
                        return null;
                    }

                }       // The node that we want to delete has no child (a leaf node )
                else if (curr.rightChild != null && curr.leftChild != null){
                    if (prev == null){  // The node that we want to delete is the root
                        TreeNode<E> leftMostNode = curr.rightChild;
                        while (leftMostNode.leftChild != null){
                            leftMostNode = leftMostNode.leftChild;
                        }
                        root.setValue(leftMostNode.value);
                        root.rightChild = deleteNode(leftMostNode.value,curr.rightChild);
                        return root;
                    }
                    else {
                        TreeNode<E> leftMostNode = curr.rightChild;
                        while (leftMostNode.leftChild != null){
                            leftMostNode = leftMostNode.leftChild;
                        }
                        curr.setValue(leftMostNode.value);
                        curr.rightChild = deleteNode(leftMostNode.value,curr.rightChild);
                        return root;
                    }

                }  // The node that we want to delete has two children
                else{   // The node that we want to delete has one child.
                    if (prev == null){ // This is a root node
                        root = (curr.rightChild != null) ?  curr.rightChild : curr.leftChild;
                        return root;
                    }
                    else {
                        TreeNode<E> saveNode = (curr.leftChild!= null) ? curr.leftChild : curr.rightChild;
                        if (prev.rightChild == curr){
                            prev.rightChild = saveNode;
                            return root;
                        }
                        else if (prev.leftChild == curr){
                            prev.leftChild = saveNode;
                            return root;
                        }
                        else {
                            System.out.println("We have a problem here #2");
                            return null;
                        }
                    }
                }                                                         // The node that we want to delete has one child.
            }
        }
        return null;


    }

    public void printTree(){
        System.out.print("Inorder traversal : ");
        inOrderTraversal(root);
        System.out.println();
        System.out.print("PreOrder traversal : ");
        preOrderTraversal(root);
        System.out.println();
        System.out.print("PostOrder traversal : ");
        postOrderTraversal(root);
        System.out.println();
        System.out.print("LevelOrder traversal : ");
        levelOrderTraversal(root);
        System.out.println();
    }

    public int getSize(){
        return size(this.root);

    }

    private int size(TreeNode<E> root){
        if (root == null){
            return 0;
        }
        return 1 + (size(root.rightChild) + size(root.leftChild));

    }

    public int getHeight(){
        return height(this.root);
    }

    public int getHeightNode(E value){
        TreeNode<E> curr = this.root;
        TreeNode<E> goal = new TreeNode<>(value);
        if (!search(value)){
            return -1;
        }
        while (curr != null){
            int comp = goal.compareTo(curr);
            if (comp > 0 ){
                curr = curr.rightChild;
            }
            else if (comp < 0 )
                curr = curr.leftChild;
            else if (comp == 0 ){
                return height(curr);
            }

        }

        return -1;

    }

    private int height(TreeNode<E> root){
        if (root== null) {
            return 0;
        }
        return 1 + Math.max(height(root.rightChild),height(root.leftChild));


    }

    public void inOrderIterative(){
        Stack<TreeNode<E>> treeNodeStack = new Stack<>();
        TreeNode<E> temp = root;


        if (root == null ){
            System.out.println("The tree is empty");
            return;
        }

        while (!treeNodeStack.isEmpty()  || temp != null){
            while (temp != null ){
                treeNodeStack.push(temp);
                temp = temp.leftChild;

            }
            temp = treeNodeStack.pop();
            System.out.print(temp);
            temp = temp.rightChild;

        }
        return;
    }

    public void preOrderIterative(){
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> curr = root;

        if (curr == null){
            return;
        }

        while (!stack.isEmpty() || curr != null){
            while (curr != null){
                System.out.print(curr + ",");
                stack.push(curr);
                curr = curr.leftChild;
            }
            curr = stack.pop();
            curr = curr.rightChild;

        }
        return;
    }

    public void postOrderIterative(){
        TreeNode<E> curr = root;
        Stack<TreeNode<E>> stack = new Stack<>();
        Stack<TreeNode<E>> stack2 = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()){
            curr = stack.pop();
            stack2.push(curr);
            if (curr.leftChild != null){
                stack.push(curr.leftChild);
            }
            if (curr.rightChild != null){
                stack.push(curr.rightChild);
            }

        }

       while (!stack2.isEmpty()){
           curr = stack2.pop();
           System.out.print(curr + ",");
       }
        return;



    }

    public E getMinValue(){
        return (getMinValue(this.root));
    }

    private E getMinValue(TreeNode<E> root){
        TreeNode<E> curr = root;

        while (curr.leftChild != null){
            curr = curr.leftChild;

        }
        return curr.value;
    }

    public E getMaxValue(){
        return getMaxValue(this.root);
    }

    private E getMaxValue(TreeNode<E> root){
        TreeNode<E> curr = root;

        while (curr.rightChild != null){
            curr = curr.rightChild;
        }
        return curr.value;
    }

    public boolean isBST(){
        return isBST(this.root);
    }

    private boolean isBST(TreeNode<E> root){
       /* if (root == null)
            return true;
        if (root.rightChild != null && getMinValue(root.rightChild).compareTo(root.value) < 0)
            return false;
        if (root.leftChild != null &&   getMaxValue(root.leftChild).compareTo(root.value) > 0){
            return false;
        }
         if  (!isBST(root.rightChild) || !isBST(root.leftChild))
                return false;
        return true;*/

       TreeNode<E> curr = root;
       E prev = null;
       Stack<TreeNode<E>> stack = new Stack<>();

       while (!stack.isEmpty() || curr !=null){
           while (curr != null){
               stack.push(curr);
               curr = curr.leftChild;
           }

           curr = stack.pop();
           if (prev != null && curr.value.compareTo(prev) < 0 )
               return false;
           prev = curr.value;
           curr = curr.rightChild;

       }
       return true;



    }

    public E getInorderSuccessor(E value){
        if (!search(value)){
            return null;
        }
        TreeNode<E> successor = null;
        TreeNode<E> ancestor = root;
        TreeNode<E> curr = root;

        while (curr!= null){
            int comp = value.compareTo(curr.value);
            if (comp > 0 ){
                curr = curr.rightChild;
            }
            if (comp < 0 ){
                curr = curr.leftChild;
            }
            else if (comp == 0){
                break;
            }
        }

        if (curr.rightChild != null){
            return getMinValue(curr.rightChild);
        }
        else{
            while (ancestor != curr){
                if (ancestor.compareTo(curr) > 0){
                    successor = ancestor;
                    ancestor = ancestor.leftChild;
                }
                else {
                    ancestor = ancestor.rightChild;
                }
            }
        }
        return successor.value;



    }


}
