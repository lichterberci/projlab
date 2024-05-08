package lab.proj.ui.components;

import javax.swing.*;

public class ItemsComponent extends Component {
	@Override
	public void Draw(JComponent target) {
		JPanel panel = MakePanelInScroll(target, BorderFactory.createEmptyBorder(),
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);

		AddDrawables(1.0, 0.2, panel);
	}
}
