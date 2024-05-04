package lab.proj.ui.screens;

import lab.proj.controller.Application;

import javax.swing.*;

public class ResultScreen {
    private final JLabel label;
    private String result;

    public ResultScreen() {
        label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Application.Dark);
        label.setForeground(Application.LightText);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }
    public void Render() {
        JComponent canvas = Application.GetInstance().GetCanvas();
        canvas.removeAll();

        RenderText(canvas);

        canvas.revalidate();
        canvas.repaint();
    }

    public void SetResult(String result) {
        this.result = result;
    }

    private void RenderText(JComponent canvas) {
        label.setBounds((int)(canvas.getWidth() * 0.05),
                (int)(canvas.getHeight() * 0.1),
                (int)(canvas.getWidth() * 0.9),
                (int)(canvas.getHeight() * 0.8));
        label.setFont(label.getFont().deriveFont(label.getHeight() * 0.22f));
        label.setText(result);
        canvas.add(label);
    }
}
