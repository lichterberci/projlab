package lab.proj.model;

import java.util.List;

public class SlideRule extends Item {
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());
    }
}
