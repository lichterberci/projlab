package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.testUseCases.TwoTeachersOneStudent;
import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDefendWithCSE implements TestUseCase {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();
    @Override
    public void runUseCase() {
        Room r1 = new Room();
        Logger.createObject(IndentedDebugPrinter.MAIN, r1, "r1");

        Room r2 = new Room();
        Logger.createObject(IndentedDebugPrinter.MAIN, r2, "r2");

        Door d = new Door();
        Logger.createObject(IndentedDebugPrinter.MAIN, d, "d");
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, d, "SetRooms", List.of(r1, r2));
        d.SetRooms(r1, r2);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, d, "SetRooms", Optional.empty());

        Student s = new Student();
        Logger.createObject(IndentedDebugPrinter.MAIN, s, "s");
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "SetLocation", List.of(r1));
        s.SetLocation(r1);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "SetLocation", Optional.empty());

        CSE cse = new CSE();
        Logger.createObject(IndentedDebugPrinter.MAIN, cse, "cse");
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, cse, "PickUp", List.of(s));
        boolean csePickedUp = cse.PickUp(s);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, cse, "PickUp", Optional.of(csePickedUp));
        if (!csePickedUp) {
            return;
        }

        int answer = AskTheUser.number("Hány élete maradt a TVSZ-nek? (1 vagy 2)");
        if (answer == 2) {
            Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, cse, "SetLifeTime", List.of(2));
            cse.SetLifeTime(2);
            Logger.returnFromMethod(IndentedDebugPrinter.MAIN, cse, "PickUp", Optional.empty());
        } else if (answer == 1) {
            Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, cse, "SetLifeTime", List.of(1));
            cse.SetLifeTime(1);
            Logger.returnFromMethod(IndentedDebugPrinter.MAIN, cse, "PickUp", Optional.empty());
        }
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, cse, "Active", new ArrayList<>());
        cse.Activate();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, cse, "Active", Optional.empty());

        Teacher t1 = new Teacher();
        Logger.createObject(IndentedDebugPrinter.MAIN, t1, "t1");
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t1, "SetLocation", List.of(r2));
        t1.SetLocation(r2);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t1, "SetLocation", Optional.empty());

        Teacher t2 = new Teacher();
        Logger.createObject(IndentedDebugPrinter.MAIN, t2, "t2");
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t2, "SetLocation", List.of(r2));
        t2.SetLocation(r2);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t2, "SetLocation", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", List.of(d));
        boolean success = s.UseDoor(d);
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "UseDoor", Optional.of(success));
        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", List.of());
        s.TimePassed();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, s, "TimePassed", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", List.of());
        t1.DropOutAll();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t1, "DropOutAll", Optional.empty());

        Logger.invokeObjectMethod(IndentedDebugPrinter.MAIN, t2, "DropOutAll", List.of());
        t2.DropOutAll();
        Logger.returnFromMethod(IndentedDebugPrinter.MAIN, t2, "DropOutAll", Optional.empty());
    }
}