package lab.proj.model;


public abstract class Charge {
    LivingItem creator;

    protected Charge(LivingItem creator) {
        this.creator = creator;
    }

    abstract int GetPriority();

    abstract void Affect();
}
