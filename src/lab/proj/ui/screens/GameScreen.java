package lab.proj.ui.screens;

import lab.proj.controller.GameManager;
import lab.proj.controller.Application;
import lab.proj.ui.components.*;
import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.util.EnumMap;
import java.util.List;

public class GameScreen implements Screen {
	public enum ComponentNames {
		TurnIndicator,
		Doors,
		Inventory,
		Actors,
		Items,
		Effects,
		Charges
	}
	private final JButton endTurnButton;
	private final EnumMap<ComponentNames, Component> components = new EnumMap<>(ComponentNames.class);

	public GameScreen() {
		endTurnButton = new JButton("End Turn");
		endTurnButton.addActionListener(actionEvent -> {
			GameManager.GetInstance().EndTurn();
		});
		endTurnButton.setBackground(Application.Dark);
		endTurnButton.setForeground(Application.LightText);

		components.put(ComponentNames.TurnIndicator, new TurnIndicatorComponent());
		components.put(ComponentNames.Doors, new DoorsComponent());
		components.put(ComponentNames.Inventory, new InventoryComponent());
		components.put(ComponentNames.Actors, new ActorsComponent());
		components.put(ComponentNames.Items, new ItemsComponent());
		components.put(ComponentNames.Effects, new EffectsComponent());
		components.put(ComponentNames.Charges, new ChargesComponent());
	}

	public void SetDrawables(List<Drawable> drawables, ComponentNames component) {
		components.get(component).SetDrawables(drawables);
	}

	@Override
	public void Render() {
		RenderEndTurn();
		RenderComponent(components.get(ComponentNames.TurnIndicator), 0.85, 0.0, 0.15, 1.0);
		RenderComponent(components.get(ComponentNames.Doors), 0.15, 0.75, 0.55, 0.15);
		RenderComponent(components.get(ComponentNames.Inventory), 0.3, 0.91, 0.25, 0.05);
		RenderComponent(components.get(ComponentNames.Actors), 0.15, 0.1, 0.2, 0.65);
		RenderComponent(components.get(ComponentNames.Items), 0.5, 0.1, 0.2, 0.65);
		RenderComponent(components.get(ComponentNames.Effects), 0, 0.9, 0.2, 0.1);
		RenderComponent(components.get(ComponentNames.Charges), 0.65, 0.9, 0.2, 0.1);
	}

	private void RenderEndTurn() {
		SizeJComponent(endTurnButton, 0.35, 0.02, 0.15, 0.05);
		endTurnButton.setFont(endTurnButton.getFont().deriveFont(endTurnButton.getHeight() * 0.6f));
		Application.GetInstance().GetCanvas().add(endTurnButton);
	}
}
