package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Charge;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class ChargeDrawable extends Drawable {

    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel();

    public ChargeDrawable(String spriteResourcePath) {
        SetDefaults(panel, Application.DarkText, Application.Dark);
        SetDefaults(label, Application.DarkText);
        try {
            label.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(spriteResourcePath))));
        } catch (IOException e) {
            label.setText("?");
        }
        panel.add(label);
    }

    @Override
    public void Draw(JComponent target) {
        SetRelativeSizes(panel, target, 0.15);
        SetRelativeSizes(label, target, 0.15);
        target.add(panel);
    }
}
