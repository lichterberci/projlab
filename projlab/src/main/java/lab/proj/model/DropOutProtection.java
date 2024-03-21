package lab.proj.model;




public class DropOutProtection implements Charge {
	@Override
	public int GetPriority() {
		throw new RuntimeException();
	}

	@Override
	public void Affect() {

	}
}
