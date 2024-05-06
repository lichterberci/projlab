package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Actor;
import lab.proj.model.Door;

import javax.swing.*;
import java.awt.*;

public class DoorDrawable extends Drawable {
    private final JButton button = new JButton("Door");
    private final Door door;
    private final Actor actor;

    public DoorDrawable(Door door, Actor actor) {
        this.door = door;
        this.actor = actor;
        SetDefaults(button, Application.DarkText, Application.Light);
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        if (door != null && actor != null)
            button.addActionListener(actionEvent -> actor.UseDoor(door));
    }
    @Override
    public void Draw(JComponent target) {
        if (door != null && actor != null) {
            button.setEnabled(door.Usable(actor));
        } else {
            button.setEnabled(false);
        }
        SetRelativeSizes(button, target, 0.3);
        target.add(button);
    }
}
