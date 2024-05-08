package lab.proj.ui.visitors;

import lab.proj.model.Curse;
import lab.proj.model.GasPoisoning;
import lab.proj.model.RoomEffect;
import lab.proj.model.RoomEffectVisitor;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.StatusDrawable;

public class EffectDrawableVisitor implements RoomEffectVisitor {

	private Drawable drawable;

	@Override
	public void VisitGasPoisoning(GasPoisoning gp) {
		drawable = new StatusDrawable("/lab/proj/assets/GameScreen_Render.png");
	}

	@Override
	public void VisitCurse(Curse c) {
		drawable = new StatusDrawable("/lab/proj/assets/GameScreen_Render.png");
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public Drawable Visit(RoomEffect effect) {
		if (effect == null) return null;
		effect.VisitRoomEffect(this);
		return drawable;
	}
}
