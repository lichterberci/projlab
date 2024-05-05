package lab.proj.ui.components;

import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;

public class InventoryComponent extends Component {
    @Override
    public void Draw(JComponent target) {
        target.setOpaque(false);
        target.setLayout(new BoxLayout(target, BoxLayout.X_AXIS));

        for (Drawable item : drawables) {
            Dimension size = new Dimension(
                    (int) ((double) target.getWidth() / drawables.size()),
                    target.getHeight());
            JPanel itemPanel = new JPanel();
            itemPanel.setMinimumSize(size);
            itemPanel.setMaximumSize(size);
            itemPanel.setOpaque(false);
            item.Draw(itemPanel);

            target.add(itemPanel);
        }
    }
}
