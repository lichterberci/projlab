package lab.proj.ui.drawables;

import lab.proj.model.Actor;
import lab.proj.ui.Application;

import javax.swing.*;
import java.awt.*;

public class ActorTurnIndicatorDrawable implements Drawable {
	private final Actor actor;

	public ActorTurnIndicatorDrawable(Actor actor) {
		this.actor = actor;
	}

	@Override
	public void Draw(JComponent target) {
		String effectText = "";
		if (actor.IsBlocked()) effectText += "blocked";

		JLabel label = new JLabel("%s%s".formatted(actor.GetName(), effectText.isEmpty() ? "" : " (%s)".formatted(effectText)));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setOpaque(true);
		label.setForeground(Application.DarkText);
//		label.setBackground(Application.Light);
		label.setBackground(Color.getHSBColor(getHueColorFromHashCode(actor.hashCode()), 0.8f, 0.9f));
		label.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Application.Border));
		label.setPreferredSize(new Dimension((int) (target.getMinimumSize().getWidth() * 0.9f), (int) (target.getMinimumSize().getHeight() * 0.8f)));

		target.add(label);
	}
}
