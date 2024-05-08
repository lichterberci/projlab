package lab.proj.ui.drawables;

import lab.proj.model.Actor;
import lab.proj.controller.Application;

import javax.swing.*;

public class TurnIndicatorDrawable extends Drawable {
	private final JLabel label = new JLabel();
	private final Actor actor;

	public TurnIndicatorDrawable(Actor actor, boolean selected) {
		this.actor = actor;
		SetDefaults(label, Application.DarkText, Application.Light);
		if (selected)
			label.setBorder(BorderFactory.createDashedBorder(Application.Border, 2f, 12f, 6f, false));
		else
			label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
	}

	@Override
	public void Draw(JComponent target) {
		if (actor != null) {
			String effectText = "";
			if (actor.IsBlocked())
			{
				effectText = "shocked";
				label.setForeground(Application.InvalidText);
			}
			label.setText("<html><center>%s<br>%s%s</center></html>".formatted(actor.GetName(),
					actor.GetLocation().GetName(), effectText.isEmpty() ? "" : " (%s)".formatted(effectText)));
		}
		SetRelativeSizes(label, target,0.2);
		target.add(label);
	}
}
