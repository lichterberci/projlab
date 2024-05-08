package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.Actor;
import lab.proj.model.Door;
import lab.proj.model.Room;

import javax.swing.*;
import java.util.Arrays;

public class DoorDrawable extends Drawable {
    private final JButton button = new JButton();
    private final Door door;
    private final Actor actor;

    public DoorDrawable(Door door, Actor actor) {
        this.door = door;
        this.actor = actor;
        SetDefaults(button, Application.DarkText, Application.Light);
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        if (door != null && actor != null)
            button.addActionListener(actionEvent -> {
                actor.UseDoor(door);
                GameManager.GetInstance().EndTurn();
            });
    }
    @Override
    public void Draw(JComponent target) {

        boolean isEnabled = true;

        if (door != null && actor != null) {

            isEnabled = door.Usable(actor);

            String otherRoom = door.GetRooms().stream()
                    .filter(room -> room != actor.GetLocation())
                    .findFirst()
                    .map(Room::GetName)
                    .orElse("");

            button.setText("<html><center>Door<br>%s</center></html>".formatted(otherRoom));
        } else {
            isEnabled = false;
        }

        button.setForeground(isEnabled ? Application.DarkText : Application.InvalidText);

        if (!isEnabled) {
            Arrays.stream(button.getMouseListeners()).forEach(button::removeMouseListener);
        }

        SetRelativeSizes(button, target, 0.3);
        target.add(button);
    }
}
