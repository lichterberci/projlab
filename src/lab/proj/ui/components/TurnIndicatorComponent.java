package lab.proj.ui.components;

import lab.proj.controller.Application;
import javax.swing.*;

public class TurnIndicatorComponent extends Component {
	@Override
	public void Draw(JComponent target) {
		JPanel panel = MakePanelInScroll(target, BorderFactory.createLineBorder(Application.Border),
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Application.Dark);

		AddDrawables(0.9, 0.1, panel);
	}
}
