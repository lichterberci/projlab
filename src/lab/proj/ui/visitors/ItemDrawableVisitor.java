package lab.proj.ui.visitors;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.*;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.ItemDrawable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents the item drawable visitor
 */
public class ItemDrawableVisitor implements ItemVisitor {
    /**
     * The drawable
     */
    private Drawable drawable;

    /**
     * Visits the beer mug
     *
     * @param bm The beer mug
     */
    @Override
    public void VisitBeerMug(BeerMug bm) {
        drawable = new ItemDrawable(bm, "Beer Mug", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    bm.PickUp(GameManager.GetInstance().GetCurrentActor());
                    if (GameManager.GetInstance().isRunning())
                        Application.GetInstance().RenderGameScreen();
                }
            }
        });
    }

    /**
     * Visits the camembert
     *
     * @param cm The camembert
     */
    @Override
    public void VisitCamembert(Camembert cm) {
        drawable = new ItemDrawable(cm, "Camembert", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    cm.PickUp(GameManager.GetInstance().GetCurrentActor());
                    if (GameManager.GetInstance().isRunning())
                        Application.GetInstance().RenderGameScreen();
                }
            }
        });
    }

    /**
     * Visits the mask
     *
     * @param m The mask
     */
    @Override
    public void VisitMask(Mask m) {
        drawable = new ItemDrawable(m, "Mask", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    m.PickUp(GameManager.GetInstance().GetCurrentActor());
                    if (GameManager.GetInstance().isRunning())
                        Application.GetInstance().RenderGameScreen();
                }
            }
        });
    }

    /**
     * Visits the purifier
     *
     * @param p The purifier
     */
    @Override
    public void VisitPurifier(Purifier p) {
        drawable = new ItemDrawable(p, "Purifier", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    p.PickUp(GameManager.GetInstance().GetCurrentActor());
                    if (GameManager.GetInstance().isRunning())
                        Application.GetInstance().RenderGameScreen();
                }
            }
        });
    }

    /**
     * Visits the towel
     *
     * @param t The towel
     */
    @Override
    public void VisitTowel(Towel t) {
        drawable = new ItemDrawable(t, "Towel", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    t.PickUp(GameManager.GetInstance().GetCurrentActor());
                    if (GameManager.GetInstance().isRunning())
                        Application.GetInstance().RenderGameScreen();
                }
            }
        });
    }


    /**
     * Visits the slide rule
     *
     * @param sr The slide rule
     */
    @Override
    public void VisitSlideRule(SlideRule sr) {
        drawable = new ItemDrawable(sr, "Slide Rule", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    sr.PickUp(GameManager.GetInstance().GetCurrentActor());
                    if (GameManager.GetInstance().isRunning())
                        Application.GetInstance().RenderGameScreen();
                }
            }
        });
    }

    /**
     * Visits the cse
     *
     * @param cse The cse
     */
    @Override
    public void VisitCSE(CSE cse) {
        drawable = new ItemDrawable(cse, "CSE", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    cse.PickUp(GameManager.GetInstance().GetCurrentActor());
                    if (GameManager.GetInstance().isRunning())
                        Application.GetInstance().RenderGameScreen();
                }
            }
        });
    }

    /**
     * Visits the transistor
     *
     * @param tr The transistor
     */
    @Override
    public void VisitTransistor(Transistor tr) {
        drawable = new ItemDrawable(tr, "Transistor", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    tr.PickUp(GameManager.GetInstance().GetCurrentActor());
                    if (tr.IsActivated())
                        GameManager.GetInstance().EndTurn();
                    if (GameManager.GetInstance().isRunning())
                        Application.GetInstance().RenderGameScreen();
                }
            }
        });
    }

    /**
     * Visits the mask
     *
     * @param m The mask
     */
    public Drawable getDrawable() {
        return drawable;
    }

    /**
     * Visits the item
     *
     * @param item The item
     * @return The drawable
     */
    public Drawable Visit(Item item) {
        if (item == null) return null;
        item.VisitItem(this);
        return drawable;
    }
}
