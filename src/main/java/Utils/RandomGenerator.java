package Utils;

import java.util.Random;

public class RandomGenerator {
    public static String getRandomNumber(int min, int max) {
        Random random = new Random();
        String randomNumber = String.valueOf(random.nextInt(max - min + 1) + min);
        return randomNumber;
    }
}
