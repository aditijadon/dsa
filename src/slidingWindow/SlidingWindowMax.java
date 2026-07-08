package array.slidingWindow;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMax {
    static List<Integer> maxSlidingWindow(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        if (arr == null || arr.length == 0 || k <= 0) return result;

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();  // Remove elements smaller than current element from back of deque
            }
            deque.offerLast(i);
            if (deque.peekFirst() == i - k) deque.pollFirst();
            if (i >= k - 1) result.add(arr[deque.peekFirst()]);
        }
        return result;
    }

    static void main() {
        int[] arr = {1,2,1,0,4,2,6};
        int k = 3;
        System.out.println(maxSlidingWindow(arr, k));
    }
}
