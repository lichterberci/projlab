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
}
