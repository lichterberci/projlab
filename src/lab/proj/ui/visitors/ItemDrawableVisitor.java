package lab.proj.ui.visitors;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.*;
import lab.proj.ui.drawables.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemDrawableVisitor implements ItemVisitor {
	private Drawable drawable;

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

	@Override
	public void VisitTransistor(Transistor tr) {
		drawable = new ItemDrawable(tr, "Transistor", new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					tr.PickUp(GameManager.GetInstance().GetCurrentActor());
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}
			}
		});
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public Drawable Visit(Item item) {
		if (item == null) return null;
		item.VisitItem(this);
		return drawable;
	}
}
