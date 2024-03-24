package lab.proj.model;


import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class BeerMug extends LivingItem {
	@Override
	public void TimePassed() {

	}

	@Override
	public void ApplyCharges() {
		DropOutProtection dp1 = new DropOutProtection(this, 0);

		IndentedDebugPrinter.getInstance().createObject(this, dp1, "dp1");

		IndentedDebugPrinter.getInstance().invokeObjectMethod(this, actor, "AddDropOutProtection", List.of(dp1));
		actor.AddDropOutProtection(dp1);
		IndentedDebugPrinter.getInstance().returnFromMethod(this, actor, "AddDropOutProtection", Optional.empty());
	}

	@Override
	public void Use() {
		DropOutProtection dp2 = new DropOutProtection(this, 0);
		IndentedDebugPrinter.getInstance().createObject(this, dp2, "dp2");

		IndentedDebugPrinter.getInstance().invokeObjectMethod(this, actor, "AddDropOutProtection", List.of(dp2));
		actor.AddDropOutProtection(dp2);
		IndentedDebugPrinter.getInstance().returnFromMethod(this, actor, "AddDropOutProtection", Optional.empty());
	}
}
