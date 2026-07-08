package array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TESCO

public class TopKFrequentElements {
    static List<Integer> findTopKFrequent(int[] arr, int k){   // Approach-1
        Map<Integer, Integer> map = new HashMap<>();
        for(int a : arr){
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        return map.entrySet().stream().sorted((a,b) -> b.getValue() - a.getValue()).map(a -> a.getKey()).limit(k).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int arr[] = {1,2,2,3,3,3,4,4,4,4,4,4};
        int k = 2;
        System.out.println(findTopKFrequent(arr,k));
    }
}

// Approach 1: HashMap + Sort, time O(n log n), space O(n)
// Approach 1: HashMap + Heap, time O(n log n), space O(n)
// Approach 1: QuickSelect, time O(n) worst case O(n*n), space O(n)
// Approach 1: BucketSort, time O(n), space O(n)
