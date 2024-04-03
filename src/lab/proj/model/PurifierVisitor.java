package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;

public class PurifierVisitor implements RoomEffectVisitor {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    public PurifierVisitor() {
        Logger.createObject(this);
    }

    public void VisitGasPoisoning(RoomEffect effect){
        Logger.invokeMethod(this, Collections.singletonList(effect));

        effect.location.RemoveEffect(effect);

        Logger.returnVoid();
    }

    public void VisitCurse(RoomEffect effect) {
        Logger.invokeMethod(this, Collections.singletonList(effect));

        // Empty.

        Logger.returnVoid();
    }
}
