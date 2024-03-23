package lab.proj.model;

public class Teacher extends Actor {
    public void Stun() {
    }
    
    public void DropOutStudent(Student s) {
    }
    
    public void DropOutAll() {
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
}
