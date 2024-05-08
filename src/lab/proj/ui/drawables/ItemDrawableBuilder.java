package lab.proj.ui.drawables;

import lab.proj.model.Item;

import java.util.Optional;
import java.util.function.Consumer;

public class ItemDrawableBuilder<ItemType extends Item> {
	private ItemType item = null;
	private String name = "";
	private Optional<Consumer<ItemType>> onBtnLftClick = Optional.empty();
	private Optional<Consumer<ItemType>> onBtnRgtClick = Optional.empty();

	public ItemDrawableBuilder setItem(ItemType item) {
		this.item = item;
		return this;
	}

	public ItemDrawableBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public ItemDrawableBuilder setOnBtnLftClick(Consumer<ItemType> onBtnLftClick) {
		this.onBtnLftClick = Optional.of(onBtnLftClick);
		return this;
	}

	public ItemDrawableBuilder setOnBtnRgtClick(Consumer<ItemType> onBtnRgtClick) {
		this.onBtnRgtClick = Optional.of(onBtnRgtClick);
		return this;
	}

	public ItemDrawable build() {
		return new ItemDrawable(item, name, onBtnLftClick, onBtnRgtClick);
	}
}