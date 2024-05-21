package lab.proj.ui.screens;

import lab.proj.controller.Application;

import javax.swing.*;

/**
 * Represents the screen that displays the result of the game.
 */
public class ResultScreen implements Screen {
    /**
     * The label that displays the result
     */
    private final JLabel label;

    /**
     * Creates a new result screen
     */
    public ResultScreen() {
        label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Application.Dark);
        label.setForeground(Application.LightText);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }

    /**
     * Sets the result to display
     *
     * @param result the result to display
     */
    public void SetResult(String result) {
        label.setText(result);
    }

    /**
     * Renders the screen
     */
    @Override
    public void Render() {
        RenderText();
    }

    /**
     * Renders the text
     */
    private void RenderText() {
        SizeJComponent(label, 0.05, 0.1, 0.9, 0.8);
        label.setFont(label.getFont().deriveFont(label.getHeight() * 0.22f));
        Application.GetInstance().GetCanvas().add(label);
    }
}
