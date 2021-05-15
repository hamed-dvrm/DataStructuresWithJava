import java.util.PriorityQueue;

public class Main {
    public static void main(String... args){
        PriorityQueueWithBinaryHeap<Integer> pQueue = new PriorityQueueWithBinaryHeap<>();
        Integer i = new Integer(42);
        Integer i2 = new Integer(29);
        Integer i3 = new Integer(18);
        Integer i4 = new Integer(14);
        Integer i5 = new Integer(7);
        Integer i6 = new Integer(18);
        Integer i7 = new Integer(12);
        Integer i8 = new Integer(11);
        Integer i9 = new Integer(13);
        pQueue.insert(i);
        pQueue.insert(i2);
        pQueue.insert(i3);
        pQueue.insert(i4);
        pQueue.insert(i5);
        pQueue.insert(i6);
        pQueue.insert(i7);
        pQueue.insert(i8);
        pQueue.insert(i9);
        pQueue.extractMax();
        pQueue.PrintPriorityQueue();


    }
  }


