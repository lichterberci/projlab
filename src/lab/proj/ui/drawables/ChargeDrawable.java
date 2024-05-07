package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Charge;
import lab.proj.model.RoomEffect;

import javax.swing.*;
import java.awt.*;

public class ChargeDrawable extends Drawable {

    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel();
    private final Charge charge;

    public ChargeDrawable(Charge charge) {
        this.charge = charge;
        SetDefaults(panel, Application.DarkText, Application.Dark);
        SetDefaults(label, Application.DarkText);
        panel.add(label);
    }

    @Override
    public void Draw(JComponent target) {
        if (charge != null) {
            label.setText(charge.getClass().getSimpleName());
        }
        SetRelativeSizes(panel, target, 0.15);
        SetRelativeSizes(label, target, 0.15);
        target.add(panel);
    }
}
