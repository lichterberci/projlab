package lab.proj.ui.screens;

import lab.proj.controller.Application;
import lab.proj.ui.components.Component;

import javax.swing.*;

/**
 * Represents a screen in the application
 */
public interface Screen {
    /**
     * Renders the screen
     */
    void Render();

    /**
     * Sizes a JComponent
     *
     * @param component the component to size
     * @param x         the x coordinate
     * @param y         the y coordinate
     * @param width     the width
     * @param height    the height
     */
    default void SizeJComponent(JComponent component, double x, double y, double width, double height) {
        JComponent canvas = Application.GetInstance().GetCanvas();
        component.setBounds((int) (x * canvas.getWidth()),
                (int) (y * canvas.getHeight()),
                (int) (width * canvas.getWidth()),
                (int) (height * canvas.getHeight()));
    }

    /**
     * Renders a component
     *
     * @param component the component to render
     * @param x         the x coordinate
     * @param y         the y coordinate
     * @param width     the width
     * @param height    the height
     */
    default void RenderComponent(Component component, double x, double y, double width, double height) {
        JComponent canvas = Application.GetInstance().GetCanvas();
        JPanel panel = new JPanel();
        SizeJComponent(panel, x, y, width, height);
        panel.setOpaque(false);
        component.Draw(panel);
        canvas.add(panel);
    }
}
