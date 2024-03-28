package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;

/**
 * An abstract class representing a charge associated with a living item in the game environment.
 * Charges can affect various aspects of the game and are associated with a priority level.
 */
public abstract class Charge {

    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * The living item that created this charge.
     */
    protected final LivingItem creator;

    /**
     * The priority level of this charge.
     */
    private final int priority;

    /**
     * Constructs a new charge with the specified creator and priority.
     *
     * @param creator  The living item that created this charge.
     * @param priority The priority level of this charge.
     */
    protected Charge(LivingItem creator, int priority) {
        this.creator = creator;
        this.priority = priority;
    }

    /**
     * Retrieves the priority level of this charge.
     *
     * @return The priority level of this charge.
     */
    int GetPriority() {
        Logger.invokeMethod(this, List.of());
        Logger.returnValue(priority);
        return priority;
    }

    /**
     * Defines the effect of this charge.
     * Each subclass must implement this method to specify the effect of the charge.
     */
    abstract void Affect();
}
