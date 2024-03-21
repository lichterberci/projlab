package lab.proj.model;

public class Student extends Actor {
    private boolean droppedOut;
    private Charge gasProtections;
    private Charge dropOutProtections;
    public void DropOut() {
    }
    
    public void AddCharge(Charge c) {
    }
    
    public void RemoveCharge(Charge c) {
    }

	public boolean IsDroppedOut() {
        return droppedOut;
    }

    @Override
    public void TimePassed() {

    }
}
