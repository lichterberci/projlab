package lab.proj.ui;

import lab.proj.model.Student;
import lab.proj.ui.screens.GameScreen;
import lab.proj.ui.screens.MenuScreen;
import lab.proj.ui.screens.ResultScreen;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class Application {
	private final JFrame frame;
	private final JPanel canvas;

	private static Application instance;

	public static Application GetInstance() {
		if (instance == null)
			instance = new Application();
		return instance;
	}

	private Application() {
		frame = new JFrame("Best game ever created");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setPreferredSize(new Dimension(800, 600));

		canvas = new JPanel();
		canvas.setLayout(null);
		canvas.setLocation(0, 0);
		canvas.setPreferredSize(new Dimension(800, 600));

		frame.add(canvas);

//		frame.pack();
		frame.setBackground(Color.decode("#f0f0f0"));
		frame.setVisible(true);
	}

	public void NavigateToMenu() {
		frame.setTitle("THE SLIDE RULE - MENU");
		MenuScreen.GetInstance().UpdateUI(Collections.emptyList());
	}

	public void NavigateToGame() {
		frame.setTitle("THE SLIDE RULE - GAME");
	}

	public void NavigateToResult() {
		frame.setTitle("THE SLIDE RULE - END");
	}

	public JComponent GetCanvas() {
		return canvas;
	}
}
