package lab.proj.ui.screens;

import lab.proj.controller.GameManager;
import lab.proj.model.Actor;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.controller.Application;
import lab.proj.ui.components.*;
import lab.proj.ui.components.Component;
import lab.proj.ui.drawables.DoorDrawable;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.ActorTurnIndicatorDrawable;
import lab.proj.ui.drawables.InventoryItemDrawable;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class GameScreen implements Screen {
	private final JButton endTurnButton;
	private final ActorTurnIndicatorComponent actorComponent;
	private final DoorsComponent doorsComponent;
	private final InventoryComponent inventoryComponent;
	private final ActorRoomDisplayComponent actorRoomDisplayComponent;
	private final ItemRoomDisplayComponent itemRoomDisplayComponent;
	private final RoomEffectComponent roomEffectComponent;
	private final ChargeComponent chargeComponent;

	public GameScreen() {
		endTurnButton = new JButton("End Turn");
		endTurnButton.addActionListener(actionEvent -> GameManager.GetInstance().EndTurn());
		endTurnButton.setBackground(Application.Dark);
		endTurnButton.setForeground(Application.LightText);
		actorComponent = new ActorTurnIndicatorComponent();
		doorsComponent = new DoorsComponent();
		inventoryComponent = new InventoryComponent();
		actorRoomDisplayComponent = new ActorRoomDisplayComponent();
		itemRoomDisplayComponent = new ItemRoomDisplayComponent();
		roomEffectComponent = new RoomEffectComponent();
		chargeComponent = new ChargeComponent();
	}

	public void SetActorIndicators(List<Drawable> actorIndicators) {
		actorComponent.SetDrawables(actorIndicators);
	}

	public void SetDoors(List<Drawable> doors) {
		doorsComponent.SetDrawables(doors);
	}

	public void SetInventoryItems(List<Drawable> items) {
		inventoryComponent.SetDrawables(items);
	}

	public void SetItemsInRoom(List<Drawable> items) {
		itemRoomDisplayComponent.SetDrawables(items);
	}

	public void SetActorsInRoom(List<Drawable> actors) {
		actorRoomDisplayComponent.SetDrawables(actors);
	}

	public void SetRoomEffects(List<Drawable> roomEffects) {
		roomEffectComponent.SetDrawables(roomEffects);
	}

	public void SetCharges(List<Drawable> charges) {
		chargeComponent.SetDrawables(charges);
	}

	@Override
	public void Render() {
		JComponent canvas = Application.GetInstance().GetCanvas();
		canvas.removeAll();

		RenderEndTurn(canvas);
		RenderComponent(actorComponent, canvas, 0.85, 0.0, 0.15, 1.0);
		RenderComponent(doorsComponent, canvas, 0.15, 0.75, 0.55, 0.15);
		RenderComponent(inventoryComponent, canvas, 0.3, 0.91, 0.25, 0.05);
		RenderComponent(actorRoomDisplayComponent, canvas, 0.15, 0.1, 0.2, 0.65);
		RenderComponent(itemRoomDisplayComponent, canvas, 0.5, 0.1, 0.2, 0.65);
		RenderComponent(roomEffectComponent, canvas, 0, 0.9, 0.2, 0.1);
		RenderComponent(chargeComponent, canvas, 0.65, 0.9, 0.2, 0.1);

		canvas.revalidate();
		canvas.repaint();
	}

	private void RenderEndTurn(JComponent canvas) {
		SizeJComponent(endTurnButton, canvas, 0.35, 0.02, 0.15, 0.05);
		endTurnButton.setFont(endTurnButton.getFont().deriveFont(endTurnButton.getHeight() * 0.6f));
		canvas.add(endTurnButton);
	}
}
