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
	public static final Color Dark = new Color(75, 75, 75);
	public static final Color Light = new Color(200, 200, 200);
	public static final Color Background = new Color(175, 175, 175);
	public static final Color DarkText = new Color(0,0,0);
	public static final Color LightText = new Color(255, 255, 255);
	public static final Color Border = new Color(0,0,0);

	private final JFrame frame;
	private final JPanel canvas;
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
