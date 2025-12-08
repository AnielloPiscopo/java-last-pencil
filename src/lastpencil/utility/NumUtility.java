package lastpencil.utility;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Utility class for handling numeric input from a BufferedReader.
 * Provides validation and constraint checking.
 */
public class NumUtility {
    private NumUtility() {
    }

    /**
     * Reads an integer greater than the specified threshold.
     *
     * @param x  minimum exclusive value
     * @param br input reader
     * @return integer strictly greater than x
     * @throws IOException              if an I/O error occurs
     * @throws NumberFormatException    if the input is not a valid number
     * @throws IllegalArgumentException if the number is not greater than x
     */
    public static int getNumGreaterThanX(int x, BufferedReader br) throws IOException {
        int num = Integer.parseInt(br.readLine().trim());

        if (num <= x) {
            throw new IllegalArgumentException("Number must be greater than " + x);
        }

        return num;
    }

    /**
     * Reads an integer value from the input that must be within a specific range.
     *
     * @param min minimum exclusive value
     * @param max maximum inclusive value
     * @param br  input reader
     * @return an integer such that min < value ≤ max
     * @throws IOException              if an I/O error occurs
     * @throws NumberFormatException    if the input is not a valid number
     * @throws IllegalArgumentException if the value is outside the allowed range
     */
    public static int getNumInRange(int min, int max, BufferedReader br) throws IOException {
        int num = Integer.parseInt(br.readLine().trim());

        if (num <= min || num > max) {
            throw new IllegalArgumentException("Number must be between " + min + " and " + max);
        }

        return num;
    }
}
