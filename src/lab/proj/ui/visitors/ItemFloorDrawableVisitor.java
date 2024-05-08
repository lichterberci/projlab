package lab.proj.ui.visitors;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.model.*;
import lab.proj.ui.drawables.*;

import java.util.function.Consumer;

public class ItemFloorDrawableVisitor implements ItemVisitor {
	private Drawable item;

	@Override
	public void VisitBeerMug(BeerMug bm) {
		item = new ItemDrawableBuilder<BeerMug>()
				.setItem(bm)
				.setName("Beer Mug")
				.setOnBtnLftClick((Consumer<BeerMug>)((BeerMug item) -> {
					item.PickUp(GameManager.GetInstance().GetCurrentActor());
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitCamembert(Camembert c) {
		item = new ItemDrawableBuilder<Camembert>()
				.setItem(c)
				.setName("Camembert")
				.setOnBtnLftClick((Consumer<Camembert>)((Camembert item) -> {
					item.PickUp(GameManager.GetInstance().GetCurrentActor());
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitMask(Mask m) {
		item = new ItemDrawableBuilder<Mask>()
				.setItem(m)
				.setName("Mask")
				.setOnBtnLftClick((Consumer<Mask>)((Mask item) -> {
					item.PickUp(GameManager.GetInstance().GetCurrentActor());
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitPurifier(Purifier p) {
		item = new ItemDrawableBuilder<Purifier>()
				.setItem(p)
				.setName("Purifier")
				.setOnBtnLftClick((Consumer<Purifier>)((Purifier item) -> {
					item.PickUp(GameManager.GetInstance().GetCurrentActor());
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitTowel(Towel t) {
		item = new ItemDrawableBuilder<Towel>()
				.setItem(t)
				.setName("Towel")
				.setOnBtnLftClick((Consumer<Towel>)((Towel item) -> {
					item.PickUp(GameManager.GetInstance().GetCurrentActor());
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitSlideRule(SlideRule sr) {
		item = new ItemDrawableBuilder<SlideRule>()
				.setItem(sr)
				.setName("Slide Rule")
				.setOnBtnLftClick((Consumer<SlideRule>)((SlideRule item) -> {
					item.PickUp(GameManager.GetInstance().GetCurrentActor());
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitCSE(CSE c) {
		item = new ItemDrawableBuilder<CSE>()
				.setItem(c)
				.setName("CSE")
				.setOnBtnLftClick((Consumer<CSE>)((CSE item) -> {
					item.PickUp(GameManager.GetInstance().GetCurrentActor());
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	@Override
	public void VisitTransistor(Transistor t) {
		item = new ItemDrawableBuilder<Transistor>()
				.setItem(t)
				.setName("Transistor")
				.setOnBtnLftClick((Consumer<Transistor>) ((Transistor item) -> {
					item.PickUp(GameManager.GetInstance().GetCurrentActor());
					if (GameManager.GetInstance().isRunning())
						Application.GetInstance().RenderGameScreen();
				}))
				.build();
	}

	public Drawable getDrawable() {
		return item;
	}
}
