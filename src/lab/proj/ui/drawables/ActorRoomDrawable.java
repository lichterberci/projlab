package lab.proj.ui.drawables;

import lab.proj.model.Actor;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class ActorRoomDrawable implements Drawable {

	private final Actor actor;

	public ActorRoomDrawable(Actor actor) {
		this.actor = actor;
	}

	@Override
	public void Draw(JComponent drawTarget) {
		int width = (int) (0.2f * drawTarget.getWidth());
		int height = (int) (0.1f * drawTarget.getHeight());

		JPanel actorPanel = new JPanel();
		actorPanel.setMinimumSize(new Dimension((int) (width * 0.9), (int) (height * 0.15)));
		actorPanel.setBackground(Color.getHSBColor(getHueColorFromHashCode(actor.hashCode()), 0.8f, 0.9f));

		JLabel label = new JLabel(actor.GetName());
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setMinimumSize(new Dimension(30, 20));

		actorPanel.add(label);

		drawTarget.add(actorPanel);
	}
}
