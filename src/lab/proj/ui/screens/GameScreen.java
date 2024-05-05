package lab.proj.ui.screens;

import lab.proj.controller.GameManager;
import lab.proj.model.Actor;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.controller.Application;
import lab.proj.ui.components.ActorTurnIndicatorComponent;
import lab.proj.ui.components.DoorsComponent;
import lab.proj.ui.components.InventoryComponent;
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

	public GameScreen() {
		endTurnButton = new JButton("End Turn");
		endTurnButton.addActionListener(actionEvent -> GameManager.GetInstance().EndTurn());
		endTurnButton.setBackground(Application.Dark);
		endTurnButton.setForeground(Application.LightText);
		actorComponent = new ActorTurnIndicatorComponent();
		doorsComponent = new DoorsComponent();
		inventoryComponent = new InventoryComponent();
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

	@Override
	public void Render() {
		JComponent canvas = Application.GetInstance().GetCanvas();
		canvas.removeAll();

		RenderEndTurn(canvas);
		RenderActorIndicators(canvas);
		RenderDoors(canvas);
		RenderInventory(canvas);

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
}
