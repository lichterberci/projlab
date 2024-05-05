package lab.proj.ui.components;

import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;

public class ChargeComponent extends Component {
    @Override
    public void Draw(JComponent target) {
        target.setLayout(new BoxLayout(target, BoxLayout.X_AXIS));
        target.setOpaque(false);

        for (Drawable actor : drawables) {
            Dimension size = new Dimension(
                    (int) (target.getWidth() * 0.4),
                    (int) (target.getHeight() * 0.8));
            JPanel actorPanel = new JPanel();
            actorPanel.setMinimumSize(size);
            actorPanel.setMaximumSize(size);
            actorPanel.setOpaque(false);
            actor.Draw(actorPanel);

            target.add(actorPanel);
        }
    }
}