package lab.proj.controller;

import lab.proj.model.*;
import lab.proj.utils.Randomware;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * Represents the game manager
 */
public class GameManager {

    /**
     * The instance of the game manager
     */
    private static GameManager instance;
    /**
     * The rooms
     */
    private final List<Room> rooms = new ArrayList<>();
    /**
     * The students
     */
    private final List<Student> students = new ArrayList<>();
    /**
     * The non-player characters
     */
    private final List<Actor> nonPlayerCharacters = new ArrayList<>();
    /**
     * Whether the game is running
     */
    private boolean isRunning;
    /**
     * Whether the game is won
     */
    private boolean isWon;
    /**
     * The turn counter
     */
    private int turnCounter = 0;
    /**
     * The current actor
     */
    private Actor currentActor = null;

    /**
     * Creates a new game manager
     */
    private GameManager() {
        ResetGame();
    }

    /**
     * Gets the instance of the game manager
     *
     * @return the instance of the game manager
     */
    public static GameManager GetInstance() {
        if (instance == null)
            instance = new GameManager();

        return instance;
    }

    /**
     * Starts the game
     */
    public void StartGame() {
        GameSetup();
        isRunning = true;
        currentActor = GetNextActorForTurn(turnCounter);
        Application.GetInstance().RenderGameScreen();
    }

    /**
     * Wins the game
     */
    public void Win() {
        isRunning = false;
        isWon = true;
        Application.GetInstance().RenderResultScreen();
    }

    /**
     * Loses the game
     */
    public void Lose() {
        isRunning = false;
        isWon = false;
        Application.GetInstance().RenderResultScreen();
    }

    /**
     * Resets the game
     */
    public void ResetGame() {
        isRunning = false;
        isWon = false;
        turnCounter = 0;
    }

    /**
     * Sets up the game
     */
    private void GameSetup() {
        CalculateLayout();
        int i = 0;
        for (Student student : students) {
            student.SetLocation(rooms.get(0));
            if (i < 3)
                CreateTeacher().SetLocation(rooms.get(rooms.size() - 1));
            else
                CreateCleaningLady().SetLocation(rooms.get(rooms.size() - 2));
            i = (i + 1) % 4;
        }
    }

    /**
     * Calculates the layout of the game
     */
    private void CalculateLayout() {

        final int numRooms = Randomware.Number(7, 20);
        rooms.clear();
        for (int i = 0; i < numRooms; i++) {
            var room = CreateRoom();
            room.SetCapacity(Randomware.Number(3, 5));
        }

        // link doors
        for (int i = 0; i < numRooms - 1; i++) {
            var door = CreateDoor();
            door.SetTwoWay();
            door.SetRooms(rooms.get(i), rooms.get(i + 1));
        }

        // random doors
        for (int i = 0; i < numRooms; i++) {
            Room room = rooms.get(i);
            int numDoors = Randomware.Number(1, 3);
            for (int j = 0; j < numDoors; j++) {
                Room otherRoom = Randomware.Choice(rooms);
                if (Math.abs(rooms.indexOf(otherRoom) - i) > 1
                        && room.GetDoors().stream().noneMatch(
                                d -> d.GetRooms().stream().anyMatch(r -> r == otherRoom))) {
                    var door = CreateDoor();
                    door.SetRooms(room, otherRoom);
                    door.SetOneWay();
                }
            }
        }

        // random items
        for (int i = 0; i < numRooms; i++) {
            Room room = rooms.get(i);
            int numItems = 1; // easily could be set to a higher number
            for (int j = 0; j < numItems; j++) {
                final int indexOfItem = Randomware.Number(0, 5);
                Item item = switch (indexOfItem) {
                    case 0 -> new Camembert();
                    case 1 -> new Mask();
                    case 2 -> new Purifier();
                    case 3 -> new Towel();
                    case 4 -> new BeerMug();
					case 5 -> new CSE();
	                default -> throw new IllegalStateException("Unexpected value: " + indexOfItem);
                };
                item.SetLocation(room);
                if (Randomware.Decision()) {
                    item.SetFake();
                }
            }
        }

        // place slideRule
        int slideRuleRoomIndex = Randomware.Number(1, numRooms - 1);
        rooms.get(slideRuleRoomIndex).GetItemsOnTheFloor().add(new SlideRule());

        // place transistors in pairs
        final int numTransistors = Randomware.Number(1, 2);
        for (int i = 0; i < numTransistors; i++) {
            Room room = rooms.get(Randomware.Number(1, numRooms - 1));
            Transistor tr1 = new Transistor();
            Transistor tr2 = new Transistor();
            tr1.PairWith(tr2);
            tr1.SetLocation(room);
            tr2.SetLocation(room);
        }

        // random curses
        final int numCursedRooms = Randomware.Number(1, 2);
        for (int i = 0; i < numCursedRooms; i++) {
            Room room = Randomware.Choice(rooms);
            if (!Objects.requireNonNull(room).GetEffects().isEmpty())
                continue;
            new Curse().SetLocation(room);
        }
    }

    /**
     * Gets whether the game is running
     *
     * @return whether the game is running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Gets whether the game is won
     *
     * @return whether the game is won
     */
    public boolean isWon() {
        return isWon;
    }

    /**
     * Drops out a student
     *
     * @param student the student to drop out
     */
    public void DropOutStudent(Student student) {
        students.remove(student);
        if (students.isEmpty())
            Lose();
    }

    /**
     * Ends the turn
     */
    public void EndTurn() {
        students.forEach(Student::TimePassed);
        new CopyOnWriteArrayList<>(rooms).forEach(Room::TimePassed);
        nonPlayerCharacters.forEach(Actor::TimePassed);

        if (!isRunning)
            return;

        turnCounter++;
        currentActor = GetNextActorForTurn(turnCounter);

        if (turnCounter % 2 == 0 && !students.isEmpty()) {
            if (currentActor.IsBlocked())
                EndTurn();
            // only draw UI if it is the turn of a student
            Application.GetInstance().RenderGameScreen();
        } else {
            if (!currentActor.GetLocation().GetItemsOnTheFloor().isEmpty() && Randomware.Decision()) {
                currentActor.CollectItem(Randomware.Choice(currentActor.GetLocation().GetItemsOnTheFloor()));
            } else {
                var usableDoors = currentActor
                        .GetLocation()
                        .GetDoors()
                        .stream()
                        .filter(door -> door.Usable(currentActor))
                        .toList();
                if (!usableDoors.isEmpty() && Randomware.Decision())
                    currentActor.UseDoor(Randomware.Choice(usableDoors));
            }
            EndTurn();
        }
    }

    /**
     * Gets the current actor
     *
     * @return the current actor
     */
    public Actor GetCurrentActor() {
        return currentActor;
    }

    /**
     * Gets the actors in order
     *
     * @return the actors in order
     */
    public List<Actor> ActorsInOrder() {
        return IntStream.range(turnCounter, turnCounter + nonPlayerCharacters.size() * 2)
                .mapToObj(this::GetNextActorForTurn)
                .toList();
    }

    /**
     * Gets the next actor for turn
     *
     * @param n the turn number
     * @return the next actor for turn
     */
    private Actor GetNextActorForTurn(int n) {
        return n % 2 == 0 ?
                students.get((n / 2) % students.size())
                : nonPlayerCharacters.get((n / 2) % nonPlayerCharacters.size());
    }

    /**
     * Creates a room
     *
     * @return the room
     */
    public Room CreateRoom() {
        Room result = new Room();
        rooms.add(result);
        return result;
    }

    /**
     * Destroys a room
     *
     * @param room the room to destroy
     */
    public void DestroyRoom(Room room) {
        rooms.remove(room);
    }

    /**
     * Creates a door
     *
     * @return the door
     */
    public Door CreateDoor() {
        Door result = new Door();
        return result;
    }

    /**
     * Creates a student
     *
     * @param name the name of the student
     * @return the student
     */
    public Student CreateStudent(String name) {
        Student result = new Student(name);
        students.add(result);
        return result;
    }

    /**
     * Creates a cleaning lady
     *
     * @return the cleaning lady
     */
    public CleaningLady CreateCleaningLady() {
        CleaningLady result = new CleaningLady();
        nonPlayerCharacters.add(result);
        return result;
    }

    /**
     * Creates a teacher
     *
     * @return the teacher
     */
    public Teacher CreateTeacher() {
        Teacher result = new Teacher();
        nonPlayerCharacters.add(result);
        return result;
    }

    /**
     * Gets the students
     *
     * @return the students
     */
    public List<Student> GetStudents() {
        return students;
    }
}
