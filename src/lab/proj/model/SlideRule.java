package lab.proj.model;

import java.util.Collections;
import java.util.List;

public class SlideRule extends Item {

    public SlideRule() {
        Logger.createObject(this);
    }

    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

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
}
