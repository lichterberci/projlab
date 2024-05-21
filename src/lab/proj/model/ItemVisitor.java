package lab.proj.model;

/**
 * An interface representing a visitor that visits items in the game environment.
 * This interface defines methods for visiting each type of item.
 */
public interface ItemVisitor {
	/**
	 * Defines the behavior of visiting a beer mug item.
	 *
	 * @param bm The beer mug item being visited.
	 */
	void VisitBeerMug(BeerMug bm);
	/**
	 * Defines the behavior of visiting a camembert item.
	 *
	 * @param c The camembert item being visited.
	 */
	void VisitCamembert(Camembert c);
	/**
	 * Defines the behavior of visiting a mask item.
	 *
	 * @param m The mask item being visited.
	 */
	void VisitMask(Mask m);
	/**
	 * Defines the behavior of visiting a purifier item.
	 *
	 * @param p The purifier item being visited.
	 */
	void VisitPurifier(Purifier p);
	/**
	 * Defines the behavior of visiting a towel item.
	 *
	 * @param t The towel item being visited.
	 */
	void VisitTowel(Towel t);
	/**
	 * Defines the behavior of visiting a slide rule item.
	 *
	 * @param sr The slide rule item being visited.
	 */
	void VisitSlideRule(SlideRule sr);
	/**
	 * Defines the behavior of visiting a CSE item.
	 *
	 * @param c The CSE item being visited.
	 */
	void VisitCSE(CSE c);
	/**
	 * Defines the behavior of visiting a transistor item.
	 *
	 * @param t The transistor item being visited.
	 */
	void VisitTransistor(Transistor t);
}
