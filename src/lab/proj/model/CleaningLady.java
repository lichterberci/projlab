package lab.proj.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class representing a cleaning lady in the game environment.
 * Cleaning ladies are non-player characters that clean rooms.
 */
public class CleaningLady extends Actor {

    /**
     * The names of the cleaning ladies.
     */
    private static final String[] CLEANING_LADY_NAMES = {"Általános Anna", "Balzsam Beáta", "Bútor Boglárka", "Csap Csilla", "Dísz Dóra", "Edény Edit", "Ecet Enikő", "Eszköz Eszter", "Gőz Gizella", "Izzító Ibolya", "Izzó Ilona", "Javító Judit", "Jutalom Julianna", "Keverő Katalin", "Kézi Klári", "Lé Luca", "Mopp Margit", "Moppos Mária", "Nedves Nikolett", "Porszívó Piroska", "Ragacs Renáta", "Rendszer Réka", "Szikra Szilvia", "Szivacs Zsuzsa", "Törölgető Teréz", "Villany Veronika", "Alapos Anikó", "Ápoló Andrea", "Zsír Zsófia"};
    /**
     * The names of the cleaning ladies in order.
     */
    private static final List<String> cleaningLadyNamesInOrder = new ArrayList<>(List.of(CLEANING_LADY_NAMES));
    /**
     * The index of the next cleaning lady name to be used.
     */
    private static int nextIndexOfCleaningLadyName = 0;

    static {
        // Shuffle the cleaning lady names.
        Collections.shuffle(cleaningLadyNamesInOrder);
    }

    /**
     * The name of the cleaning lady.
     */
    private final PurifierVisitor pv;

    /**
     * The purifier visitor.
     */
    public CleaningLady() {
        Logger.createObject(this);
        pv = new PurifierVisitor();
        this.name = cleaningLadyNamesInOrder.get(nextIndexOfCleaningLadyName);
        nextIndexOfCleaningLadyName = (nextIndexOfCleaningLadyName + 1) % cleaningLadyNamesInOrder.size();
    }

    /**
     * Visits the cleaning lady.
     *
     * @param v The visitor to visit the cleaning lady.
     */
    @Override
    public void VisitActor(ActorVisitor v) {
        Logger.invokeMethod(this, List.of());
        v.VisitCleaningLady(this);
        Logger.returnVoid();
    }

    /**
     * Gets the name of the cleaning lady.
     *
     * @return The name of the cleaning lady.
     */
    @Override
    public void Shock() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    /**
     * Gets the name of the cleaning lady.
     *
     * @return The name of the cleaning lady.
     */
    @Override
    public void DropOut() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    /**
     * Gets the name of the cleaning lady.
     *
     * @return The name of the cleaning lady.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        location.VisitEffects(pv);
        location.CleanRoom();

        Logger.returnVoid();
    }

    /**
     * Gets the name of the cleaning lady.
     *
     * @return The name of the cleaning lady.
     */
    @Override
    public void GetOut() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    /**
     * Gets the name of the cleaning lady.
     *
     * @return The name of the cleaning lady.
     */
    @Override
    public boolean UseDoor(Door d) {
        Logger.invokeMethod(this, Collections.singletonList(d));

        boolean wasSuccessful = super.UseDoor(d);
        if (wasSuccessful)
            new CopyOnWriteArrayList<>(location.GetActors()).forEach(Actor::GetOut);

        Logger.returnValue(wasSuccessful);
        return wasSuccessful;
    }

    /**
     * Gets the name of the cleaning lady.
     *
     * @return The name of the cleaning lady.
     */
    @Override
    public void NotifyStudentWin(SlideRule sr) {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }
}
