package lab.proj.ui.components;

import lab.proj.controller.Application;
import lab.proj.ui.drawables.DoorDrawable;
import lab.proj.ui.drawables.StudentNameDrawable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DoorsComponent implements Component {

    private List<DoorDrawable> doors;
    @Override
    public void Draw(JComponent target) {
        target.setBackground(Application.Dark);
        target.setLayout(new BoxLayout(target, BoxLayout.X_AXIS));

        for (DoorDrawable door : doors) {
            Dimension size = new Dimension(
                    (int) (target.getWidth() * 0.2),
                    (int) (target.getHeight() * 0.9));
            JPanel doorPanel = new JPanel();
            doorPanel.setMinimumSize(size);
            doorPanel.setMaximumSize(size);
            doorPanel.setOpaque(false);
            door.Draw(doorPanel);

            target.add(doorPanel);
        }
    }

    public void SetDoors(List<DoorDrawable> doors) {
        this.doors = doors;
    }
}
