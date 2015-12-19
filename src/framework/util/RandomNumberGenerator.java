package framework.util;

import java.util.Random;

/**
 * Created by michaelwomack on 12/16/15.
 */
public class RandomNumberGenerator {
    private static Random random = new Random();

    public static int getRandomIntBetween(int lower, int upper) {
        int num = random.nextInt(upper - lower) + lower;

        //Don't return 0
        if (num != 0)
            return num;
        else
            return getRandomIntBetween(lower, upper);
    }

    public static int getRandomNumber(int upper) {
        return random.nextInt(upper);
    }
}
