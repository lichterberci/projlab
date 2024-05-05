package lab.proj.ui.components;

import lab.proj.controller.Application;
import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;

public class DoorsComponent extends Component {
    @Override
    public void Draw(JComponent target) {
        target.setBackground(Application.Dark);
        target.setLayout(new BoxLayout(target, BoxLayout.X_AXIS));

        for (Drawable door : drawables) {
            Dimension size = new Dimension(
                    (int) (target.getWidth() * 0.25),
                    (int) (target.getHeight() * 0.9));
            JPanel doorPanel = new JPanel();
            doorPanel.setMinimumSize(size);
            doorPanel.setMaximumSize(size);
            doorPanel.setOpaque(false);
            door.Draw(doorPanel);

            target.add(doorPanel);
        }
    }
}
