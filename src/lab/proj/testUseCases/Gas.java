package lab.proj.testUseCases;

import lab.proj.model.*;

/**
 * A class representing the usage of a Gas item in a specific use case.
 * This use case involves a room, a student, a teacher, a mask, and a gas protection item.
 */
public class Gas implements TestUseCase {

    /**
     * Runs the use case, invoking the superclass method and activating the Gas item.
     */
    @Override
    public void runUseCase() {
        var room = new Room();
        var g = new GasPoisoning();
        var st = new Student();
        var mask = new Mask();
        var t1 = new Teacher();
        var gprot = new GasProtection(mask, 0);

        st.SetLocation(room);
        t1.SetLocation(room);
        boolean result = mask.PickUp(st);
        mask.Activate();
        g.SetLocation(room);
        st.TimePassed();
        room.TimePassed();
        t1.DropOutAll();
    }
}
