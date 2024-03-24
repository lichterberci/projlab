package lab.proj.model;

/**
 * An abstract class representing a living item in the game environment.
 * Living items extend the functionality of basic items and have a limited lifetime.
 */
public abstract class LivingItem extends Item {

    /** The remaining lifetime of the living item. */
    private int lifetime;

    /**
     * Performs the action associated with using the living item.
     * Each subclass must implement this method to define the specific action.
     */
    public abstract void Use();
}
