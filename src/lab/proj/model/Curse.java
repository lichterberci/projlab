package lab.proj.model;

import lab.proj.utils.Randomware;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a curse effect in the game environment.
 * Curse effects extend the functionality of room effects.
 */
public class Curse extends RoomEffect {

    /**
     * Creates a new curse effect.
     */
    public Curse() {
        Logger.createObject(this);
    }

    /**
     * Visits the curse effect.
     *
     * @param rev The visitor to visit the room effect.
     */
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

        boolean shouldHide = Randomware.Decision();
        if (shouldHide)
            for (Door door : location.GetDoors())
                door.Hide();

        Logger.returnVoid();
    }
}
