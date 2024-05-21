package lab.proj.testUseCases;

import lab.proj.model.Towel;

/**
 * A class representing a specific use case with one teacher and one student.
 */
public class StudentStunsTeacher extends OneTeacherOneStudent {

    /**
     * Runs the use case, invoking the superclass method and activating the Towel item.
     */
    @Override
    public void runUseCase() {
        super.runUseCase();

        Towel t = new Towel();
        boolean success = t.PickUp(s);

        success = s.UseDoor(d);

        s.TimePassed();
    }
}
