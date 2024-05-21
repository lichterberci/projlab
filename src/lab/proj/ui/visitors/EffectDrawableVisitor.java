package lab.proj.ui.visitors;

import lab.proj.model.Curse;
import lab.proj.model.GasPoisoning;
import lab.proj.model.RoomEffect;
import lab.proj.model.RoomEffectVisitor;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.StatusDrawable;

/**
 * A visitor that visits effects and returns the drawable
 */
public class EffectDrawableVisitor implements RoomEffectVisitor {

    /**
     * The drawable
     */
    private Drawable drawable;

    /**
     * Visits the effect and returns the drawable
     *
     * @param effect the effect to visit
     * @return the drawable
     */
    @Override
    public void VisitGasPoisoning(GasPoisoning gp) {
        drawable = new StatusDrawable("☢");
    }

    /**
     * Visits the effect and returns the drawable
     *
     * @param effect the effect to visit
     * @return the drawable
     */
    @Override
    public void VisitCurse(Curse c) {
        drawable = new StatusDrawable("😈");
    }

    /**
     * Gets the drawable
     *
     * @return the drawable
     */
    public Drawable getDrawable() {
        return drawable;
    }

    /**
     * Visits the effect and returns the drawable
     *
     * @param effect the effect to visit
     * @return the drawable
     */
    public Drawable Visit(RoomEffect effect) {
        if (effect == null) return null;
        effect.VisitRoomEffect(this);
        return drawable;
    }
}
