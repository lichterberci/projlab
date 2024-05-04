package lab.proj.ui.drawables;

import lab.proj.model.Actor;

import javax.swing.*;
import java.awt.*;

public class ActorTurnIndicatorDrawable implements Drawable {
	private final Actor actor;

	public ActorTurnIndicatorDrawable(Actor actor) {
		this.actor = actor;
	}

	@Override
	public void Draw(JComponent drawTarget) {

		String effectText = "";

		if (actor.IsBlocked()) effectText += "blocked";

		JLabel label = new JLabel("%s%s".formatted(actor.GetName(), effectText.isEmpty() ? "" : " (%s)".formatted(effectText)));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setMinimumSize(new Dimension(30, 20));

		JPanel actorPanel = new JPanel();
		actorPanel.add(label);
//		actorPanel.setBackground(Color.decode("#d0d0d0"));
		actorPanel.setBackground(Color.getHSBColor(getHueColorFromHashCode(actor.hashCode()), 0.8f, 0.9f));
		actorPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		actorPanel.setAlignmentX(0.5f);
		actorPanel.setAlignmentY(0.5f);
		actorPanel.setPreferredSize(new Dimension((int) (drawTarget.getMinimumSize().getWidth() * 0.9f), (int) (drawTarget.getMinimumSize().getHeight() * 0.8f)));

		drawTarget.add(actorPanel);
	}
}
