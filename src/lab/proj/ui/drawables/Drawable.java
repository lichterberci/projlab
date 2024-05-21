package lab.proj.ui.drawables;

import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a drawable in the UI
 */
public abstract class Drawable {

    /**
     * Draws the drawable
     *
     * @param target The target component to draw on
     */
    public abstract void Draw(JComponent target);

    /**
     * Gets the color from the hash code
     *
     * @param hashCode The hash code
     * @return The color
     */
    protected Color getColorFromHashCode(int hashCode) {
        float hue = (hashCode % 1000) / 1000.0f;
        try {
            MessageDigest hashAlg = MessageDigest.getInstance("SHA-512");
            byte[] hash = hashAlg.digest(new byte[]{(byte) (hashCode >> 24), (byte) (hashCode >> 16), (byte) (hashCode >> 8), (byte) (hashCode)});
            final int HASH_LENGTH = hashAlg.getDigestLength(); // in bytes
            short result = 0;
            for (int i = 0; i < HASH_LENGTH; i += 2) {
                result ^= (short) (hash[i] << 8 | hash[i + 1]);
            }
            hue = result / 65535.0f;
        } catch (NoSuchAlgorithmException ignored) {
        }
        return Color.getHSBColor(hue, 0.8f, 0.9f);
    }

    /**
     * Sets the default properties of a label
     *
     * @param label      The label
     * @param foreground The foreground color
     */
    protected void SetDefaults(JLabel label, Color foreground) {
        SetDefaults(label, foreground, null);
    }

    /**
     * Sets the default properties of a button
     *
     * @param button     The button
     * @param foreground The foreground color
     */
    protected void SetDefaults(JButton button, Color foreground) {
        SetDefaults(button, foreground, null);
    }

    /**
     * Sets the default properties of a component
     *
     * @param component  The component
     * @param foreground The foreground color
     */
    protected void SetDefaults(JLabel label, Color foreground, Color background) {
        SetDefaults((JComponent) label, foreground, background);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }

    /**
     * Sets the default properties of a button
     *
     * @param button     The button
     * @param foreground The foreground color
     */
    protected void SetDefaults(JButton button, Color foreground, Color background) {
        SetDefaults((JComponent) button, foreground, background);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
    }

    /**
     * Sets the default properties of a component
     *
     * @param component  The component
     * @param foreground The foreground color
     * @param background The background color
     */
    protected void SetDefaults(JComponent component, Color foreground, Color background) {
        component.setForeground(foreground);
        if (background != null) {
            component.setBackground(background);
            component.setOpaque(true);
        } else {
            component.setOpaque(false);
        }
    }

    /**
     * Sets the relative sizes of a component
     *
     * @param component The component
     * @param target    The target component
     * @param fontMod   The font modifier
     */
    protected void SetRelativeSizes(JComponent component, JComponent target, double fontMod) {
        SetRelativeSizes(component, target, fontMod, 0.9, 0.9);
    }

    /**
     * Sets the relative sizes of a component
     *
     * @param component The component
     * @param target    The target component
     * @param fontMod   The font modifier
     * @param widthMod  The width modifier
     * @param heightMod The height modifier
     */
    protected void SetRelativeSizes(JComponent component, JComponent target, double fontMod, double widthMod, double heightMod) {
        component.setPreferredSize(new Dimension(
                (int) (target.getMinimumSize().getWidth() * widthMod),
                (int) (target.getMinimumSize().getHeight() * heightMod)));
        component.setFont(component.getFont().deriveFont((float) (target.getMinimumSize().getHeight() * fontMod)));
    }
}
