package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Actor;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class ActorRoomDrawable extends Drawable {
	private final JPanel panel = new JPanel();
	private final JLabel label = new JLabel();
 	private final Actor actor;

	public ActorRoomDrawable(Actor actor) {
		this.actor = actor;
		SetDefaults(label, Application.DarkText);
		panel.add(label);
	}

	@Override
	public void Draw(JComponent target) {
		if (actor != null) {
			label.setText(actor.GetName());
			panel.setBackground(getColorFromHashCode(actor.hashCode()));
		}
		SetRelativeSizes(panel, target, 0.3);
		SetRelativeSizes(label, target, 0.3);
		target.add(panel);
	}
}
