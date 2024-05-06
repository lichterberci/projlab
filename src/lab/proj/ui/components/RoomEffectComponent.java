package lab.proj.ui.components;

import javax.swing.*;

public class RoomEffectComponent extends Component {
    @Override
    public void Draw(JComponent target) {
        target.setLayout(new BoxLayout(target, BoxLayout.X_AXIS));
        target.setOpaque(false);

        AddDrawables(0.4, 1.0, target);
    }
}
