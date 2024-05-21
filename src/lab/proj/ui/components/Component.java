package lab.proj.ui.components;

import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a component in the UI
 */
public abstract class Component {
    /**
     * The list of drawables in the component
     */
    protected List<Drawable> drawables = new ArrayList<>();

    /**
     * Sets the drawables in the component
     *
     * @param drawables The list of drawables to set
     */
    public void SetDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
    }

    /**
     * Draws the component
     *
     * @param target The target component to draw on
     */
    public abstract void Draw(JComponent target);

    /**
     * Adds drawables to the target component
     *
     * @param widthMod  The width modifier
     * @param heightMod The height modifier
     * @param target    The target component to add drawables to
     */
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

    /**
     * Makes a panel in a scroll pane
     *
     * @param target  The target component to add the scroll pane to
     * @param border  The border of the scroll pane
     * @param hPolicy The horizontal scroll bar policy
     * @param vPolicy The vertical scroll bar policy
     * @return The panel in the scroll pane
     */
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
