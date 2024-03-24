package lab.proj.model;


public abstract class Charge {
    private final int priority;
    protected final LivingItem creator;
    protected Charge(LivingItem creator, int priority) {
        this.creator = creator;
        this.priority = priority;
    }
    int GetPriority() {
        return priority;
    }
    abstract void Affect();
}
