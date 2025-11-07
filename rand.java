import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class rand {
    public static int[] rand(int amin, int amax) {
        Random random = new Random();
        int[] randomNumbers = new int[amax-amin];
        int min = amin;
        int max = amax;

        for (int i = 0; i < max; i++) {
            // Generate a random integer between min and max (inclusive)
            int randomNumber = random.nextInt(max - min + 1) + min;
            randomNumbers[i]=randomNumber;
        }

        return randomNumbers;
    }
}