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
        JComponent canvas = Application.GetInstance().GetCanvas();
        canvas.removeAll();

        RenderText(canvas);

        canvas.revalidate();
        canvas.repaint();
    }

    private void RenderText(JComponent canvas) {
        SizeJComponent(label, canvas, 0.05, 0.1, 0.9, 0.8);
        label.setFont(label.getFont().deriveFont(label.getHeight() * 0.22f));
        canvas.add(label);
    }
}
