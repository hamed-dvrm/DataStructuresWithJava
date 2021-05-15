import com.sun.scenario.effect.Merge;

import java.util.Random;
import java.util.concurrent.ConcurrentMap;

public class Sort <K extends Comparable<K>> {

    public K[] BubbleSort(K[] valueList){
        for (int i = 0 ; i < valueList.length -1 ; i++){
            boolean swapped = false;
            for (int j = 0 ; j < valueList.length -1 - i ; j++){
                if (valueList[j].compareTo(valueList[j+1]) > 0  ){
                    swap(valueList,j,j+1);
                    swapped = true;
                }

            }
            if (swapped == false)
                return valueList;
        }
        printSortedResult(valueList);
        return valueList;
    }

    public K[] InsertionSort(K[] valueList){
        for (int i = 1 ; i < valueList.length ; i++){
            for (int j = i ; j > 0 ; j--){
                if (valueList[j].compareTo(valueList[j-1]) <0)
                    swap(valueList,j,j-1);
                else
                    break;;
            }

        }
        printSortedResult(valueList);
        return valueList;
    }

    public K[] SelectionSort(K[] valeList){
        for (int i = 0 ; i < valeList.length-1 ;i++){
            int minIndex = i;
            for (int j = minIndex +1  ; j<valeList.length-1 ; j++){
                if (valeList[minIndex].compareTo(valeList[j]) > 0){
                    minIndex = j;
                }
            }
            swap(valeList,minIndex,i);
        }
        printSortedResult(valeList);
        return valeList;
    }

    public void MergeSort(K[] valueList,int firstIndex,int lastIndex){



        if (firstIndex < lastIndex) {

            int mid = firstIndex + (lastIndex - firstIndex) / 2;
            MergeSort(valueList, firstIndex, mid);
            MergeSort(valueList, mid + 1, lastIndex);
            if (valueList[mid].compareTo(valueList[mid+1]) > 0 )
                merge(valueList, firstIndex, mid, lastIndex);

        }

    }

    public void QuickSort(K[] valueList){
        ShuffleArray(valueList);
        for (int i = 0; i < valueList.length ; i++){
            System.out.print(valueList[i] + ",");
        }
        System.out.println();
        quickSort(valueList,0,valueList.length-1);
    }

    public void LSDradixSort(String[] wordList , int width){
        int n = 256;


        String[] result = new String[wordList.length];



        for (int i = width -1  ; i >= 0 ; i--){
            int[] R = new int[n+1];
            for (int j = 0 ; j < wordList.length ; j++){
                R[wordList[j].charAt(i) +1 ]++;

            }

            for (int j = 0 ; j < 256 ; j++){
                R[j+1] += R[j];
            }

            for (int j = 0; j < wordList.length ; j++){
                result[R[wordList[j].charAt(i)]++] = wordList[j];
            }

            for (int j = 0 ; j < result.length ; j++){
                wordList[j] = result[j];
            }




        }


    }

    public void MSDradixSort(String[] wordList){
        String[] aux = new String[wordList.length];
        MSDradixSort(wordList,aux,0,wordList.length-1,0);

    }

    private void MSDradixSort(String[] wordList,String[] aux , int low , int high , int d){
        int R = 256;
        int[] count = new int[R+2];

        if (high <= low)
            return;

        for (int i = low; i <= high ; i++){
            count[charAt(wordList[i],d) + 2]++;
        }

        for (int i = 0 ; i < R+1 ;i++){
            count[i+1] += count[i];
        }

        for (int i = low ; i <= high ; i++){
            aux[count[charAt(wordList[i],d) + 1]++] = wordList[i];
        }

        for (int i = low ; i <= high ; i++){
            wordList[i] = aux[i- low];//???
        }
        for (int r = 0; r < R; r++)
            MSDradixSort(wordList, aux, low + count[r] , low + count[r+1] -1 , d+1);


    }

    private int charAt(String word,int d){
        if (d < word.length())
            return word.charAt(d);
        return -1;
    }

    private void quickSort(K[] valueList,int low , int high){
        if (low < high){
            int part = partition(valueList,low,high);

            quickSort(valueList,low,part-1);
            quickSort(valueList,part+1,high);
        }


    }

    private int partition(K[] valueList,int low, int high){
        int i = low+1;
        int j = high;
        while (true) {
            while (valueList[i].compareTo(valueList[low]) < 0) {
                if (i == high)
                    break;
                i++;
            }
            while (valueList[j].compareTo(valueList[low]) >= 0) {

                if (j == low)
                    break;
                j--;
            }
            if (i >= j)
                break;
            swap(valueList,i,j);
        }
        swap(valueList,j,low);
        return j;

    }

    private int ThreeWayPartition(K[] valeList,int low , int high){
        int i = low;
        int j = high;

        while (i <= j){
            if (valeList[i].compareTo(valeList[low]) > 0)
                swap(valeList,i,j--);
            else if (valeList[i].compareTo(valeList[low]) < 0){
                swap(valeList,low++,i++);
            }
            else
                i++;
        }
        return j;
    }  // need to rewrite !

    private void ShuffleArray(K[] valueList){
        Random rand = new Random();

        for (int i = 0 ; i < valueList.length ; i++){
            int randomized = rand.nextInt(valueList.length);
            swap(valueList,randomized,i);
        }
    }

    private void merge(K[] valueList,int firstIndex,int mid,int lastIndex){
        assert isSorted(valueList,firstIndex,mid);
        assert isSorted(valueList,mid+1,lastIndex);

        K[] aux = (K[]) new Comparable[lastIndex - firstIndex + 1];
        int j = 0;
        for (int i = firstIndex ; i <= lastIndex; i++){
            aux[j] = valueList[i];
            j++;

        }
        int i = 0;
        int k = firstIndex;
        int midPoint = (aux.length -1)/2;
        j = midPoint + 1;
        while (i <= midPoint && j <= aux.length-1 ){
            if (aux[i].compareTo(aux[j]) > 0){
                valueList[k] = aux[j];
                j++;
            }
            else {
                valueList[k] = aux[i];
                i++;
            }
            k++;

        }
        while (i <= midPoint){
            valueList[k] = aux[i];
            i++;
            k++;
        }
        while (j <= aux.length-1){
            valueList[k] = aux[j];
            j++;
            k++;
        }

        assert isSorted(valueList,firstIndex,lastIndex);



    }

    private int swap(K[] valeList,int firstIndex, int secondIndex){
        if (firstIndex < 0 || firstIndex > valeList.length-1 || secondIndex < 0  || secondIndex > valeList.length -1 ){
            throw new IllegalArgumentException("indexes were not right");
        }
        K temp = valeList[firstIndex];
        valeList[firstIndex] = valeList[secondIndex];
        valeList[secondIndex] = temp;
        return 1;

    }

    private void printSortedResult(K[] valueList){
        for (int i = 0 ; i < valueList.length ; i++){
            System.out.print(valueList[i] + ",");
        }
    }

    private boolean isSorted (Comparable[] valueList,int firstIndex,int secondIndex){
        for (int i = firstIndex ; i<secondIndex ; i++){
            if (valueList[i].compareTo(valueList[i+1]) > 0)
                return false;
        }
        return true;

    }




}
