import sun.awt.image.ImageWatched;

public class LinkedListWithTail {
    public Node head;
    public Node tail;

    public static class Node{
        int data;
        Node next;

        Node(int number){
            data = number;
            next = null;
        }
    }

    public static void main(String[] args){

    }

   public static int getSize(LinkedListWithTail list){
        if (list.head == null){
            return 0;
        }
        Node pointer = list.head;
        int counter = 0;
        while (pointer != null){
            counter ++;
            pointer = pointer.next;
        }
        return counter;

   }  // O(n)
   public static boolean isEmpty(LinkedListWithTail list){
        if (list.head != null){
            return false;
        }
        return true;
   } //O(1)
   public  static int getValue_at(LinkedListWithTail list , int index){
        if (index < 0 || index > getSize(list)-1){
            System.out.println("This list does not have that index");
            return -1;
        }


        Node pointer = list.head;
        for (int i = 0 ; i <index ; i++){
            pointer = pointer.next;
        }
        return pointer.data;

   } // O(n)
   public static void pushFront(LinkedListWithTail list , int value){
        Node newNode = new Node(value);
        if (list.head == null ){
            list.head = newNode;
            list.tail = newNode;
            return;
        }
        newNode.next = list.head;
        list.head = newNode;
        return;


   } //O(1)
   public static int popFront(LinkedListWithTail list){
        if (getSize(list) == 0){
            System.out.println("No element in the list ");
            return -1;

        }
        else if (getSize(list) == 1){
            int result = list.head.data;
            list.head = null;
            list.tail = null;
            return result;
        }
        int result = list.head.data;
        list.head = list.head.next;
        return result;
   } // O(1)
   public static void pushBack(LinkedListWithTail list, int value){
        Node newNode = new Node(value);

        if (getSize(list) == 0){
            list.head = newNode;
            list.tail = newNode;
            return;
        }
        list.tail.next = newNode;
        list.tail = newNode;
        return;
   } //O(1)
   public static int popBack(LinkedListWithTail list){
        if (isEmpty(list)){
            System.out.println("There is no element in the list");
            return -1;
        }
        else if (getSize(list) == 1){
            int reuslt = list.tail.data;
            list.tail = null;
            list.head = null;
            return reuslt;
        }
        Node pointer = list.head;
        while(pointer.next != list.tail){
            pointer = pointer.next;
        }
        int result = list.tail.data;
        list.tail = pointer;
        return result;
   } //? probably it will be O(n)
   public static int getFront(LinkedListWithTail list){
        if (isEmpty(list)){
            System.out.println("The list is empty");
            return -1;
        }
        return list.head.data;
   } //O(1)
   public static int getBack(LinkedListWithTail list){
        if (isEmpty(list)){
            System.out.println("The list is empty");
            return -1;
        }
        return list.tail.data;
   } //O(1)
   public static int insert(LinkedListWithTail list, int index , int value){
        Node newNode = new Node(value);
        if (index < 0 || index > getSize(list)){
            System.out.println("The index is not legal");
            return -1;
        }
        else if (index == getSize(list)){
            list.tail.next = newNode;
            list.tail = newNode;
            return 1;

        }
        else if (index == 0 && getSize(list) == 0){
            list.tail = newNode;
            list.head = newNode;
            return 1;
        }
        else if (index == 0){
            newNode.next = list.head;
            list.head = newNode;
            return 1;

        }
        Node pointer = list.head;
        for (int i = 0 ; i < index -1 ; i++){
            pointer = pointer.next;
        }
        newNode.next = pointer.next;
        pointer.next = newNode;
        return 1;

   }//O(n)
   public static int erase(LinkedListWithTail list , int index){
        if (index < 0 || index < getSize(list) -1){
            System.out.println("Illegal index");
            return -1;
        }
        if (index == 0){
            if (getSize(list) == 1){
                list.head = null;
                list.tail = null;
                return 1;
            }
            else{
                list.head = list.head.next;
            }
        }
        Node pointer = list.head;
        for (int i = 0 ; i < index-1 ; i++ ){
            pointer = pointer.next;

        }
        if (pointer.next == list.tail){
            list.tail = pointer;
            pointer.next = null;
            return 1;
        }
        pointer.next = pointer.next.next;
        return 1;


   }//O(n)
   public static int value_n_from_End(LinkedListWithTail list,int index) {
        int indexFromStart = getSize(list) -1 - index;
        return (getValue_at(list,indexFromStart));
   } //O(n)
   public static  int reverse(LinkedListWithTail list){
        if (isEmpty(list)){
            System.out.println("There is not element in the list");
            return 0;
        }
        if (getSize(list) == 1){
            System.out.println("The list is reversed right now");
            return 0;
        }
        Node prev = null;
        Node next = null;
        Node current = list.head;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        prev = list.head;
        list.head = list.tail;
        list.tail = prev;
        return 1;
   } //O(n)
   public static  int remove(LinkedListWithTail list, int value){
        if (isEmpty(list)){
            System.out.println("The list is already empty");
            return 0;
        }
        Node pointer = list.head;
        while (pointer != null){
            if (pointer.data == value){
                return value;
            }
            pointer = pointer.next;
        }
        System.out.println("The list does not have the value you have entered");
        return -1;
   }//O(n)
}
