package lab.proj.model;


import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.Optional;

public class DropOutProtection extends Charge {

	protected DropOutProtection(LivingItem creator, int priority) {
		super(creator, priority);
	}

	@Override
	public void Affect() {
		IndentedDebugPrinter.getInstance().invokeObjectMethod(this, creator, "Use", new ArrayList<>());
		creator.Use();
		IndentedDebugPrinter.getInstance().returnFromMethod(this, creator, "Use", Optional.empty());
	}
}
