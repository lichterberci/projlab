package lab.proj.ui.components;

import javax.swing.*;

public class ActorRoomDisplayComponent extends Component {
	@Override
	public void Draw(JComponent target) {
		target.setLayout(new BoxLayout(target, BoxLayout.Y_AXIS));
		target.setOpaque(false);

		AddDrawables(1, 0.2, target);
	}
}
