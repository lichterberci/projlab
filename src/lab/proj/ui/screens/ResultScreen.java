package lab.proj.ui.screens;

import lab.proj.ui.Application;

import javax.swing.*;
import java.awt.*;

public class ResultScreen {
    private final JComponent canvas;

    private static ResultScreen instance;
    public static ResultScreen GetInstance() {
        if (instance == null)
            instance = new ResultScreen();
        return instance;
    }

    private ResultScreen() {
        this.canvas = Application.GetInstance().GetCanvas();
    }

    public void SetResult(String result) {
        canvas.removeAll();

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        JLabel text = new JLabel(result, SwingConstants.CENTER);
        text.setBounds((int)(width * 0.05), (int)(height * 0.1), (int)(width * 0.9), (int)(height * 0.8));
        text.setFont(text.getFont().deriveFont(width * 0.12f));
        text.setForeground(Color.WHITE);
        text.setOpaque(true);
        text.setBackground(Color.decode("#c0c0c0"));
        text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        canvas.add(text);
        canvas.revalidate();
        canvas.repaint();
    }
}
