package lab.proj.model;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CleaningLady extends Actor {

    private final PurifierVisitor pv;

    public CleaningLady() {
        Logger.createObject(this);
        pv = new PurifierVisitor();
    }

    @Override
    public void VisitActor(ActorVisitor v) {
        Logger.invokeMethod(this, List.of());
        v.VisitCleaningLady(this);
        Logger.returnVoid();
    }

    @Override
    public void Shock() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    @Override
    public void DropOut() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        location.VisitEffects(pv);
        location.CleanRoom();

        Logger.returnVoid();
    }

    @Override
    public void GetOut() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    @Override
    public boolean UseDoor(Door d) {
        Logger.invokeMethod(this, Collections.singletonList(d));

        boolean wasSuccessful = super.UseDoor(d);
        if (wasSuccessful)
            new CopyOnWriteArrayList<>(location.GetActors()).forEach(Actor::GetOut);

        Logger.returnValue(wasSuccessful);
        return wasSuccessful;
    }

    @Override
    public void NotifyStudentWin(SlideRule sr) {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }
}
