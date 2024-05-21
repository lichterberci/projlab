package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Represents the item drawable
 */
public class ItemDrawable extends Drawable{

	/**
	 * The button
	 */
	private final JButton button = new JButton();

	/**
	 * The item
	 */
	private final Item item;

	/**
	 * The name
	 */
	private final String name;

	/**
	 * Creates a new item drawable
	 * @param item The item
	 * @param name The name
	 * @param ml The mouse listener
	 */
	public ItemDrawable(Item item, String name, MouseListener ml) {
		this.item = item;
		this.name = name;
		SetDefaults(button, Application.DarkText, Application.Background);
		button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
		if (ml != null)
			button.addMouseListener(ml);
	}

	/**
	 * Creates a new item drawable
	 */
	@Override
	public void Draw(JComponent target) {
		boolean isEnabled = false;
		if (item != null) {
			button.setText(name);
			if (item.IsDead() || item.IsSticky() || item.IsActivated())
				button.setFont(button.getFont().deriveFont(Font.ITALIC));

			if (!(item.IsDead() || item.IsSticky()))
				isEnabled = true;
			button.setBackground(getColorFromHashCode(item.hashCode()));
		}
		if (!isEnabled)
			Arrays.stream(button.getMouseListeners()).forEach(button::removeMouseListener);

		SetRelativeSizes(button, target, 0.3);
		target.add(button);
	}
}
