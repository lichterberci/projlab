package lab.proj.ui.screens;

import lab.proj.controller.Application;

import javax.swing.*;

public class ResultScreen implements Screen {
    private final JLabel label;

    public ResultScreen() {
        label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Application.Dark);
        label.setForeground(Application.LightText);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }

    public void SetResult(String result) {
        label.setText(result);
    }

    @Override
    public void Render() {
        RenderText();
    }

    private void RenderText() {
        SizeJComponent(label, 0.05, 0.1, 0.9, 0.8);
        label.setFont(label.getFont().deriveFont(label.getHeight() * 0.22f));
        Application.GetInstance().GetCanvas().add(label);
    }
}
