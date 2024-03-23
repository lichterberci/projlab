package lab.proj.model;




public class DropOutProtection extends Charge {
	public DropOutProtection(LivingItem creator) {
		super(creator);
	}

	@Override
	public int GetPriority() {
		return 1;
	}

	@Override
	public void Affect() {

	}
}
