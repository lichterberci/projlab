package lab.proj.ui.drawables;

import lab.proj.model.Actor;
import lab.proj.controller.Application;

import javax.swing.*;
import java.awt.*;

public class ActorTurnIndicatorDrawable implements Drawable {
	private final JLabel label;
	private final Actor actor;

	public ActorTurnIndicatorDrawable(Actor actor) {
		this.actor = actor;
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setForeground(Application.DarkText);
		label.setBackground(Application.Light);
//		label.setBackground(Color.getHSBColor(getHueColorFromHashCode(actor.hashCode()), 0.8f, 0.9f));
		label.setOpaque(true);
		label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
	}

	public void Selected() {
		label.setBorder(BorderFactory.createDashedBorder(Application.Border, 2f, 12f, 6f, false));
	}

	@Override
	public void Draw(JComponent target) {
		String effectText = "";
		if (actor.IsBlocked()) {
			effectText = "shocked";
			label.setForeground(Application.InvalidText);
		}
		label.setText("<html><center>%s%s</center></html>".formatted(actor.GetName(), effectText.isEmpty() ? "" : "<br>(%s)".formatted(effectText)));
		label.setPreferredSize(new Dimension(
				(int) (target.getMinimumSize().getWidth() * 0.9f),
				(int) (target.getMinimumSize().getHeight() * 0.8f)));
		label.setFont(label.getFont().deriveFont((float) (target.getMinimumSize().getHeight() * 0.2f)));
		target.add(label);
	}
}
