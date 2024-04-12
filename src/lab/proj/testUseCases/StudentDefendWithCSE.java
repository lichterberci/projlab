package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.AskTheUser;
import lab.proj.utils.SequenceDiagramPrinter;

public class StudentDefendWithCSE implements TestUseCase {
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    @Override
    public void runUseCase() {
        Room r1 = new Room();
        Room r2 = new Room();
        Door d = new Door();
        d.SetRooms(r1, r2);

        Student s = new Student();
        s.SetLocation(r1);

        CSE cse = new CSE();
        boolean csePickedUp = cse.PickUp(s);
        if (!csePickedUp) {
            return;
        }

        int answer = AskTheUser.number("Hány élete maradt a TVSZ-nek? (1 vagy 2)");
        if (answer == 2) {
            cse.SetLifeTime(2);
        } else if (answer == 1) {
            cse.SetLifeTime(1);
        }
        cse.Activate();

        Teacher t1 = new Teacher();
        t1.SetLocation(r2);

        Teacher t2 = new Teacher();
        t2.SetLocation(r2);

        boolean success = s.UseDoor(d);
        s.TimePassed();

        t1.DropOutAll();
        t2.DropOutAll();
    }
}