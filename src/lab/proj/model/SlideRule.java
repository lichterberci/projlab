package lab.proj.model;

import java.util.List;

public class SlideRule extends Item {

    public SlideRule() {
        Logger.createObject(this);
    }

    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        Logger.returnVoid();
    }

    @Override
    public boolean PickUp(Actor a) {
        Logger.invokeMethod(this, List.of(a));

        boolean success = super.PickUp(a);
        if (success)
            a.NotifyStudentWin(this);

        Logger.returnValue(success);
        return success;
    }
}
