package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Teacher extends Actor {
    public void Stun() {
    }
    
    public void DropOutStudent(Student s) {
    }
    
    public void DropOutAll() {
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, location, "GetActors", new ArrayList<>());
        List<Actor> actors = location.GetActors();
        IndentedDebugPrinter.getInstance().returnFromMethod(this, location, "GetActors", Optional.of(actors));

        for (Actor a : actors) {
            IndentedDebugPrinter.getInstance().invokeObjectMethod(this, a, "DropOut", new ArrayList<>());
            a.DropOut();
            IndentedDebugPrinter.getInstance().returnFromMethod(this, a, "DropOut", Optional.empty());
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
