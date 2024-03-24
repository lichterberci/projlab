package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Student extends Actor {
    private boolean droppedOut;
    private final List<Charge> gasProtections = new ArrayList<>();
    private final List<Charge> dropOutProtections = new ArrayList<>();

    @Override
    public void DropOut() {
        droppedOut = true;
    }

    public void AddCharge(Charge c) {
        switch (c.GetPriority()) {
            case 0:
                gasProtections.add(c);
                break;
            case 1:
                dropOutProtections.add(c);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + c.GetPriority());
        }
    }

    public void RemoveCharge(Charge c) {
    }

    public boolean IsDroppedOut() {
        return droppedOut;
    }

    @Override
    public void TimePassed() {

    }

    @Override
    public void VisitActor(ActorVisitor v) {
        v.VisitStudent(this);
    }

    @Override
    public void Shock() {
        if (AskTheUser.decision("Does the player have a mask?")) {
            var gasProtection = gasProtections.stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Student does not have a mask"));
            IndentedDebugPrinter.getInstance().invokeObjectMethod(this, gasProtection, "Affect", Collections.emptyList());
            gasProtection.Affect();
            IndentedDebugPrinter.getInstance().returnFromMethod(this, gasProtection, "Affect", Optional.empty());
        }
    }
}
