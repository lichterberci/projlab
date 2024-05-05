package lab.proj.ui.components;

import lab.proj.controller.Application;
import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;

public class ActorTurnIndicatorComponent extends Component {
	@Override
	public void Draw(JComponent target) {
		target.setBackground(Application.Dark);
		target.setLayout(new BoxLayout(target, BoxLayout.Y_AXIS));

		for (Drawable actor : drawables) {
			Dimension size = new Dimension(
					(int) (target.getWidth() * 0.9),
					(int) (target.getHeight() * 0.1));
			JPanel actorPanel = new JPanel();
			actorPanel.setMinimumSize(size);
			actorPanel.setMinimumSize(size);
			actorPanel.setOpaque(false);
			actor.Draw(actorPanel);

			target.add(actorPanel);
		}
	}
}
