public class QueueWithDynamicArray {
    int readIndex;
    int writeIndex;
    int[] array;

    public QueueWithDynamicArray(int number){
        readIndex = 0;
        writeIndex = 0;

        array = new int[number];

    }

    public QueueWithDynamicArray(){
        readIndex = 0;
        writeIndex = 0;
        array = new int[1];

    }
    public boolean IsEmpty(){
        if (readIndex == writeIndex){
            return true;
        }
        return false;
    }

    public int Dequeue(){
        if (IsEmpty()){
            System.out.println("The list is empty");
            return -1;
        }
        int result = array[readIndex];
        readIndex++;
        if (readIndex  > array.length - 1){

            readIndex = readIndex % array.length;
        }

        return result;
    }

    public void Enqueue(int number){
        if (isFull())
            resizeArray(array.length * 2);
        array[writeIndex] = number;
        writeIndex++;
        if (writeIndex > array.length -1 ){
            writeIndex = writeIndex % array.length;
        }

    }

    public boolean isFull(){
        if (writeIndex == (readIndex -1) ){
            return true;
        }
        if (writeIndex == array.length-1 && readIndex == 0 ){
            return true;
        }
        return false;
    }

    private void resizeArray(int newCapacity){
        int[] temp = new int[newCapacity];
        int i = readIndex;
        int j = 0;
        while (i != writeIndex){
            temp[j] = array[i];
            i++;
            if (i > array.length - 1 )
                i = i% array.length;
            j++;
        }
        readIndex = 0;
        writeIndex =  j;
        array = temp;
        System.out.println("The array has been resized");

    }

    public void printQueue(){
        int i = readIndex;
        if (IsEmpty()){
            System.out.println("The queue is empty");
            return;
        }
        while (i != writeIndex){
            System.out.println(array[i]);
            i++;
            if (i > array.length - 1 ){
                i = i % array.length;
            }
        }
    }
}
