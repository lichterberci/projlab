package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Actor;
import lab.proj.model.Door;

import javax.swing.*;
import java.awt.*;

public class DoorDrawable implements Drawable {
    private final JButton button;
    private final Door door;
    private final Actor actor;

    public DoorDrawable(Door door, Actor actor) {
        this.door = door;
        this.actor = actor;
        button = new JButton("Door");
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setForeground(Application.DarkText);
        button.setBackground(Application.Light);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        if (door != null && actor != null)
            button.addActionListener(actionEvent -> actor.UseDoor(door));
    }
    @Override
    public void Draw(JComponent target) {
        button.setEnabled(door != null && actor != null && door.Usable(actor));
        button.setPreferredSize(new Dimension(
                (int) (target.getMinimumSize().getWidth() * 0.9f),
                (int) (target.getMinimumSize().getHeight() * 0.8f)));
        button.setFont(button.getFont().deriveFont((float) (target.getMinimumSize().getHeight() * 0.3f)));
        target.add(button);
    }
}
