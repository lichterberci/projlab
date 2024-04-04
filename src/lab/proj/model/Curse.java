package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.SequenceDiagramPrinter;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a curse effect in the game environment.
 * Curse effects extend the functionality of room effects.
 */
public class Curse extends RoomEffect {

    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    public Curse() {
        Logger.createObject(this);
    }

    @Override
    public void VisitRoomEffect(RoomEffectVisitor rev) {
        Logger.invokeMethod(this, Collections.singletonList(rev));

        rev.VisitCurse(this);

        Logger.returnVoid();
    }

    /**
     * Performs actions associated with the passage of time.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());
        List<Door> doors = location.GetDoors();
        boolean shouldHide = AskTheUser.decision("Érvényesül-e az átok?");
        if (shouldHide)
            for (Door door : doors)
                door.Hide();

        Logger.returnVoid();
    }
}
