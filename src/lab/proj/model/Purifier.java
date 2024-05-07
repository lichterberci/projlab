package lab.proj.model;

import lab.proj.controller.GameManager;

import java.util.List;

public class Purifier extends Item {

    public Purifier() {
        Logger.createObject(this);
    }

    @Override
    public void Activate() {
        Logger.invokeMethod(this, List.of());

        if (fake) {
            Logger.returnVoid();
            return;
        }

        activated = true;

        Drop();
        location.VisitEffects(new PurifierVisitor());
        dead = true;

        GameManager.GetInstance().EndTurn();
        Logger.returnVoid();
    }
}
