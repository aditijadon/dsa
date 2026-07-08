package array.binarySearch;

import java.util.Arrays;

/*
Given an array ‘arr of integer numbers, ‘ar[i]’ represents the number of pages in the ‘i-th’ book. There are a ‘m’
number of students, and the task is to allocate all the books to the students.Allocate books in such a way that:
Each student gets at least one book, Each book should be allocated to only one student, Book allocation should be in a
contiguous manner. You have to allocate the book to ‘m’ students such that the maximum number of pages assigned to a
student is minimum. If the allocation of books is not possible. return -1
*/

public class BookAllocation {
    public static int allocateBooks(int[] arr, int n, int m) {
        if (m > n) return -1;  // If books are fewer than students, impossible (each gets at least one)
        Arrays.sort(arr);
        int low = arr[n-1];   // 111 - 112
        int high = Arrays.stream(arr).sum();;         // 203

        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(arr, m, mid)) {
                result = mid;    // This is a potential answer
                high = mid - 1;  // Try to find an even smaller "maximum"
            } else {
                low = mid + 1;   // The limit was too small, increase it
            }
        }
        return result;
    }

    private static boolean isPossible(int[] arr, int m, int maxPagesLimit) {
        int studentsCount = 1;
        int currentPagesSum = 0;
        for (int pages : arr) {
            if (currentPagesSum + pages <= maxPagesLimit) {
                currentPagesSum += pages;  // Add book to current student
            } else {
                studentsCount++;   // Give book to the next student
                currentPagesSum = pages;
                if (studentsCount > m) return false;  // If students exceed 'm', this limit is not possible
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 5, m = 3, arr[] = {25, 46, 28, 49, 24};
        System.out.println("Minimum possible maximum pages: " + allocateBooks(arr, arr.length, m));
    }
}
