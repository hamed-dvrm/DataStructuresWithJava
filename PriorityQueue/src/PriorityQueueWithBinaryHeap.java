import java.util.ArrayList;
import java.util.Collections;

public class PriorityQueueWithBinaryHeap<K extends Comparable<K>> {
    private int size;

    ArrayList<K> heapList;

    public PriorityQueueWithBinaryHeap(){
        size = 0;
        heapList = new ArrayList<>();
        heapList.add(0,null);
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        if (size==0)
            return true;
        return false;

    }

    public void insert(K value){
        int insertedIndex = size + 1;
        heapList.add(insertedIndex,value);
        size++;
        siftUp(insertedIndex);
        return;

    }

    public K extractMax(){
        if (isEmpty()){
            System.out.println("There is no element here");
            return null;
        }
        K result = heapList.get(1);
        Collections.swap(heapList,size,1);
        heapList.remove(size);
        size--;
        siftDown(1);
        return result;
    }

    public void PrintPriorityQueue(){
        for(K temp : heapList){
            System.out.println(temp);
        }
    }





    private void siftUp(int index){
        while (index > 1) {
            int parentIndex = index / 2;
            if (heapList.get(index).compareTo(heapList.get(parentIndex)) > 0) {
                Collections.swap(heapList,index,parentIndex);
                index = parentIndex;
            }
            else
                break;
        }

    }

    private void siftDown(int index){
        while (index <= size && (index*2 <=size)){
            if(heapList.get(index).compareTo(heapList.get(findMax(leftChildIndex(index),rightChildIndex(index)))) >= 0)
                break;
            int swapIndex = findMax(leftChildIndex(index),rightChildIndex(index));
            Collections.swap(heapList,index,swapIndex);
            index = swapIndex;


        }

    }

    private int findMax(int firstIndex , int secondIndex){
        if (secondIndex >size)
            return firstIndex;
        if (heapList.get(firstIndex).compareTo(heapList.get(secondIndex))>= 0)
            return firstIndex;
       return secondIndex;

    }

    private int leftChildIndex(int index){
        return index*2;

    }
    private  int rightChildIndex(int index){
        return index*2+1;
    }
















}
