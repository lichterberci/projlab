package lab.proj.ui.components;

import lab.proj.controller.Application;
import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;

public class ItemRoomDisplayComponent extends Component {
	@Override
	public void Draw(JComponent target) {
		target.setLayout(new BoxLayout(target, BoxLayout.Y_AXIS));
		target.setOpaque(false);

		for (Drawable item : drawables) {
			Dimension size = new Dimension(
					(int) (target.getWidth() * 0.8),
					(int) (target.getHeight() * 0.2));
			JPanel itemPanel = new JPanel();
			itemPanel.setMinimumSize(size);
			itemPanel.setMaximumSize(size);
			itemPanel.setOpaque(false);
			item.Draw(itemPanel);

			target.add(itemPanel);
		}
	}
}
