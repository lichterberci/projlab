package lab.proj.ui.components;

import javax.swing.*;

/**
 * Represents the inventory component
 */
public class InventoryComponent extends Component {

    /**
     * Creates a new inventory component
     */
    @Override
    public void Draw(JComponent target) {
        target.setLayout(new BoxLayout(target, BoxLayout.X_AXIS));
        target.setOpaque(false);

        AddDrawables(1.0 / drawables.size(), 1.0, target);
    }
}
