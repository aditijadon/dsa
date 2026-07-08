package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    static boolean hasDuplicate(int[] arr){
        Set<Integer> set = new HashSet<>();
        Arrays.stream(arr).forEach(a -> set.add(a));
        return set.size() == arr.length ? false : true;
    }

    public static void main(String[] args) {
        int arr[] = {1,3,6,8,2};
        System.out.println(hasDuplicate(arr));
    }
}
