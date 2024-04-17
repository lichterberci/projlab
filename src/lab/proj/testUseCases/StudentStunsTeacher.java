package lab.proj.testUseCases;

import lab.proj.model.Towel;

public class StudentStunsTeacher extends OneTeacherOneStudent {


    @Override
    public void runUseCase() {
        super.runUseCase();

        Towel t = new Towel();
        boolean success = t.PickUp(s);

        success = s.UseDoor(d);

        s.TimePassed();
    }
}
