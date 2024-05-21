package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Actor;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the turn indicator drawable
 */
public class TurnIndicatorDrawable extends Drawable {

    /**
     * The label
     */
    private final JLabel label = new JLabel();

    /**
     * The actor
     */
    private final Actor actor;

    /**
     * Creates a new turn indicator drawable
     *
     * @param actor    The actor
     * @param selected The selected
     */
    public TurnIndicatorDrawable(Actor actor, boolean selected) {
        this.actor = actor;
        SetDefaults(label, Application.DarkText, Application.Light);
        if (selected) {
            label.setBorder(BorderFactory.createDashedBorder(Application.Border, 2f, 12f, 6f, false));
            label.setFont(label.getFont().deriveFont(Font.BOLD));
        } else
            label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
    }

    /**
     * Creates a new turn indicator drawable
     */
    @Override
    public void Draw(JComponent target) {
        if (actor != null) {
            String effectText = "";
            if (actor.IsBlocked()) {
                effectText = "shocked";
                label.setForeground(Application.InvalidText);
            }
            label.setText("<html><center>%s<br>%s%s</center></html>".formatted(actor.GetName(),
                    actor.GetLocation().GetName(), effectText.isEmpty() ? "" : " (%s)".formatted(effectText)));
        }
        SetRelativeSizes(label, target, 0.2);
        target.add(label);
    }
}
