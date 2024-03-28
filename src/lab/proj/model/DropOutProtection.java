package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a drop out protection charge associated with a living item in the game environment.
 * Drop out protections can affect the behavior of the associated living item.
 */
public class DropOutProtection extends Charge {

    /**
     * A logger for debugging purposes.
     */
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    /**
     * Constructs a new drop out protection charge with the specified creator and priority.
     *
     * @param creator  The living item that created this drop out protection.
     * @param priority The priority level of this drop out protection.
     */
    protected DropOutProtection(LivingItem creator, int priority) {
        
        super(creator, priority);
    }

    /**
     * Affects the behavior of the associated living item.
     * This method invokes the "Use" method of the creator living item.
     */
    @Override
    public void Affect() {
        
        
        creator.Use();
        
    }
}
