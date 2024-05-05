package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Actor;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class ActorRoomDrawable implements Drawable {
	private final JPanel panel;
	private final JLabel label;
 	private final Actor actor;

	public ActorRoomDrawable(Actor actor) {
		this.actor = actor;
		panel = new JPanel();
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setForeground(Application.DarkText);
		panel.add(label);
	}

	@Override
	public void Draw(JComponent target) {
		panel.setBackground(Color.getHSBColor(getHueColorFromHashCode(actor.hashCode()), 0.8f, 0.9f));
		panel.setPreferredSize(new Dimension(
				(int) (target.getMinimumSize().getWidth() * 0.9f),
				(int) (target.getMinimumSize().getHeight() * 0.9f)));
		label.setText(actor.GetName());
		label.setFont(label.getFont().deriveFont((float) (target.getMinimumSize().getHeight() * 0.3f)));
		target.add(panel);
	}
}
