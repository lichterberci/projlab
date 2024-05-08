package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.Item;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class InventoryItemDrawable extends Drawable {
    private final JButton button = new JButton();
    private final Item item;
    private final String name;
    public InventoryItemDrawable(Item item, String name, MouseListener ml) {
        this.item = item;
        this.name = name;
        SetDefaults(button, Application.DarkText, Application.Light);
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        if (ml != null)
            button.addMouseListener(ml);
    }
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
