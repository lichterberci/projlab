package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.RoomEffect;

import javax.swing.*;

public class EffectDrawable extends Drawable {

    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel();
    private final RoomEffect effect;

    public EffectDrawable(RoomEffect effect) {
        this.effect = effect;
        SetDefaults(panel, Application.DarkText, Application.Dark);
        SetDefaults(label, Application.DarkText);
        panel.add(label);
    }

    @Override
    public void Draw(JComponent target) {
        if (effect != null) {
            label.setText(effect.getClass().getSimpleName());
        }
        SetRelativeSizes(panel, target, 0.15);
        SetRelativeSizes(label, target, 0.15);
        target.add(panel);
    }
}
