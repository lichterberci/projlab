package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Actor;
import lab.proj.model.Item;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class ItemRoomDrawable implements Drawable{
	private final JButton button;
	private final Item item;

	public ItemRoomDrawable(Item item, Actor actor) {
		this.item = item;
		button = new JButton();
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setForeground(Application.DarkText);
		button.setOpaque(true);
		button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
		if (item != null && actor != null)
			button.addActionListener(actionEvent -> item.PickUp(actor));
	}

	@Override
	public void Draw(JComponent target) {
		button.setText(item.getClass().getName());
		button.setPreferredSize(new Dimension(
				(int) (target.getMinimumSize().getWidth() * 0.9f),
				(int) (target.getMinimumSize().getHeight() * 0.9f)));
		button.setBackground(Color.getHSBColor(getHueColorFromHashCode(item.hashCode()), 0.8f, 0.9f));
		button.setFont(button.getFont().deriveFont((float) (target.getMinimumSize().getHeight() * 0.3f)));
		target.add(button);
	}
}
