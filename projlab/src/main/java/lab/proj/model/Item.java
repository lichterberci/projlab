package lab.proj.model;

public abstract class Item implements Entity {
    private boolean activated;
    private boolean dead;
    private Actor actor;
    public boolean PickUp(Actor a) {
        throw new RuntimeException();
    }
    
    public void Drop() {
    }
    
    public boolean IsPickedUp() {
        return actor != null;
    }
    
    public void Activate() {
    }
    
    public boolean IsActivated() {
        return activated;
    }
    
    public void ApplyCharges() {
    }
}
