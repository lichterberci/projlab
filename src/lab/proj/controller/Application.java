package lab.proj.controller;

import lab.proj.model.Actor;
import lab.proj.model.Item;
import lab.proj.model.Room;
import lab.proj.ui.drawables.*;
import lab.proj.ui.screens.GameScreen;
import lab.proj.ui.screens.MenuScreen;
import lab.proj.ui.screens.ResultScreen;
import lab.proj.ui.visitors.EffectDrawableVisitor;
import lab.proj.ui.visitors.InventoryItemDrawableVisitor;
import lab.proj.ui.visitors.ItemDrawableVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Represents the application
 */
public class Application {
    /**
     * The dark color
     */
    public static final Color Dark = new Color(75, 75, 75);
    /**
     * The light color
     */
    public static final Color Light = new Color(200, 200, 200);
    /**
     * The background color
     */
    public static final Color Background = new Color(175, 175, 175);
    /**
     * The dark text color
     */
    public static final Color DarkText = new Color(0, 0, 0);
    /**
     * The light text color
     */
    public static final Color LightText = new Color(255, 255, 255);
    /**
     * The invalid text color
     */
    public static final Color InvalidText = new Color(171, 32, 32);
    /**
     * The border color
     */
    public static final Color Border = new Color(0, 0, 0);

    /**
     * The width of the window
     */
    private static final int windowWidth = 1200;
    /**
     * The height of the window
     */
    private static final int windowHeight = 600;
    /**
     * The path to the gas protection sprite
     */
    private static final String GAS_PROTECTION_SPRITE_PATH = "GameScreen_new.png";
    /**
     * The path to the drop-out protection sprite
     */
    private static final String DROP_OUT_PROTECTION_SPRITE_PATH = "GameScreen_new.png";
    /**
     * The instance of the application
     */
    private static Application instance;
    /**
     * The instance of the application
     */
    private final JFrame frame;
    /**
     * The canvas
     */
    private final JPanel canvas;
    /**
     * The menu screen
     */
    private final MenuScreen menu;
    /**
     * The game screen
     */
    private final GameScreen game;
    /**
     * The result screen
     */
    private final ResultScreen result;

    /**
     * Creates a new application
     */
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

    /**
     * Gets the instance of the application
     *
     * @return the instance of the application
     */
    public static Application GetInstance() {
        if (instance == null)
            instance = new Application();
        return instance;
    }

    /**
     * Renders the menu screen
     */
    public void RenderMenuScreen() {
        frame.setTitle("THE SLIDE RULE - MENU");
        menu.SetStudents(Convert(GameManager.GetInstance().GetStudents(), StudentNameDrawable::new));

        canvas.removeAll();
        menu.Render();
        canvas.revalidate();
        canvas.repaint();
    }

    /**
     * Renders the game screen
     */
    public void RenderGameScreen() {
        frame.setTitle("THE SLIDE RULE - GAME");
        GameManager gm = GameManager.GetInstance();
        List<Actor> actorsInOrder = GameManager.GetInstance().ActorsInOrder();
        Room room = gm.GetCurrentActor().GetLocation();
        List<Item> inventory = new ArrayList<>(gm.GetCurrentActor().GetItems());
        inventory.addAll(Collections.nCopies(Actor.MAX_ITEMS - inventory.size(), null));

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
        game.SetDrawables(Convert(inventory, item -> new InventoryItemDrawableVisitor().Visit(item)),
                GameScreen.ComponentNames.Inventory);
        game.SetDrawables(Convert(room.GetActors(), ActorDrawable::new),
                GameScreen.ComponentNames.Actors);
        game.SetDrawables(Convert(room.GetItemsOnTheFloor(), item -> new ItemDrawableVisitor().Visit(item)),
                GameScreen.ComponentNames.Items);
        game.SetDrawables(Convert(room.GetEffects(), effect -> new EffectDrawableVisitor().Visit(effect)),
                GameScreen.ComponentNames.Effects);
        game.SetDrawables(Convert(
                        Stream.of(
                                        gm.GetCurrentActor().HasGasProtection() ? "😷" : "",
                                        gm.GetCurrentActor().HasDropOutProtection() ? "🎓" : ""
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

    /**
     * Renders the result screen
     */
    public void RenderResultScreen() {
        frame.setTitle("THE SLIDE RULE - END");
        result.SetResult(GameManager.GetInstance().isWon() ? "Students win!" : "Teachers win!");

        canvas.removeAll();
        result.Render();
        canvas.revalidate();
        canvas.repaint();
    }

    /**
     * Sets the size of the component
     *
     * @param component the component
     * @param x         the x coordinate
     * @param y         the y coordinate
     * @param width     the width
     * @param height    the height
     */
    public JComponent GetCanvas() {
        return canvas;
    }

    /**
     * Converts a list of elements to a list of drawables
     *
     * @param from the list of elements
     * @param gen  the function that generates the drawable
     * @param <B>  the type of the elements
     * @param <D>  the type of the drawables
     * @return the list of drawables
     */
    private <B, D extends Drawable> List<Drawable> Convert(List<B> from, Function<B, D> gen) {
        return from.stream().map(gen).map(ed -> (Drawable) ed).toList();
    }
}
