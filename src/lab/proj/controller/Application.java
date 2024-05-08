package lab.proj.controller;

import lab.proj.model.*;
import lab.proj.ui.drawables.*;
import lab.proj.ui.screens.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Application {
	public static final Color Dark = new Color(75, 75, 75);
	public static final Color Light = new Color(200, 200, 200);
	public static final Color Background = new Color(175, 175, 175);
	public static final Color DarkText = new Color(0,0,0);
	public static final Color LightText = new Color(255, 255, 255);
	public static final Color InvalidText = new Color(150, 150, 150);
	public static final Color Border = new Color(0,0,0);

	private static final int windowWidth = 1200;
	private static  final int windowHeight = 600;

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
		frame.getContentPane().setPreferredSize(new Dimension(windowWidth, windowHeight));
		frame.pack();
		frame.setBackground(Background);

		canvas = new JPanel();
		canvas.setLayout(null);
		canvas.setBounds(0, 0, windowWidth, windowHeight);
		canvas.setOpaque(false);

		frame.add(canvas);
		frame.setVisible(true);
		menu = new MenuScreen();
		game = new GameScreen();
		result = new ResultScreen();
	}

	public void RenderMenuScreen() {
		frame.setTitle("THE SLIDE RULE - MENU");
		menu.SetStudents(Convert(GameManager.GetInstance().GetStudents(), StudentNameDrawable::new));

		canvas.removeAll();
		menu.Render();
		canvas.revalidate();
		canvas.repaint();
	}

	public void RenderGameScreen() {
		frame.setTitle("THE SLIDE RULE - GAME");
		List<Actor> actorsInOrder = GameManager.GetInstance().ActorsInOrder();
		Actor currentActor = actorsInOrder.get(0);
		Room room = currentActor.GetLocation();
		List<Item> inventory = new ArrayList<>(currentActor.GetItems());
		inventory.addAll(Collections.nCopies(Actor.MAX_ITEMS-inventory.size(), null));

		game.SetDrawables(Convert(actorsInOrder, actor -> new TurnIndicatorDrawable(actor, actor == currentActor)),
				GameScreen.ComponentNames.TurnIndicator);
		game.SetDrawables(Convert(room.GetDoors(), door -> new DoorDrawable(door, currentActor)),
				GameScreen.ComponentNames.Doors);
 		game.SetDrawables(Convert(inventory, InventoryItemDrawable::new),
				GameScreen.ComponentNames.Inventory);
		game.SetDrawables(Convert(room.GetActors(), ActorDrawable::new),
				GameScreen.ComponentNames.Actors);
		game.SetDrawables(Convert(room.GetItemsOnTheFloor(), item -> new ItemDrawable(item, currentActor)),
				GameScreen.ComponentNames.Items);
		game.SetDrawables(Convert(room.GetEffects(), EffectDrawable::new),
				GameScreen.ComponentNames.Effects);
		game.SetDrawables(Convert(currentActor.GetCharges(), ChargeDrawable::new),
				GameScreen.ComponentNames.Charges);

		canvas.removeAll();
		game.Render();
		canvas.revalidate();
		canvas.repaint();
	}

	public void RenderResultScreen() {
		frame.setTitle("THE SLIDE RULE - END");
		result.SetResult(GameManager.GetInstance().isWon()? "Students win!" : "Teachers win!");

		canvas.removeAll();
		result.Render();
		canvas.revalidate();
		canvas.repaint();
	}

	public JComponent GetCanvas() {
		return canvas;
	}

	private <B, D extends Drawable> List<Drawable> Convert(List<B> from, Function<B, D> gen) {
		return from.stream().map(gen).map(ed -> (Drawable) ed).toList();
	}
}
