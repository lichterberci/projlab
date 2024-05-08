package lab.proj.model;

public interface ItemVisitor {
	void VisitBeerMug(BeerMug bm);
	void VisitCamembert(Camembert c);
	void VisitMask(Mask m);
	void VisitPurifier(Purifier p);
	void VisitTowel(Towel t);
	void VisitSlideRule(SlideRule sr);
	void VisitCSE(CSE c);
	void VisitTransistor(Transistor t);
}
