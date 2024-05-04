package lab.proj.ui.drawables;

import javax.swing.JComponent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Drawable {
	void Draw(JComponent target);
	default float getHueColorFromHashCode(int hashCode) {
        try {
            MessageDigest hashAlg = MessageDigest.getInstance("SHA-512");
			byte[] hash = hashAlg.digest(new byte[]{(byte) (hashCode >> 24), (byte) (hashCode >> 16), (byte) (hashCode >> 8), (byte) (hashCode)});
			final int HASH_LENGTH = hashAlg.getDigestLength(); // in bytes
			short result = 0;
			for (int i = 0; i < HASH_LENGTH; i+=2) {
				result ^= (short) (hash[i] << 8 | hash[i + 1]);
			}
			return result / 65535.0f;
		} catch (NoSuchAlgorithmException e) {
			return (hashCode % 1000) / 1000.0f;
        }
	}
}
