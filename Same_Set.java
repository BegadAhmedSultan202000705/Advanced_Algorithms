package submisson;
import java.util.Arrays;

public class Same_Set {

    public static boolean same_Set(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return false;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {2, 4, 1, 5, 3};
        boolean result = same_Set(arr1, arr2);
        System.out.println("Arrays have the same set of numbers: " + result);
    }
}