package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.function.Consumer;

public class ItemDrawable <ItemType extends Item> extends Drawable{
	private final JButton button = new JButton();
	private final ItemType item;
	private final String itemName;

	public ItemDrawable(ItemType item, String name, Optional<Consumer<ItemType>> onBtnLftClick, Optional<Consumer<ItemType>> onBtnRgtClick) {
		this.item = item;
		itemName = name;
		SetDefaults(button, Application.DarkText, Application.Background);
		button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
		if (item != null) {
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						onBtnLftClick.ifPresent(consumer -> consumer.accept(item));
					} else if (SwingUtilities.isRightMouseButton(e)) {
						onBtnRgtClick.ifPresent(consumer -> consumer.accept(item));
					}
				}
			});

		}
	}

	@Override
	public void Draw(JComponent target) {
		if (item != null) {
			button.setText(itemName);
			if (item.IsDead() || item.IsSticky() || item.IsActivated())
				button.setFont(button.getFont().deriveFont(Font.ITALIC));
			button.setBackground(getColorFromHashCode(item.hashCode()));
		}
		SetRelativeSizes(button, target, 0.3);
		target.add(button);
	}
}
