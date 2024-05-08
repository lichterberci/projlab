package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.Item;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventoryItemDrawable extends Drawable {
    private final JButton button = new JButton();
    private final Item item;
    public InventoryItemDrawable(Item item) {
        this.item = item;
        SetDefaults(button, Application.DarkText, Application.Light);
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        if (item != null)
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        item.Activate();
                        GameManager.GetInstance().EndTurn();
                    }
                    else if (SwingUtilities.isRightMouseButton(e))
                        item.Drop();
                    Application.GetInstance().RenderGameScreen();
                }
            });
    }
    @Override
    public void Draw(JComponent target) {
        if (item != null) {
            button.setText(item.toString());
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
        SetRelativeSizes(button, target, 0.5, 1.0, 1.0);
        target.add(button);
    }
}
