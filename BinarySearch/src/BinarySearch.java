public class BinarySearch<E extends Comparable<E> > {

    public  int BinarySearch(E [] valueList,E value){
        int high = valueList.length -1;
        int low = 0;

        while (low <= high){
            int mid = high+low/2;
            int comp = value.compareTo(valueList[mid]);
            if (comp > 0){
                low = mid + 1;

            }
            else if (comp < 0 ){
                high = mid -1;
            }
            else if (comp == 0 ){
                return 1;
            }
        }
        return -1;
    }

    public int BinarySearchRecursive(E[] valueList, int low , int high, E number) {
       if (high < low ){
           return -1;
       }
       int mid = low + (high - low) /2;
       int comp = number.compareTo(valueList[mid]);
       if (comp == 0 ){
           return mid;
       }
       else if (comp > 0 ){
           return BinarySearchRecursive(valueList,mid + 1 ,high,number);
       }
       else if (comp < 0 ){
           return BinarySearchRecursive(valueList,low,mid-1,number);
       }
       return -1;
    }
    int search(Integer[] nums, int target) {
        int low = 0;
        int high = nums.length -1;
        int pivot = -1;
        while (pivot == -1){
            int mid = low + (high - low) /2;
            if (nums[mid +1] > nums[high])
                low = mid+1;
            else if (nums[low] > nums[mid-1])
                high = mid -1;
            else
                pivot = mid;
        }
        low = 0;
        high =nums.length-1;
        if (target == nums[pivot])
            return pivot;
        else if (target >= nums[pivot+1] && target <= nums[high])
            low = pivot +1;
        else if (target <= nums[pivot-1] && target >= nums[low])
            high = pivot -1;
        while (low <= high){
            int mid = low + (high - low)/2;
            if (target == nums[mid])
                return mid;
            if (target > nums[mid])
                low = mid+1;
            if (target < nums[mid])
                high = mid -1;
        }
        return -1;
    }


}




