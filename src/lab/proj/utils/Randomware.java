package lab.proj.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for generating random values.
 */
public class Randomware {
    /**
     * Default seed for the random number generator.
     */
    private static final int DEFAULT_SEED = 0;
    /**
     * Random number generator.
     */
    private static final Random random = new Random(DEFAULT_SEED);

    /**
     * Private constructor to prevent instantiation.
     */
    private Randomware() {
    }

    /**
     * Sets the seed of the random number generator.
     *
     * @param seed seed value
     */
    public static void SetSeed(int seed) {
        random.setSeed(seed);
    }

    /**
     * Generates a random element from the given collection.
     *
     * @param collection collection of elements
     * @param <T>        type of the elements
     * @return a random element from the collection
     */
    public static <T> T Choice(List<T> collection) {
        if (collection.isEmpty())
            return null;
        int choiceIndex = random.nextInt(collection.size());
        return collection.get(choiceIndex);
    }

    /**
     * Generates a random subset of the given collection.
     *
     * @param collection collection of elements
     * @param <T>        type of the elements
     * @return a random subset of the collection
     */
    public static <T> List<T> Subset(List<T> collection) {
        List<T> subset = new ArrayList<>();
        for (T item : collection)
            if (Decision())
                subset.add(item);
        return subset;
    }

    /**
     * Generates a random boolean value.
     *
     * @return a random boolean value
     */
    public static boolean Decision() {
        return random.nextBoolean();
    }

    /**
     * Generates a random boolean value with the given likelihood.
     *
     * @param likelihood likelihood of the result being true
     * @return a random boolean value
     */
    public static boolean Decision(float likelihood) {
        return random.nextFloat() <= likelihood;
    }

    /**
     * Generates a random number from the given interval.
     *
     * @param from lower bound
     * @param to   upper bound
     * @return a random number between [from; to] (inclusive)
     */
    public static int Number(int from, int to) {
        return random.nextInt(to + 1) + from;
    }
}
