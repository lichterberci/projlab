package lab.proj.ui.visitors;

import lab.proj.model.Curse;
import lab.proj.model.GasPoisoning;
import lab.proj.model.RoomEffectVisitor;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.EffectDrawable;

public class EffectDrawableVisitor implements RoomEffectVisitor {

	private Drawable drawable;

	@Override
	public void VisitGasPoisoning(GasPoisoning gp) {
		drawable = new EffectDrawable(gp, "/lab/proj/assets/GameScreen_Render.png");
	}

	@Override
	public void VisitCurse(Curse c) {
		drawable = new EffectDrawable(c, "/lab/proj/assets/GameScreen_Render.png");
	}

	public Drawable getDrawable() {
		return drawable;
	}
}
