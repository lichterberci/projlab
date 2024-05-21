package lab.proj.ui.visitors;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.*;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.InventoryItemDrawable;
import lab.proj.ui.drawables.ItemDrawable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents the inventory item drawable visitor
 */
public class InventoryItemDrawableVisitor implements ItemVisitor {
    /**
     * The drawable
     */
    private Drawable drawable = new ItemDrawable(null, "", null);

    /**
     * Visits the beer mug
     *
     * @param bm The beer mug
     */
    @Override
    public void VisitBeerMug(BeerMug bm) {
        drawable = new InventoryItemDrawable(bm, "bm", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    bm.Activate();
                    GameManager.GetInstance().EndTurn();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    bm.Drop();
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
        drawable = new InventoryItemDrawable(cm, "cm", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    cm.Activate();
                    GameManager.GetInstance().EndTurn();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    cm.Drop();
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
        drawable = new InventoryItemDrawable(m, "m", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    m.Activate();
                    GameManager.GetInstance().EndTurn();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    m.Drop();
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
    public void VisitPurifier(Purifier p) {
        drawable = new InventoryItemDrawable(p, "p", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    p.Activate();
                    GameManager.GetInstance().EndTurn();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    p.Drop();
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
    public void VisitTowel(Towel t) {
        drawable = new InventoryItemDrawable(t, "t", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    t.Activate();
                    GameManager.GetInstance().EndTurn();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    t.Drop();
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
    public void VisitSlideRule(SlideRule sr) {
        drawable = new InventoryItemDrawable(sr, "sr", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    sr.Activate();
                    GameManager.GetInstance().EndTurn();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    sr.Drop();
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
    public void VisitCSE(CSE cse) {
        drawable = new InventoryItemDrawable(cse, "cse", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    cse.Activate();
                    GameManager.GetInstance().EndTurn();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    cse.Drop();
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
    public void VisitTransistor(Transistor tr) {
        drawable = new InventoryItemDrawable(tr, "tr", new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    tr.Activate();
                    GameManager.GetInstance().EndTurn();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    tr.Drop();
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
        if (item != null)
            item.VisitItem(this);
        return drawable;
    }
}