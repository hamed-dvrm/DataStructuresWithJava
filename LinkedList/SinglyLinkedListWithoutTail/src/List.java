public class List {
    Node head;

    public List(){ // O(1)
        head = null;
    }

    public List(List next){ // O(1)
        head = next.head;
    }

    public int getSize(){
        if (head == null){
            return 0;
        }
        else{
            Node pointer = head;
            int counter = 1;
            while(pointer.next != null){
                counter++;
                pointer = pointer.next;
            }
            return counter;
        }
    }                                        // O(n)
    public boolean isEmpty(){
        if (head == null){
            return true;
        }
        else
            return false;
    }                                    // O(1)
    public int getValue_at(int index){
        if ((index > getSize() - 1) || (index < 0 )){
            System.out.println("There is not a element in the list with that index");
            return -1;
        }
        if (index == 0){
            return head.data;
        }
        Node pointer = head;
        for (int i = 0; i <index ; i++){
            pointer = pointer.next;

        }
        return pointer.data;
    }                           // O(n)
    public void pushFront(int value){ //O(1)
        Node newNode = new Node(value);
        if (isEmpty()){
            head = newNode;
            return;

        }
        newNode.next = head;
        head = newNode;
    }                            // O(1)
    public int popFront() {
        if (isEmpty()){
            System.out.println("The list is empty");
            return -1;
        }
        int result = head.data;
        head = head.next;
        return result;


    }                                      // O(1)
    public void pushBack(int value){
        Node newNode = new Node(value);
        if (isEmpty()){
            head = newNode;
            return;
        }
        Node pointer = head;
        while(pointer.next != null){
            pointer = pointer.next;

        }
        pointer.next = newNode;

    }                             // O(n)
    public int popBack(){
        if (isEmpty()){
            System.out.println("the list is empty");
            return -1;
        }
        if (getSize() == 1){
            int result = head.data;
            head = null;
            return result;
        }
        Node pointer = head;
        while (pointer.next.next != null){
            pointer = pointer.next;
        }
        int result = pointer.next.data;
        pointer.next = null;
        return result;

    }                                        // O(n)
    public int getFront(){
        if (isEmpty()){
            System.out.println("The list is empty");
            return -1;
        }
        return head.data;
    }                                       // O(1)
    public int getBack(){
        if (isEmpty()){
            System.out.println("The list is empty");
            return -1;
        }
        Node pointer = head;
        while (pointer.next != null){
            pointer = pointer.next;
        }
        return pointer.data;
    }                                        // O(n)
    public int insert(int index,int value){
        if (isEmpty() && index != 0){
            System.out.println("The list empty and index is not  feasiable");
            return -1;
        }
        if (index <0 || index > getSize()){
            System.out.println("The index is not true");
            return -1;
        }
        Node pointer = head;
        Node newNode = new Node(value);
        if (index == 0){
            newNode.next = head;
            head = newNode;
            return 1;
        }
        for (int i = 0 ; i<index -1 ; i++){
            pointer = pointer.next;
        }
        newNode.next = pointer.next;
        pointer.next = newNode;
        return 1;

    }                      // O(n)
    public int erase(int index){
        if (index < 0 || index > getSize() -1  || isEmpty()){
            System.out.println("The index is not true or the list is empty");
            return -1;
        }

        if (index == 0 ){
            head = head.next;
            return 1;

        }
        Node pointer = head;
        for (int i = 0 ; i < index - 1 ; i++){
            pointer = pointer.next;
        }
        pointer.next = pointer.next.next;
        return 1;


    }                                 // O(n)
    public int value_n_from_end(int index){
        int target = (getSize() - 1 ) - index;
        return (getValue_at(target));
    }                      // O(n)
    public int reverse(){
        if (isEmpty() || getSize() == 1){
            System.out.println("There is no element in the list ");
            return 1;
        }

        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return 1;

    }                                        // O(n)
    public int remove_value(int value){
        if (isEmpty()){
            System.out.println("The list is empty");
            return -1;
        }
        Node prev = null;
        Node pointer = head;
        while (pointer != null){
            if (pointer.data == value){
                prev.next = pointer.next;
                return 1;
            }
            prev = pointer;
            pointer = pointer.next;
        }
        return -1;
    }                          // o(n)
    public void printList(){
        Node pointer = head;
        while (pointer != null){
            System.out.print(pointer.data + "->");
            pointer = pointer.next;
        }
        System.out.println();
    }                                     // O(n)



}
