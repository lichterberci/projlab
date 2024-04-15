package lab.proj.model;

import java.util.List;

/**
 * An abstract class representing a living item in the game environment.
 * Living items extend the functionality of basic items and have a limited lifetime.
 */
public abstract class LivingItem extends Item {

    private static final int DEFAULT_LIFETIME = 5;

    /**
     * The remaining lifetime of the living item.
     */
    protected int lifetime = DEFAULT_LIFETIME;

    /**
     * Performs the action associated with using the living item.
     * Each subclass must implement this method to define the specific action.
     */
    public abstract void Use();

    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        if (--lifetime <= 0) {
            lifetime = 0;
            dead = true;
            Drop();
        }

        Logger.returnVoid();
    }

}
