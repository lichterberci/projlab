package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Actor;
import lab.proj.model.Item;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class ItemRoomDrawable extends Drawable{
	private final JButton button = new JButton();
	private final Item item;

	public ItemRoomDrawable(Item item, Actor actor) {
		this.item = item;
		SetDefaults(button, Application.DarkText, Application.Background);
		button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
		if (item != null && actor != null)
			button.addActionListener(actionEvent -> item.PickUp(actor));
	}

	@Override
	public void Draw(JComponent target) {
		if (item != null) {
			button.setText(item.getClass().getSimpleName());
			button.setBackground(getColorFromHashCode(item.hashCode()));
		}
		SetRelativeSizes(button, target, 0.3);
		target.add(button);
	}
}
