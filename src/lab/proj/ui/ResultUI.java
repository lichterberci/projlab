package lab.proj.ui;

import javax.swing.*;
import java.awt.*;

public class ResultUI {
    private final int width = 800;
    private final int height = 600;
    private final JFrame frame;
    public ResultUI() {
        frame = new JFrame("Result");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLayout(null);
        frame.setBackground(Color.decode("#f0f0f0"));
        frame.setVisible(true);
    }

    public void SetResult(String result) {
        frame.getContentPane().removeAll();
        JLabel text = new JLabel(result, SwingConstants.CENTER);
        text.setBounds((int)(width * 0.05), (int)(height * 0.1), (int)(width * 0.9), (int)(height * 0.8));
        text.setFont(text.getFont().deriveFont(width * 0.12f));
        text.setForeground(Color.WHITE);
        text.setOpaque(true);
        text.setBackground(Color.decode("#c0c0c0"));
        text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        frame.add(text);
        frame.revalidate();
        frame.repaint();
    }
}
