package submisson;

import java.util.Arrays;

public class Remove_duplicates {

    public static char[] remove_Duplicates(char[] input) {
        if (input == null || input.length == 0) {
            return input;
        }

        boolean[] seen = new boolean[256];  
        int writeIndex = 0;

        for (int i = 0; i < input.length; i++) {
            char current_Char = input[i];
            if (!seen[current_Char]) {
                seen[current_Char] = true;
                input[writeIndex] = current_Char;
                writeIndex++;
            }
        }

        return Arrays.copyOf(input, writeIndex);
    }

    public static void main(String[] args) {
        char[] input = {'a', 'b', 'c', 'a', 'd', 'e', 'e', 'b'};
        char[] output = remove_Duplicates(input);
        System.out.println("Input array: " + Arrays.toString(input));
        System.out.println("Output array: " + Arrays.toString(output));
    }
}