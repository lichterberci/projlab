package lab.proj.controller;

import lab.proj.model.*;
import lab.proj.ui.drawables.*;
import lab.proj.ui.screens.*;
import lab.proj.ui.visitors.EffectDrawableVisitor;
import lab.proj.ui.visitors.ItemDrawableVisitor;
import lab.proj.ui.visitors.InventoryItemDrawableVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Application {
	public static final Color Dark = new Color(75, 75, 75);
	public static final Color Light = new Color(200, 200, 200);
	public static final Color Background = new Color(175, 175, 175);
	public static final Color DarkText = new Color(0,0,0);
	public static final Color LightText = new Color(255, 255, 255);
	public static final Color InvalidText = new Color(171, 32, 32);
	public static final Color Border = new Color(0,0,0);

	private static final int windowWidth = 1200;
	private static  final int windowHeight = 600;
	private static final String GAS_PROTECTION_SPRITE_PATH = "GameScreen_new.png";
	private static final String DROP_OUT_PROTECTION_SPRITE_PATH = "GameScreen_new.png";

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
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
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
		GameManager gm = GameManager.GetInstance();
		List<Actor> actorsInOrder = GameManager.GetInstance().ActorsInOrder();
		Room room = gm.GetCurrentActor().GetLocation();
		List<Item> inventory = new ArrayList<>(gm.GetCurrentActor().GetItems());
		inventory.addAll(Collections.nCopies(Actor.MAX_ITEMS-inventory.size(), null));

		game.SetDrawables(Convert(actorsInOrder, actor -> new TurnIndicatorDrawable(actor, actor == gm.GetCurrentActor())),
				GameScreen.ComponentNames.TurnIndicator);
		game.SetDrawables(Convert(room.GetDoors(),
			door -> new DoorDrawable(door,
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							GameManager.GetInstance().GetCurrentActor().UseDoor(door);
							GameManager.GetInstance().EndTurn();
						}
					}
				})),
			GameScreen.ComponentNames.Doors);
 		game.SetDrawables(Convert(inventory,item -> new InventoryItemDrawableVisitor().Visit(item)),
				GameScreen.ComponentNames.Inventory);
		game.SetDrawables(Convert(room.GetActors(), ActorDrawable::new),
				GameScreen.ComponentNames.Actors);
		game.SetDrawables(Convert(room.GetItemsOnTheFloor(), item -> new ItemDrawableVisitor().Visit(item)),
				GameScreen.ComponentNames.Items);
		game.SetDrawables(Convert(room.GetEffects(), effect -> new EffectDrawableVisitor().Visit(effect)),
				GameScreen.ComponentNames.Effects);
		game.SetDrawables(Convert(
				Stream.of(
						gm.GetCurrentActor().HasGasProtection() ? GAS_PROTECTION_SPRITE_PATH : "",
						gm.GetCurrentActor().HasDropOutProtection() ? DROP_OUT_PROTECTION_SPRITE_PATH : ""
					)
						.filter(path -> !path.isEmpty())
						.toList(),
					StatusDrawable::new
				),
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
