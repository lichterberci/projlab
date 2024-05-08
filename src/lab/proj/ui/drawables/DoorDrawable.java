package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.Actor;
import lab.proj.model.Door;
import lab.proj.model.Room;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class DoorDrawable extends Drawable {
    private final JButton button = new JButton();
    private final Door door;

    public DoorDrawable(Door door, MouseListener ml) {
        this.door = door;
        SetDefaults(button, Application.DarkText, Application.Light);
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        if (ml != null)
            button.addMouseListener(ml);
    }
    @Override
    public void Draw(JComponent target) {
        boolean isEnabled = false;
        if (door != null) {
            isEnabled = door.Usable(GameManager.GetInstance().GetCurrentActor());
            String otherRoom = door.GetRooms().stream()
                    .filter(room -> room != GameManager.GetInstance().GetCurrentActor().GetLocation())
                    .findFirst()
                    .map(Room::GetName)
                    .orElse("");
            button.setText("<html><center>%s</center></html>".formatted(otherRoom));
        }
        button.setForeground(isEnabled ? Application.DarkText : Application.InvalidText);
        if (!isEnabled)
            Arrays.stream(button.getMouseListeners()).forEach(button::removeMouseListener);

        SetRelativeSizes(button, target, 0.3);
        target.add(button);
    }
}
