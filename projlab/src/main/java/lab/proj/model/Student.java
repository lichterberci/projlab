package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Student extends Actor {
    private boolean droppedOut;
    
    public void RemoveCharge(Charge c) {
    }

	public boolean IsDroppedOut() {
        return droppedOut;
    }

    @Override
    public void TimePassed() {
		gasProtections.clear();
		dropOutProtections.clear();
		for (Item i : collectedItems) {
			IndentedDebugPrinter.getInstance().invokeObjectMethod(this, i, "ApplyCharges", new ArrayList<>());
			i.ApplyCharges();
			IndentedDebugPrinter.getInstance().returnFromMethod(this, i, "ApplyCharges", Optional.empty());
		}
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

	@Override
	public void DropOut() {
		if (dropOutProtections.isEmpty()) {
			droppedOut = true;
		} else {
			DropOutProtection dropOutProtection = dropOutProtections.get(0);
			IndentedDebugPrinter.getInstance().invokeObjectMethod(this, dropOutProtection, "Affect", new ArrayList<>());
			dropOutProtection.Affect();
			IndentedDebugPrinter.getInstance().returnFromMethod(this, dropOutProtection, "Affect", Optional.empty());
			dropOutProtections.remove(0);
		}
	}
}
