package lab.proj.ui.drawables;

import lab.proj.controller.Application;

import javax.swing.*;

/**
 * Represents the status drawable
 */
public class StatusDrawable extends Drawable {

    /**
     * The panel
     */
    private final JPanel panel = new JPanel();
    /**
     * The label
     */
    private final JLabel label = new JLabel();

    /**
     * Creates a new status drawable
     *
     * @param spriteText The sprite text
     */
    public StatusDrawable(String spriteText) {
        SetDefaults(panel, Application.DarkText, Application.Dark);
        SetDefaults(label, Application.DarkText);
        label.setText(spriteText);
        panel.add(label);
    }

    /**
     * Creates a new status drawable
     */
    @Override
    public void Draw(JComponent target) {
        SetRelativeSizes(panel, target, 1.0f);
        SetRelativeSizes(label, target, 1.0f);
        target.add(panel);
    }
}
