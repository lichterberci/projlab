package lab.proj.ui.screens;

import lab.proj.controller.GameManager;
import lab.proj.model.Actor;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.controller.Application;
import lab.proj.ui.components.ActorTurnIndicatorComponent;
import lab.proj.ui.components.DoorsComponent;
import lab.proj.ui.drawables.DoorDrawable;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.ActorTurnIndicatorDrawable;

import javax.swing.*;
import java.util.List;

public class GameScreen implements Screen {
	private final ActorTurnIndicatorComponent actorComponent;
	private final DoorsComponent doorsComponent;

	public GameScreen() {
		actorComponent = new ActorTurnIndicatorComponent();
		doorsComponent = new DoorsComponent();
	}

	public void SetActorIndicators(List<ActorTurnIndicatorDrawable> actorIndicators) {
		actorComponent.SetActors(actorIndicators);
	}

	public void SetDoors(List<DoorDrawable> doors) {
		doorsComponent.SetDoors(doors);
	}

	@Override
	public void Render() {
		JComponent canvas = Application.GetInstance().GetCanvas();
		canvas.removeAll();

		RenderEndTurn(canvas);
		RenderActorIndicators(canvas);
		RenderDoors(canvas);

		canvas.revalidate();
		canvas.repaint();
	}

	private void RenderEndTurn(JComponent canvas) {
		JButton endTurnButton = new JButton("End Turn");
		endTurnButton.setBounds((int) (0.35f * canvas.getWidth()),
				(int) (0.02f * canvas.getHeight()),
				(int) (0.15f * canvas.getWidth()),
				(int) (0.05f * canvas.getHeight()));
		endTurnButton.addActionListener(actionEvent -> GameManager.GetInstance().EndTurn());
		endTurnButton.setBackground(Application.Dark);
		endTurnButton.setForeground(Application.LightText);
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
}
