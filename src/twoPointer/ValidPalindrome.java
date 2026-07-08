package array.twoPointer;

public class ValidPalindrome {
    static boolean isValid(String s){
        String str = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        int left = 0, right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "Was it a car or a cat I saw?";
        System.out.println(isValid(str));
    }
}
