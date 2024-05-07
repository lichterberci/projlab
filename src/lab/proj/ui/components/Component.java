package lab.proj.ui.components;

import lab.proj.ui.drawables.Drawable;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Component {
    protected  List<Drawable> drawables = new ArrayList<>();

    public void SetDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
    }
    public abstract void Draw(JComponent target);
    protected void AddDrawables(double widthMod, double heightMod, JComponent target) {
        for (Drawable drawable : drawables) {
            Dimension size = new Dimension(
                    (int) (target.getWidth() * widthMod),
                    (int) (target.getHeight() * heightMod));
            JPanel panel = new JPanel();
            panel.setMinimumSize(size);
            panel.setMaximumSize(size);
            panel.setOpaque(false);
            drawable.Draw(panel);
            target.add(panel);
        }
    }
    protected JPanel MakePanelInScroll(JComponent target, Border border, int hPolicy, int vPolicy) {
        JPanel panel = new JPanel();
        panel.setSize(target.getSize());
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(border);
        scrollPane.setHorizontalScrollBarPolicy(hPolicy);
        scrollPane.setVerticalScrollBarPolicy(vPolicy);
        scrollPane.setOpaque(false);
        target.setLayout(new BorderLayout());
        target.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}
