package lab.proj.ui.visitors;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.*;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.ItemDrawable;
import lab.proj.ui.drawables.ItemDrawableBuilder;

import java.util.Optional;
import java.util.function.Consumer;

public class ItemInventoryDrawableVisitor implements ItemVisitor {
	private Drawable item = new ItemDrawable<Item>(null,
			"",
			Optional.empty(),
			Optional.empty());

	@Override
	public void VisitBeerMug(BeerMug bm) {
		item = new ItemDrawableBuilder<BeerMug>()
				.setItem(bm)
				.setName("bm")
				.setOnBtnLftClick((Consumer<BeerMug>)((BeerMug item) -> {
					item.Activate();
					GameManager.GetInstance().EndTurn();
				}))
				.setOnBtnRgtClick((Consumer<BeerMug>)((BeerMug item) -> {
					item.Drop();
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitCamembert(Camembert c) {
		item = new ItemDrawableBuilder<Camembert>()
				.setItem(c)
				.setName("cm")
				.setOnBtnLftClick((Consumer<Camembert>)((Camembert item) -> {
					item.Activate();
					GameManager.GetInstance().EndTurn();
				}))
				.setOnBtnRgtClick((Consumer<Camembert>)((Camembert item) -> {
					item.Drop();
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitMask(Mask m) {
		item = new ItemDrawableBuilder<Mask>()
				.setItem(m)
				.setName("m")
				.setOnBtnLftClick((Consumer<Mask>)((Mask item) -> {
					item.Activate();
					GameManager.GetInstance().EndTurn();
				}))
				.setOnBtnRgtClick((Consumer<Mask>)((Mask item) -> {
					item.Drop();
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitPurifier(Purifier p) {
		item = new ItemDrawableBuilder<Purifier>()
				.setItem(p)
				.setName("p")
				.setOnBtnLftClick((Consumer<Purifier>)((Purifier item) -> {
					item.Activate();
					GameManager.GetInstance().EndTurn();
				}))
				.setOnBtnRgtClick((Consumer<Purifier>)((Purifier item) -> {
					item.Drop();
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitTowel(Towel t) {
		item = new ItemDrawableBuilder<Towel>()
				.setItem(t)
				.setName("t")
				.setOnBtnLftClick((Consumer<Towel>)((Towel item) -> {
					item.Activate();
					GameManager.GetInstance().EndTurn();
				}))
				.setOnBtnRgtClick((Consumer<Towel>)((Towel item) -> {
					item.Drop();
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitSlideRule(SlideRule sr) {
		item = new ItemDrawableBuilder<SlideRule>()
				.setItem(sr)
				.setName("sr")
				.setOnBtnLftClick((Consumer<SlideRule>)((SlideRule item) -> {
					item.Activate();
					GameManager.GetInstance().EndTurn();
				}))
				.setOnBtnRgtClick((Consumer<SlideRule>)((SlideRule item) -> {
					item.Drop();
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitCSE(CSE c) {
		item = new ItemDrawableBuilder<CSE>()
				.setItem(c)
				.setName("cse")
				.setOnBtnLftClick((Consumer<CSE>)((CSE item) -> {
					item.Activate();
					GameManager.GetInstance().EndTurn();
				}))
				.setOnBtnRgtClick((Consumer<CSE>)((CSE item) -> {
					item.Drop();
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitTransistor(Transistor t) {
		item = new ItemDrawableBuilder<Transistor>()
				.setItem(t)
				.setName("tr")
				.setOnBtnLftClick((Consumer<Transistor>) ((Transistor item) -> {
					item.Activate();
					GameManager.GetInstance().EndTurn();
				}))
				.setOnBtnRgtClick((Consumer<Transistor>) ((Transistor item) -> {
					item.Drop();
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	public Drawable getDrawable() {
		return item;
	}

	public void VisitItem(Item item) {
		if (item == null) return;
		item.VisitItem(this);
	}
}