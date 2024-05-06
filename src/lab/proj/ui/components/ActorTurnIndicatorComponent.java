package lab.proj.ui.components;

import lab.proj.controller.Application;
import javax.swing.*;

public class ActorTurnIndicatorComponent extends Component {
	@Override
	public void Draw(JComponent target) {
		target.setLayout(new BoxLayout(target, BoxLayout.Y_AXIS));
		target.setBackground(Application.Dark);

		AddDrawables(0.9, 0.1, target);
	}
}
