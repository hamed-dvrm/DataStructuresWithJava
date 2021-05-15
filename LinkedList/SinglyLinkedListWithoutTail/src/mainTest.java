public class mainTest {
    public static void main(String[] args) {
        List list = new List();
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.printList();
        list.reverse();
        list.printList();
        System.out.println(list.getValue_at(2));
        System.out.println(list.value_n_from_end(1));
        System.out.println(list.remove_value(5));
        list.remove_value(1);
        list.printList();
        list.erase(2);
        list.insert(2,5);
        list.printList();


    }
}


