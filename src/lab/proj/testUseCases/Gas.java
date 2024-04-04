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

        room.AddActor(st);
        room.AddActor(t1);
        boolean result = mask.PickUp(st);
        mask.Activate();
        room.AddEffect(g);
        st.TimePassed();
        room.TimePassed();
        t1.DropOutAll();
    }
}
