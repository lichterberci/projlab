package lab.proj.ui.drawables;

import lab.proj.model.Item;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class ItemRoomDrawable implements Drawable{

	private final Item item;

	public ItemRoomDrawable(Item item) {
		this.item = item;
	}

	@Override
	public void Draw(JComponent drawTarget) {

		JPanel itemPanel = new JPanel();
		itemPanel.setMinimumSize(new Dimension((int) (drawTarget.getWidth() * 0.4), (int) (drawTarget.getHeight() * 0.1)));
		itemPanel.setBackground(Color.getHSBColor(getHueColorFromHashCode(item.hashCode()), 0.8f, 0.9f));

		JLabel label = new JLabel();
		label.setText(item.getClass().getName());
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setMinimumSize(new Dimension(30, 20));

		itemPanel.add(label);
		drawTarget.add(itemPanel);
	}
}
