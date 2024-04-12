package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.SequenceDiagramPrinter;

public class Gas implements TestUseCase {

    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

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
