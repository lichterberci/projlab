package lab.proj.ui.screens;

import lab.proj.controller.Application;
import lab.proj.ui.components.Component;

import javax.swing.*;

public interface Screen {
    void Render();
    default void SizeJComponent(JComponent component, double x, double y, double width, double height) {
        JComponent canvas = Application.GetInstance().GetCanvas();
        component.setBounds((int) (x * canvas.getWidth()),
                (int) (y * canvas.getHeight()),
                (int) (width * canvas.getWidth()),
                (int) (height * canvas.getHeight()));
    }
    default void RenderComponent(Component component, double x, double y, double width, double height) {
        JComponent canvas = Application.GetInstance().GetCanvas();
        JPanel panel = new JPanel();
        SizeJComponent(panel, x, y, width, height);
        panel.setOpaque(false);
        component.Draw(panel);
        canvas.add(panel);
    }
}
