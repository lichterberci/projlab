package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Item;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.Arrays;

/**
 * Represents the inventory item drawable
 */
public class InventoryItemDrawable extends Drawable {

    /**
     * The button
     */
    private final JButton button = new JButton();

    /**
     * The item
     */
    private final Item item;

    /**
     * The name
     */
    private final String name;

    /**
     * Creates a new inventory item drawable
     *
     * @param item The item
     * @param name The name
     * @param ml   The mouse listener
     */
    public InventoryItemDrawable(Item item, String name, MouseListener ml) {
        this.item = item;
        this.name = name;
        SetDefaults(button, Application.DarkText, Application.Light);
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        if (ml != null)
            button.addMouseListener(ml);
    }

    /**
     * Creates a new inventory item drawable
     */
    @Override
    public void Draw(JComponent target) {
        boolean isEnabled = false;
        if (item != null) {
            button.setText(name);
            isEnabled = true;
        }
        if (!isEnabled)
            Arrays.stream(button.getMouseListeners()).forEach(button::removeMouseListener);

        SetRelativeSizes(button, target, 0.5, 1.0, 1.0);
        target.add(button);
    }
}
