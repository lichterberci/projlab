package lab.proj.ui.drawables;

import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Drawable {
	public abstract void Draw(JComponent target);
	protected Color getColorFromHashCode(int hashCode) {
		float hue = (hashCode % 1000) / 1000.0f;
        try {
            MessageDigest hashAlg = MessageDigest.getInstance("SHA-512");
			byte[] hash = hashAlg.digest(new byte[]{(byte) (hashCode >> 24), (byte) (hashCode >> 16), (byte) (hashCode >> 8), (byte) (hashCode)});
			final int HASH_LENGTH = hashAlg.getDigestLength(); // in bytes
			short result = 0;
			for (int i = 0; i < HASH_LENGTH; i+=2) {
				result ^= (short) (hash[i] << 8 | hash[i + 1]);
			}
			hue = result / 65535.0f;
		} catch (NoSuchAlgorithmException ignored) {}
		return Color.getHSBColor(hue, 0.8f, 0.9f);
	}
	protected void SetDefaults(JLabel label, Color foreground) {
		SetDefaults(label, foreground, null);
	}
	protected void SetDefaults(JButton button, Color foreground) {
		SetDefaults(button, foreground, null);
	}
	protected void SetDefaults(JLabel label, Color foreground, Color background) {
		SetDefaults((JComponent) label, foreground, background);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
	}
	protected void SetDefaults(JButton button, Color foreground, Color background) {
		SetDefaults((JComponent) button, foreground, background);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setVerticalAlignment(SwingConstants.CENTER);
	}
	protected void SetDefaults(JComponent component, Color foreground, Color background) {
		component.setForeground(foreground);
		if (background != null) {
			component.setBackground(background);
			component.setOpaque(true);
		} else {
			component.setOpaque(false);
		}
	}
	protected void SetRelativeSizes(JComponent component, JComponent target, double fontMod) {
		SetRelativeSizes(component, target, fontMod, 0.9, 0.9);
	}
	protected void SetRelativeSizes( JComponent component, JComponent target, double fontMod, double widthMod, double heightMod) {
		component.setPreferredSize(new Dimension(
				(int) (target.getMinimumSize().getWidth() * widthMod),
				(int) (target.getMinimumSize().getHeight() * heightMod)));
		component.setFont(component.getFont().deriveFont((float) (target.getMinimumSize().getHeight() * fontMod)));
	}
}
