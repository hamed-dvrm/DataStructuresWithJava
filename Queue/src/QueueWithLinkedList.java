public class QueueWithLinkedList {

    Node head;
    Node tail;

    static class Node{
        int data;
        Node next;

        public Node(int number){
            this.data = number;
            next = null;
        }
    }

    public QueueWithLinkedList(){
        head = null;
        tail = null;
    }

    public void Enqueue(int number){
        Node newNode = new Node(number);
        if (IsEmpty()){
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
        return;

    }

    public boolean IsEmpty(){
        if (head == null){
            return true;
        }
        return false;
    }

    public int Dequeue(){
        if(IsEmpty()){
            System.out.println("The list is empty");
            return -1;
        }
        if (GetSize() == 1){
            int result = head.data;
            head = null;
            tail = null;
            return result;
        }
        int result = head.data;
        head = head.next;
        return result;

    }

    public int GetSize(){
        if (IsEmpty()){
            return 0;
        }
        int counter = 0;
        Node pointer = head;
        while (pointer != null){
            counter++;
            pointer = pointer.next;
        }
        return counter;
    }



}
