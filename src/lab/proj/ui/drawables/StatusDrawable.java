package lab.proj.ui.drawables;

import lab.proj.controller.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class StatusDrawable extends Drawable {

    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel();

    public StatusDrawable(String spriteText) {
        SetDefaults(panel, Application.DarkText, Application.Dark);
        SetDefaults(label, Application.DarkText);
        label.setText(spriteText);
        panel.add(label);
    }

    @Override
    public void Draw(JComponent target) {
        SetRelativeSizes(panel, target, 1.0f);
        SetRelativeSizes(label, target, 1.0f);
        target.add(panel);
    }
}
