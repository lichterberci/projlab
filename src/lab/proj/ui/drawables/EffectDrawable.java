package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.RoomEffect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class EffectDrawable extends Drawable {

    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel();
    private final RoomEffect effect;

    public EffectDrawable(RoomEffect effect, String spriteResourcePath) {
        this.effect = effect;
        SetDefaults(panel, Application.DarkText, Application.Dark);
        SetDefaults(label, Application.DarkText);
        panel.add(label);
	    try {
		    label.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(spriteResourcePath))));
	    } catch (IOException e) {
		    label.setText("?");
	    }
    }

    @Override
    public void Draw(JComponent target) {
        SetRelativeSizes(panel, target, 0.15);
        SetRelativeSizes(label, target, 0.15);
        target.add(panel);
    }
}
