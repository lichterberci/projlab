package lab.proj.ui.screens;

import lab.proj.ui.components.Component;

import javax.swing.*;

public interface Screen {
    void Render();
    default void SizeJComponent(JComponent component, JComponent canvas, double x, double y, double width, double height) {
        component.setBounds((int) (x * canvas.getWidth()),
                (int) (y * canvas.getHeight()),
                (int) (width * canvas.getWidth()),
                (int) (height * canvas.getHeight()));
    }
    default void RenderComponent(Component component, JComponent canvas, double x, double y, double width, double height) {
        JPanel panel = new JPanel();
        SizeJComponent(panel, canvas, x, y, width, height);
        panel.setOpaque(false);
        component.Draw(panel);
        canvas.add(panel);
    }
}
