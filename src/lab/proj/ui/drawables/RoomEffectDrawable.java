package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Actor;
import lab.proj.model.Room;
import lab.proj.model.RoomEffect;

import javax.swing.*;
import java.awt.*;

public class RoomEffectDrawable implements Drawable {

    private final JPanel panel;
    private final JLabel label;
    private final RoomEffect effect;

    public RoomEffectDrawable(RoomEffect effect) {
        this.effect = effect;
        panel = new JPanel();
        panel.setBackground(Application.Dark);
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setForeground(Application.DarkText);
        panel.add(label);
    }

    @Override
    public void Draw(JComponent target) {
        panel.setPreferredSize(new Dimension(
                (int) (target.getMinimumSize().getWidth() * 0.9f),
                (int) (target.getMinimumSize().getHeight() * 0.9f)));
        label.setText(effect.getClass().getName());
        label.setFont(label.getFont().deriveFont((float) (target.getMinimumSize().getHeight() * 0.3f)));
        target.add(panel);
    }
}
