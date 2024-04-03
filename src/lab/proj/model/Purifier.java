package lab.proj.model;

import java.util.List;

public class Purifier extends Item {
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        Logger.returnVoid();
    }

    @Override
    public void Activate() {
        Logger.invokeMethod(this, List.of());
        if (fake) {
            Logger.returnVoid();
            return;
        }

        activated = true;

        Room room = actor.GetLocation();

        room.VisitEffects(new PurifierVisitor());
        Drop();

        Logger.returnVoid();
    }
}
