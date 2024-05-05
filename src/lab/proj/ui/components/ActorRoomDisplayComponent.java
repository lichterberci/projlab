package lab.proj.ui.components;

import lab.proj.controller.Application;
import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;

public class ActorRoomDisplayComponent extends Component {
	@Override
	public void Draw(JComponent target) {
		target.setLayout(new BoxLayout(target, BoxLayout.X_AXIS));

		for (Drawable actor : drawables) {
			Dimension size = new Dimension(
					(int) (target.getWidth() * 0.25),
					(int) (target.getHeight() * 0.9));
			JPanel actorPanel = new JPanel();
			actorPanel.setMinimumSize(size);
			actorPanel.setMaximumSize(size);
			actorPanel.setOpaque(false);
			actor.Draw(actorPanel);

			target.add(actorPanel);
		}
	}
}
