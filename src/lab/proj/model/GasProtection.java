package lab.proj.model;

import lab.proj.utils.SequenceDiagramPrinter;

import java.util.List;

/**
 * A class representing a gas protection charge associated with a living item in the game environment.
 * Gas protections can affect the behavior of the associated living item.
 */
public class GasProtection extends Charge {

    /**
     * A logger for debugging purposes.
     */
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    /**
     * Constructs a new gas protection charge with the specified creator and priority.
     *
     * @param creator  The living item that created this gas protection.
     * @param priority The priority level of this gas protection.
     */
    public GasProtection(LivingItem creator, int priority) {
        super(creator, priority);
        Logger.createObject(this);
    }

    /**
     * Retrieves the priority level of this gas protection.
     * This method overrides the GetPriority method of the Charge class and always returns 0.
     *
     * @return The priority level of this gas protection.
     */
    @Override
    public int GetPriority() {
        Logger.invokeMethod(this, List.of());
        Logger.returnValue(0);
        return 0;
    }

    /**
     * Affects the behavior of the associated living item.
     * This method invokes the "Use" method of the creator living item.
     */
    @Override
    public void Affect() {
        Logger.invokeMethod(this, List.of());

        creator.Use();

        Logger.returnVoid();
    }
}
