package lab.proj.ui.components;

import lab.proj.controller.Application;

import javax.swing.*;

public class InventoryComponent extends Component {
    @Override
    public void Draw(JComponent target) {
        target.setLayout(new BoxLayout(target, BoxLayout.X_AXIS));
        target.setOpaque(false);

        AddDrawables(1.0 / drawables.size(), 1.0, target);
    }
}
