import java.awt.*;
import java.util.Objects;

public class LinearProbingHashTable <Key,Value> {
    private static final int INIT_CAPACITY = 4;


    private HashTableNode[] hashList;
    private int m_capacity;
    private int m_size;

    private class HashTableNode{
        Key key;
        Value value;

        public HashTableNode(Key key , Value value){
            this.key = key;
            this.value = value;
        }

        public HashTableNode(){
            key = null;
            value = null;
        }
    }

    public LinearProbingHashTable(int capacity){
        hashList =(HashTableNode[]) new Object[capacity];
        m_size = 0;
        m_capacity = capacity;
    }

    public LinearProbingHashTable(){
        this(INIT_CAPACITY);
    }

    private int  hash(Key key){
        int  result =  (key.hashCode() & 0x7fffffff) % m_capacity;
        return result;
    }

    private void resize(int newCapacity){
        LinearProbingHashTable<Key,Value> temp = new LinearProbingHashTable<>(newCapacity);
        for (int i = 0 ; i < m_capacity ; i++){
            if (hashList[i] != null){
                temp.put(hashList[i].key,hashList[i].value);
            }
        }
        m_capacity = newCapacity;
        hashList = temp.hashList;

    }

    public void put(Key key , Value value){
        if (key == null){
            throw new IllegalArgumentException("First argument can not be null");
        }
        if (value == null){
            delete(key);
            return;
        }
        if (m_size > m_capacity/2){
            resize(m_capacity*2);
        }

        int hashCode = hash(key);
        int i = hashCode;
        while (hashList[i] != null){
            if (hashList[i].key.equals(key)){
                hashList[i].value = value;
                return;
            }


            i = ( i+1) % m_capacity;

        }
        HashTableNode tempNode = new HashTableNode(key,value);
        hashList[i] = tempNode;
        m_size++;


    }

    public boolean  contains(Key key){
        if (key == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
        int i;
        for (i = hash(key) ; hashList[i] != null ;  i = ((i+1) % m_capacity)){
            if (hashList[i].key.equals(key))
                return true;
        }
        return false;
    }



    public void delete(Key key){
        if (key == null)
            throw new IllegalArgumentException("The argument can not be null");
        if (!contains(key)){
            return;

        }

        int i;
        for(i = hash(key); hashList[i] != null ; i = (i+1) % m_capacity){
            if (hashList[i].key.equals(key)){
                hashList[i] = null;
                m_size--;
                break;
            }
        }
        i = (i+1) % m_capacity;
        while (hashList[i] != null){
            HashTableNode temp = hashList[i];
            hashList[i] = null;
            put (temp.key,temp.value);
            i = (i +1) % m_capacity;
            m_size--;
        }



        if (m_size < m_capacity/4){
            resize(m_capacity/2);
        }

    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("The key can not be null");
        if (!contains(key)) {
            System.out.println("There is no element in  hash list with that key");
            return null;
        }
        int i;
        for (i = hash(key) ; hashList[i]!= null ; i = (i+1) %m_capacity ){
            if (hashList[i].key.equals(key)){
                return hashList[i].value;
            }
        }
        return null;
    }


}