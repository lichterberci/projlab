package lab.proj.model;

import lab.proj.utils.DebugPrinter;
import lab.proj.utils.SequenceDiagramPrinter;

import java.util.List;

/**
 * An interface representing an entity in the game environment.
 * Entities are objects that can be affected by the passage of time.
 */
public interface Entity {

    /**
     * A logger for debugging purposes.
     */
    DebugPrinter Logger = SequenceDiagramPrinter.getInstance();

    /**
     * Performs actions associated with the passage of time.
     * Each implementing class must define its own behavior for this method.
     */
    default void TimePassed() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }
}
