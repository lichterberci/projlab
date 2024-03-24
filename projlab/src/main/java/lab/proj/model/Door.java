package lab.proj.model;

import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Door implements Entity {
    private static final IndentedDebugPrinter Logger = IndentedDebugPrinter.getInstance();

    private boolean hidden;
    private Room r2;
    private Room r1;

    public void Hide() {
        hidden = true;
    }

    public void Show() {
        hidden = false;
    }

    public boolean GoThrough(Room r, Actor a) {
        boolean doorIsHidden = AskTheUser.decision("Az ajtó el van tüntetve?");

        if (doorIsHidden)
            return false;


        // o: other room (i.e. the one that is not equal to r)
        Room o = Arrays.stream(GetRooms())
                .filter(candidate -> candidate != r)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Door connects to the same room on both sides!"));

        Logger.invokeObjectMethod(this, o, "StepIn", List.of(a));
        boolean wasSuccessful = o.StepIn(a);
        Logger.returnFromMethod(this, o, "StepIn", Optional.of(wasSuccessful));

        return wasSuccessful;
    }

    public void SetRooms(Room r1, Room r2) {
        this.r1 = r1;
        this.r2 = r2;

        Logger.invokeObjectMethod(this, r1, "AddDoor", List.of(this));
        r1.AddDoor(this);
        Logger.returnFromMethod(this, r1, "AddDoor", Optional.empty());

        Logger.invokeObjectMethod(this, r2, "AddDoor", List.of(this));
        r2.AddDoor(this);
        Logger.returnFromMethod(this, r2, "AddDoor", Optional.empty());
    }

    public Room[] GetRooms() {
        return new Room[]{r1, r2};
    }

    public void ChangeRoom(Room r1, Room r2) {
    }

    @Override
    public void TimePassed() {

    }
}
