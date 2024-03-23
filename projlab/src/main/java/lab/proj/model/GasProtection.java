package lab.proj.model;


import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.Optional;

public class GasProtection extends Charge {
	public GasProtection(LivingItem creator) {
		super(creator);
	}

	@Override
	public int GetPriority() {
		return 0;
	}

	@Override
	public void Affect() {
		IndentedDebugPrinter.getInstance().invokeObjectMethod(this, creator, "Use", Collections.emptyList());
		creator.Use();
		IndentedDebugPrinter.getInstance().returnFromMethod(this, creator, "Use", Optional.empty());
	}
}
