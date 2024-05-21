package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Actor;

import javax.swing.*;

/**
 * Represents the actor drawable
 */
public class ActorDrawable extends Drawable {
	/**
	 * The panel
	 */
	private final JPanel panel = new JPanel();
	/**
	 * The label
	 */
	private final JLabel label = new JLabel();
	/**
	 * The actor
	 */
 	private final Actor actor;

	/**
	 * Creates a new actor drawable
	 * @param actor The actor
	 */
	public ActorDrawable(Actor actor) {
		this.actor = actor;
		SetDefaults(label, Application.DarkText);
		panel.add(label);
	}

	/**
	 * Creates a new actor drawable
	 */
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
