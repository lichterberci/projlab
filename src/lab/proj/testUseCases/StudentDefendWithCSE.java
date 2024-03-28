package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

public class StudentDefendWithCSE implements TestUseCase {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    @Override
    public void runUseCase() {
        Room r1 = new Room();
        Logger.createObject(r1, "r1");

        Room r2 = new Room();
        Logger.createObject(r2, "r2");

        Door d = new Door();
        Logger.createObject(d, "d");
        d.SetRooms(r1, r2);

        Student s = new Student();
        Logger.createObject(s, "s");
        s.SetLocation(r1);

        CSE cse = new CSE();
        Logger.createObject(cse, "cse");
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
        Logger.createObject(t1, "t1");
        t1.SetLocation(r2);

        Teacher t2 = new Teacher();
        Logger.createObject(t2, "t2");
        t2.SetLocation(r2);

        boolean success = s.UseDoor(d);
        s.TimePassed();

        t1.DropOutAll();
        t2.DropOutAll();
    }
}