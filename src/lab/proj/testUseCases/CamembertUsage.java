package lab.proj.testUseCases;

import lab.proj.model.Camembert;
import lab.proj.model.Room;
import lab.proj.model.Student;

/**
 * A class representing the usage of a Camembert item in a specific use case.
 * This use case involves two teachers and one student.
 */
public class CamembertUsage implements TestUseCase {

    /**
     * Runs the use case, invoking the superclass method and activating the Camembert item.
     */
    @Override
    public void runUseCase() {
        Student s = new Student();
        Room r1 = new Room();
        Camembert cm = new Camembert();

        s.SetLocation(r1);

        boolean camembertPickedUp = cm.PickUp(s);
        if (!camembertPickedUp) {
            return;
        }
        cm.Activate();
    }
}
