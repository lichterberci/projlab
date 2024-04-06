package lab.proj.utils;

import java.util.List;
import java.util.Random;

public class Randomware {

    private static final int DEFAULT_SEED = 0;
    private static final Random random = new Random(DEFAULT_SEED);
    private Randomware() {
    }

    public static void SetSeed(int seed) {
        random.setSeed(seed);
    }

    public static <T> T Choice(List<T> collection) {
        int choiceIndex = random.nextInt(collection.size());
        return collection.get(choiceIndex);
    }

    public static boolean Decision() {
        return random.nextBoolean();
    }

    /**
     * Generates a random number from the given interval.
     *
     * @param from lower bound
     * @param to   upper bound
     * @return a random number between [from; to] (inclusive)
     */
    public static int Number(int from, int to) {
        return random.nextInt(from, to + 1);
    }
}
