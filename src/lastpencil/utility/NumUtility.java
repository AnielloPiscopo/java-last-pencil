package lastpencil.utility;

import java.io.BufferedReader;
import java.io.IOException;

public class NumUtility {
    public static int getNumGreaterThanX(int x, BufferedReader br) throws IOException {
        int num = Integer.parseInt(br.readLine().trim());

        if (num <= x) {
            throw new IllegalArgumentException();
        }

        return num;
    }

    public static int getNumInRange(int min, int max, BufferedReader br) throws IOException {
        int num = Integer.parseInt(br.readLine().trim());

        if(num<=min || num>max){
            throw new IllegalArgumentException();
        }

        return num;
    }
}
