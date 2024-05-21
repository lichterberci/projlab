package lab.proj.controller;

import lab.proj.model.*;
import lab.proj.utils.Randomware;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class GameManager {

    private static GameManager instance;
    private final List<Room> rooms = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Actor> nonPlayerCharacters = new ArrayList<>();
    private boolean isRunning;
    private boolean isWon;
    private int turnCounter = 0;
    private Actor currentActor = null;

    private GameManager() {
        ResetGame();
    }

    public static GameManager GetInstance() {
        if (instance == null)
            instance = new GameManager();

        return instance;
    }

    public void StartGame() {
        GameSetup();
        isRunning = true;
        currentActor = GetNextActorForTurn(turnCounter);
        Application.GetInstance().RenderGameScreen();
    }

    public void Win() {
        isRunning = false;
        isWon = true;
        Application.GetInstance().RenderResultScreen();
    }

    public void Lose() {
        isRunning = false;
        isWon = false;
        Application.GetInstance().RenderResultScreen();
    }

    public void ResetGame() {
        isRunning = false;
        isWon = false;
        turnCounter = 0;
    }

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
            new Curse().SetLocation(room);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isWon() {
        return isWon;
    }

    public void DropOutStudent(Student student) {
        students.remove(student);
        if (students.isEmpty())
            Lose();
    }

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

    public Actor GetCurrentActor() {
        return currentActor;
    }

    public List<Actor> ActorsInOrder() {
        return IntStream.range(turnCounter, turnCounter + nonPlayerCharacters.size() * 2)
                .mapToObj(this::GetNextActorForTurn)
                .toList();
    }

    private Actor GetNextActorForTurn(int n) {
        return n % 2 == 0 ?
                students.get((n / 2) % students.size())
                : nonPlayerCharacters.get((n / 2) % nonPlayerCharacters.size());
    }

    public Room CreateRoom() {
        Room result = new Room();
        rooms.add(result);
        return result;
    }

    public void DestroyRoom(Room room) {
        rooms.remove(room);
    }

    public Door CreateDoor() {
        Door result = new Door();
        return result;
    }

    public Student CreateStudent(String name) {
        Student result = new Student(name);
        students.add(result);
        return result;
    }

    public CleaningLady CreateCleaningLady() {
        CleaningLady result = new CleaningLady();
        nonPlayerCharacters.add(result);
        return result;
    }

    public Teacher CreateTeacher() {
        Teacher result = new Teacher();
        nonPlayerCharacters.add(result);
        return result;
    }

    public List<Student> GetStudents() {
        return students;
    }
}
