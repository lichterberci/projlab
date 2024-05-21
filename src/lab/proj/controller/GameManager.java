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
                CreateTeacher().SetLocation(rooms.get(5));
            else
                CreateCleaningLady().SetLocation(rooms.get(4));
            i = (i + 1) % 4;
        }
    }

    private void CalculateLayout() {
        Room r1 = CreateRoom();
        Room r2 = CreateRoom();
        Room r3 = CreateRoom();
        Room r4 = CreateRoom();
        Room r5 = CreateRoom();
        Room r6 = CreateRoom();

        CreateDoor().SetRooms(r1, r2);
        CreateDoor().SetRooms(r2, r3);
        CreateDoor().SetRooms(r3, r6);
        CreateDoor().SetRooms(r6, r5);
        CreateDoor().SetRooms(r5, r4);
        CreateDoor().SetRooms(r4, r1);
        Door d = CreateDoor();
        d.SetRooms(r2, r5);
        d.SetOneWay();
        new Curse().SetLocation(r2);
        new SlideRule().SetLocation(r3);
//        new CSE().SetLocation(r1);
        new Camembert().SetLocation(r1);
//        new Mask().SetLocation(r1);
        new Towel().SetLocation(r1);
        new Purifier().SetLocation(r1);
        var tr1 = new Transistor();
        var tr2 = new Transistor();
        tr1.PairWith(tr2);
        tr1.SetLocation(r1);
        tr2.SetLocation(r1);
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
