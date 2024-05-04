package lab.proj.ui.screens;

import lab.proj.model.Actor;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.controller.Application;
import lab.proj.ui.components.ActorTurnIndicatorComponent;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.ActorTurnIndicatorDrawable;

import javax.swing.*;
import java.util.List;

public class GameScreen {
	private final ActorTurnIndicatorComponent actorComponent;
	private List<ActorTurnIndicatorDrawable> actorIndicators;

	public GameScreen() {
		this.actorComponent = new ActorTurnIndicatorComponent();
	}

	public void Render() {
		JComponent canvas = Application.GetInstance().GetCanvas();
		canvas.removeAll();

		actorComponent.SetActors(actorIndicators);
		actorComponent.Draw(canvas);

		canvas.revalidate();
		canvas.repaint();
	}

	public void SetActorIndicators(List<ActorTurnIndicatorDrawable> actorIndicators) {
		this.actorIndicators = actorIndicators;
	}
}
