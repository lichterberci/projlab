package lab.proj.ui;

import lab.proj.model.Actor;
import lab.proj.model.Room;
import lab.proj.model.Student;
import lab.proj.ui.drawables.ActorTurnIndicatorDrawable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameUI {
	private final JFrame frame;

	private final ActorTurnIndicatorComponent actorComponent;

	public GameUI() {
		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setLayout(null);
		frame.setVisible(true);

		actorComponent = new ActorTurnIndicatorComponent();
	}

	public void UpdateUI(List<Actor> allActorsInOrder /* for the turn indicator */,
	                     Room currentRoom,
	                     Student activeStudent) {

		frame.getContentPane().removeAll();

		// Update the turn indicator
		List<Drawable> turnIndicatorDrawables = allActorsInOrder.stream()
				.map(actor -> (Drawable)new ActorTurnIndicatorDrawable(actor))
				.toList();
		actorComponent.SetActors(turnIndicatorDrawables);

		actorComponent.Draw(frame);

		frame.revalidate();
		frame.repaint();
	}
}
