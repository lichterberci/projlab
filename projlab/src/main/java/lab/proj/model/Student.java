package lab.proj.model;

import java.util.List;

public class Student extends Actor {
    private boolean droppedOut;
    private List<Charge> gasProtections;
    private List<Charge> dropOutProtections;
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
