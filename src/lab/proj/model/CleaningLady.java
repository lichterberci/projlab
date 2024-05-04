package lab.proj.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CleaningLady extends Actor {

    private static final String[] CLEANING_LADY_NAMES = {"Általános Anna", "Balzsam Beáta", "Bútor Boglárka", "Csap Csilla", "Dísz Dóra", "Edény Edit", "Ecet Enikő", "Eszköz Eszter", "Gőz Gizella", "Izzító Ibolya", "Izzó Ilona", "Javító Judit", "Jutalom Julianna", "Keverő Katalin", "Kézi Klári", "Lé Luca", "Mopp Margit", "Moppos Mária", "Nedves Nikolett", "Porszívó Piroska", "Ragacs Renáta", "Rendszer Réka", "Szikra Szilvia", "Szivacs Zsuzsa", "Törölgető Teréz", "Villany Veronika", "Alapos Anikó", "Ápoló Andrea", "Zsír Zsófia"};
    private static final List<String> cleaningLadyNamesInOrder = new ArrayList<>(List.of(CLEANING_LADY_NAMES));
    static {
        Collections.shuffle(cleaningLadyNamesInOrder);
    }
    private static int nextIndexOfCleaningLadyName = 0;
    private final PurifierVisitor pv;

    public CleaningLady() {
        Logger.createObject(this);
        pv = new PurifierVisitor();
        this.name = cleaningLadyNamesInOrder.get(nextIndexOfCleaningLadyName);
        nextIndexOfCleaningLadyName = (nextIndexOfCleaningLadyName + 1) % cleaningLadyNamesInOrder.size();
    }

    @Override
    public void VisitActor(ActorVisitor v) {
        Logger.invokeMethod(this, List.of());
        v.VisitCleaningLady(this);
        Logger.returnVoid();
    }

    @Override
    public void Shock() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    @Override
    public void DropOut() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        location.VisitEffects(pv);
        location.CleanRoom();

        Logger.returnVoid();
    }

    @Override
    public void GetOut() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    @Override
    public boolean UseDoor(Door d) {
        Logger.invokeMethod(this, Collections.singletonList(d));

        boolean wasSuccessful = super.UseDoor(d);
        if (wasSuccessful)
            new CopyOnWriteArrayList<>(location.GetActors()).forEach(Actor::GetOut);

        Logger.returnValue(wasSuccessful);
        return wasSuccessful;
    }

    @Override
    public void NotifyStudentWin(SlideRule sr) {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }
}
