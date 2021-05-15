public class TreeNode<E extends Comparable<E>> implements Comparable<TreeNode<E>> {
    E value;
    TreeNode<E> leftChild;
    TreeNode<E> rightChild;

    TreeNode(E value){
        this.value = value;
        leftChild = null;
        rightChild = null;
    }

    @Override
    public int compareTo(TreeNode<E> o) {
        return value.compareTo(o.value);
    }

    public String toString(){
        return (value + "");
    }

    public void setValue(E value){
        this.value = value;
    }
}
