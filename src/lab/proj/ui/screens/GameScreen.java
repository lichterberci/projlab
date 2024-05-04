package lab.proj.ui.screens;

import lab.proj.model.Actor;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.ui.Application;
import lab.proj.ui.components.ActorTurnIndicatorComponent;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.ActorTurnIndicatorDrawable;

import javax.swing.*;
import java.util.List;

public class GameScreen {
	private final JComponent canvas;
	private final ActorTurnIndicatorComponent actorComponent;

	private static GameScreen instance;
	public static GameScreen GetInstance() {
		if (instance == null)
			instance = new GameScreen();
		return instance;
	}

	private GameScreen() {
		this.canvas = Application.GetInstance().GetCanvas();
		this.actorComponent = new ActorTurnIndicatorComponent();
	}

	public void UpdateUI(List<Actor> allActorsInOrder /* for the turn indicator */,
	                     Room currentRoom,
	                     Student activeStudent) {

		canvas.removeAll();

		// Update the turn indicator
		List<Drawable> turnIndicatorDrawables = allActorsInOrder.stream()
				.map(actor -> (Drawable)new ActorTurnIndicatorDrawable(actor))
				.toList();

		actorComponent.SetActors(turnIndicatorDrawables);

		actorComponent.Draw(canvas);
	}
}
