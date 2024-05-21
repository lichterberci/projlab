package lab.proj.model;

import java.util.Collections;

/**
 * A class representing a slide rule item in the game environment.
 * Slide rules can be picked up by actors in the game environment.
 */
public class SlideRule extends Item {

    /**
     * Creates a new slide rule item.
     */
    public SlideRule() {
        Logger.createObject(this);
    }

    /**
     * Attempts to pick up the slide rule with the specified actor.
     *
     * @param a The actor attempting to pick up the slide rule.
     * @return true if the slide rule is successfully picked up, false otherwise.
     */
    @Override
    public boolean PickUp(Actor a) {
        Logger.invokeMethod(this, Collections.singletonList(a));

        if (fake) {
            Logger.returnValue(false);
            return false;
        }

        boolean success = super.PickUp(a);
        if (success)
            a.NotifyStudentWin(this);

        Logger.returnValue(success);
        return success;
    }

    /**
     * Visits the slide rule item.
     *
     * @param iv The visitor to visit the item.
     */
    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitSlideRule(this);
    }
}
