package lab.proj.controller;

import lab.proj.model.Student;
import lab.proj.ui.drawables.*;
import lab.proj.ui.screens.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Application {
	public static final Color Dark = new Color(75, 75, 75);
	public static final Color Light = new Color(200, 200, 200);
	public static final Color Background = new Color(175, 175, 175);
	public static final Color DarkText = new Color(0,0,0);
	public static final Color LightText = new Color(255, 255, 255);
	public static final Color Border = new Color(0,0,0);

	private final JFrame frame;
	private final JPanel canvas;
	private final MenuScreen menu;
	private final GameScreen game;
	private final ResultScreen result;

	private static Application instance;

	public static Application GetInstance() {
		if (instance == null)
			instance = new Application();
		return instance;
	}

	private Application() {
		frame = new JFrame("THE SLIDE RULE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.getContentPane().setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setBackground(Background);

		canvas = new JPanel();
		canvas.setLayout(null);
		canvas.setBounds(0, 0, 800, 600);
		canvas.setOpaque(false);

		frame.add(canvas);
		frame.setVisible(true);
		menu = new MenuScreen();
		game = new GameScreen();
		result = new ResultScreen();
	}

	public void RenderMenuScreen() {
		frame.setTitle("THE SLIDE RULE - MENU");
		java.util.List<StudentNameDrawable> studentDrawables =
				GameManager.GetInstance().GetStudents()
				.stream()
				.map(StudentNameDrawable::new)
				.toList();
		menu.SetStudents(studentDrawables);
		menu.Render();
	}

	public void RenderGameScreen() {
		frame.setTitle("THE SLIDE RULE - GAME");
		List<ActorTurnIndicatorDrawable> actorIndicators =
				GameManager.GetInstance().ActorsInOrder().stream()
				.map(ActorTurnIndicatorDrawable::new)
				.toList();
		game.SetActorIndicators(actorIndicators);
		game.Render();
	}

	public void RenderResultScreen() {
		frame.setTitle("THE SLIDE RULE - END");
		result.SetResult(GameManager.GetInstance().isWon()? "Students win!" : "Teachers win!");
		result.Render();
	}

	public JComponent GetCanvas() {
		return canvas;
	}
}
