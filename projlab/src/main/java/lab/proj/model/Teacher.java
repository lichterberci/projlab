package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Teacher extends Actor {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    public void Stun() {
    }

    public void DropOutStudent(Student s) {
    }

    public void DropOutAll() {
        Logger.invokeObjectMethod(this, location, "GetActors", List.of());
        List<Actor> actorsInTheRoom = location.GetActors();
        Logger.returnFromMethod(this, location, "GetActors", Optional.of(actorsInTheRoom));

        for (Actor actor : actorsInTheRoom) {
            Logger.invokeObjectMethod(this, actor, "DropOut", List.of());
            actor.DropOut();
            Logger.returnFromMethod(this, actor, "DropOut", Optional.empty());
        }
    }

    @Override
    public void TimePassed() {

    }

    @Override
    public void VisitActor(ActorVisitor v) {
        v.VisitTeacher(this);
    }

    @Override
    public void Shock() {

	}

    @Override
    public void DropOut() {
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, this, "DropOut", new ArrayList<>());
        IndentedDebugPrinter.getInstance().returnFromMethod(this, this, "DropOut", Optional.empty());
    }
}
