package lab.proj.ui.screens;

import lab.proj.controller.GameManager;
import lab.proj.model.Actor;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.controller.Application;
import lab.proj.ui.components.*;
import lab.proj.ui.drawables.DoorDrawable;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.ActorTurnIndicatorDrawable;
import lab.proj.ui.drawables.InventoryItemDrawable;

import javax.swing.*;
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
		RenderActorIndicators(canvas);
		RenderDoors(canvas);
		RenderInventory(canvas);
		RenderActorsInRoom(canvas);
		RenderItemsOnTheFloor(canvas);
		RenderRoomEffects(canvas);
		RenderCharges(canvas);

		canvas.revalidate();
		canvas.repaint();
	}

	private void RenderEndTurn(JComponent canvas) {
		endTurnButton.setBounds((int) (0.35f * canvas.getWidth()),
				(int) (0.02f * canvas.getHeight()),
				(int) (0.15f * canvas.getWidth()),
				(int) (0.05f * canvas.getHeight()));
		endTurnButton.setFont(endTurnButton.getFont().deriveFont(endTurnButton.getHeight() * 0.6f));
		canvas.add(endTurnButton);
	}

	private void RenderActorIndicators(JComponent canvas) {
		JPanel actorIndicatorsPanel = new JPanel();
		actorIndicatorsPanel.setBounds((int) (0.85f * canvas.getWidth()),
				(int) (0.0f * canvas.getHeight()),
				(int) (0.15f * canvas.getWidth()),
				(int) (1f * canvas.getHeight()));
		actorComponent.Draw(actorIndicatorsPanel);

		JScrollPane indicatorsScrollPane = new JScrollPane(actorIndicatorsPanel);
		indicatorsScrollPane.setBounds((int) (0.85f * canvas.getWidth()),
				(int) (0.0f * canvas.getHeight()),
				(int) (0.15f * canvas.getWidth()),
				(int) (1f * canvas.getHeight()));
		indicatorsScrollPane.setBorder(BorderFactory.createLineBorder(Application.Border));
		indicatorsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		indicatorsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		indicatorsScrollPane.setOpaque(false);
		canvas.add(indicatorsScrollPane);
	}

	private void RenderDoors(JComponent canvas) {
		JPanel doorsPanel = new JPanel();
		doorsPanel.setBounds((int) (0.15f * canvas.getWidth()),
				(int) (0.8f * canvas.getHeight()),
				(int) (0.55f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()));
		doorsComponent.Draw(doorsPanel);

		JScrollPane doorsScrollPane = new JScrollPane(doorsPanel);
		doorsScrollPane.setBounds((int) (0.15f * canvas.getWidth()),
				(int) (0.8f * canvas.getHeight()),
				(int) (0.55f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()));
		doorsScrollPane.setBorder(BorderFactory.createLineBorder(Application.Border));
		doorsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		doorsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		doorsScrollPane.setOpaque(false);
		canvas.add(doorsScrollPane);
	}

	private void RenderInventory(JComponent canvas) {
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBounds((int) (0.30f * canvas.getWidth()),
				(int) (0.91f * canvas.getHeight()),
				(int) (0.25f * canvas.getWidth()),
				(int) (0.05f * canvas.getHeight()));
		inventoryPanel.setOpaque(false);
		inventoryComponent.Draw(inventoryPanel);
		canvas.add(inventoryPanel);
	}

	private void RenderActorsInRoom(JComponent canvas) {
		JPanel actorsPanel = new JPanel();
		actorsPanel.setBounds((int) (0.15f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()),
				(int) (0.2f * canvas.getWidth()),
				(int) (0.7f * canvas.getHeight()));
		actorRoomDisplayComponent.Draw(actorsPanel);

		JScrollPane actorsScrollPane = new JScrollPane(actorsPanel);
		actorsScrollPane.setBounds((int) (0.15f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()),
				(int) (0.2f * canvas.getWidth()),
				(int) (0.7f * canvas.getHeight()));
		actorsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		actorsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		actorsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		actorsScrollPane.setOpaque(false);
		canvas.add(actorsScrollPane);
	}

	private void RenderItemsOnTheFloor(JComponent canvas) {
		JPanel itemsPanel = new JPanel();
		itemsPanel.setBounds((int) (0.5f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()),
				(int) (0.2f * canvas.getWidth()),
				(int) (0.7f * canvas.getHeight()));
		itemRoomDisplayComponent.Draw(itemsPanel);

		JScrollPane itemsScrollPane = new JScrollPane(itemsPanel);
		itemsScrollPane.setBounds((int) (0.5f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()),
				(int) (0.2f * canvas.getWidth()),
				(int) (0.7f * canvas.getHeight()));
		itemsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		itemsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		itemsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		itemsScrollPane.setOpaque(false);
		canvas.add(itemsScrollPane);
	}

	private void RenderRoomEffects(JComponent canvas) {
		JPanel roomEffectsPanel = new JPanel();
		roomEffectsPanel.setBounds(0,
				(int) (0.9f * canvas.getHeight()),
				(int) (0.2f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()));
		roomEffectComponent.Draw(roomEffectsPanel);

		JScrollPane roomEffectsScrollPane = new JScrollPane(roomEffectsPanel);
		roomEffectsScrollPane.setBounds(0,
				(int) (0.9f * canvas.getHeight()),
				(int) (0.2f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()));
		roomEffectsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		roomEffectsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roomEffectsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		roomEffectsScrollPane.setOpaque(false);
		canvas.add(roomEffectsScrollPane);
	}

	private void RenderCharges(JComponent canvas) {
		JPanel chargesPanel = new JPanel();
		chargesPanel.setBounds(0,
				(int) (0.9f * canvas.getHeight()),
				(int) (0.2f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()));
		chargeComponent.Draw(chargesPanel);

		JScrollPane chargesScrollPane = new JScrollPane(chargesPanel);
		chargesScrollPane.setBounds(0,
				(int) (0.9f * canvas.getHeight()),
				(int) (0.2f * canvas.getWidth()),
				(int) (0.1f * canvas.getHeight()));
		chargesScrollPane.setBorder(BorderFactory.createEmptyBorder());
		chargesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		chargesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		chargesScrollPane.setOpaque(false);
		canvas.add(chargesScrollPane);
	}
}
