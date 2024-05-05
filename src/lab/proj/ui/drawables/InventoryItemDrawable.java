package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventoryItemDrawable implements Drawable {
    private final JButton button;
    private final Item item;
    public InventoryItemDrawable(Item item) {
        this.item = item;
        button = new JButton("item");
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setForeground(Application.DarkText);
        button.setBackground(Application.Light);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        if (item != null)
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e))
                        item.Activate();
                    else if (SwingUtilities.isRightMouseButton(e))
                        item.Drop();
                }
            });
    }
    @Override
    public void Draw(JComponent target) {
        button.setEnabled(item != null);
        button.setPreferredSize(new Dimension(
                (int) (target.getMinimumSize().getWidth()),
                (int) (target.getMinimumSize().getHeight())));
        button.setFont(button.getFont().deriveFont((float) (target.getMinimumSize().getHeight() * 0.5f)));
        target.add(button);
    }
}
