package lab.proj.model;

import java.util.*;

/**
 * A class representing a teacher actor in the game environment.
 * Teachers can perform actions such as stunning students and initiating dropout processes.
 */
public class Teacher extends Actor {

    /**
     * The list of teacher names to be used in the game.
     */
    private static final String[] TEACHER_LIST = new String[]{"Kiss Bálint", "Szirmay-Kalos László", "Rupp Tünde", "Arató Péter", "Balla Katalin", "Benyó Balázs", "Csébfalvi Balázs", "Ercsényi András", "Faragó Szabolcs", "Fördős Gergely", "Gincsainé Dr", "Goldschmidt Balázs", "Gyenes Zoltán", "Harmati István", "Hodosi Hajnalka", "Homlok József", "Horváth István", "Horváth Tamás", "Kacsó Ágota", "Kertész Zsolt", "Ketler Tamás", "Kiss Andrea", "Kondorosi Károly", "Kovács Kálmán", "Lantos Béla", "Ludmány Balázs", "Mohácsi Zsófia", "Pilászy György", "Rácz György", "Risztics Péter", "Salvi Péter", "Simon Balázs", "Somogyi Péter", "Szántó Mátyás", "Szeberényi Imre", "Szécsi László", "Szemenyei Márton", "Szlávecz Ákos", "Vaitkus Márton", "Vajta László", "Vámos Gábor", "Várady Tamás"};
    /**
     * The list of teacher names in a random order.
     */
    private static final List<String> teacherListInOrder = new ArrayList<>(List.of(TEACHER_LIST));
    /**
     * The index of the next teacher name to be used.
     */
    private static int nextTeacherNameIndex = 0;

    static {
        // Shuffle the teacher list to randomize the order of teacher names
        Collections.shuffle(teacherListInOrder);
    }

    /**
     * The name of the teacher.
     */
    private boolean stunned = false;
    /**
     * Indicates whether the teacher is stunned.
     */
    private Set<Actor> alreadyDroppedOut = new HashSet<>();

    /**
     * Creates a new teacher with a name from the teacher list.
     */
    public Teacher() {
        Logger.createObject(this);
        this.name = teacherListInOrder.get(nextTeacherNameIndex);
        nextTeacherNameIndex = (nextTeacherNameIndex + 1) % teacherListInOrder.size();
    }

    /**
     * Stuns the teacher.
     */
    public void Stun() {
        Logger.invokeMethod(this, List.of());

        stunned = true;

        Logger.returnVoid();
    }

    /**
     * Unstuns the teacher.
     */
    @Override
    public boolean IsBlocked() {
        Logger.invokeMethod(this, List.of());
        boolean isBlocked = super.IsBlocked() || stunned;
        Logger.returnValue(isBlocked);
        return isBlocked;
    }

    /**
     * Initiates the dropout process for all actors in the teacher's current location.
     * If an actor is droppable, the method calls its `DropOut` method.
     */
    public void DropOutAll() {
        Logger.invokeMethod(this, List.of());

        if (!IsBlocked())
            for (Actor actor : location.GetActors())
                if (!alreadyDroppedOut.contains(actor))
                    actor.DropOut();

        alreadyDroppedOut = new HashSet<>(location.GetActors());

        Logger.returnVoid();
    }

    /**
     * Accepts a visitor, allowing it to visit the teacher.
     *
     * @param v The visitor to be accepted.
     */
    @Override
    public void VisitActor(ActorVisitor v) {
        Logger.invokeMethod(this, Collections.singletonList(v));

        v.VisitTeacher(this);

        Logger.returnVoid();
    }

    /**
     * Initiates the dropout process for the teacher.
     * This method logs the attempt to drop out the teacher.
     */
    @Override
    public void DropOut() {
        Logger.invokeMethod(this, List.of());
        Logger.returnVoid();
    }

    /**
     * Reacts to being shocked.
     * This method logs the attempt to shock the teacher.
     */
    @Override
    public void NotifyStudentWin(SlideRule sr) {
        Logger.invokeMethod(this, List.of());

        sr.Drop();

        Logger.returnVoid();
    }

    /**
     * Reacts to being shocked.
     * This method logs the attempt to shock the teacher.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        DropOutAll();

        Logger.returnVoid();
    }
}
