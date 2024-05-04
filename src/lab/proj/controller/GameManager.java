package lab.proj.controller;

import lab.proj.model.*;
import lab.proj.ui.Application;
import lab.proj.ui.screens.GameScreen;
import lab.proj.ui.screens.ResultScreen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class GameManager {

    private static GameManager instance;
    private final Set<Room> rooms = new HashSet<>();
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
        isRunning = true;
        EndTurn();
        GameScreen.GetInstance().UpdateUI(ActorsInOrder(),
                currentActor.GetLocation(),
                (Student)currentActor);
    }

    public void Win() {
        isRunning = false;
        isWon = true;
        Application.GetInstance().NavigateToResult();
        ResultScreen.GetInstance().SetResult("Students win!");
    }

    public void Lose() {
        isRunning = false;
        isWon = false;
        Application.GetInstance().NavigateToResult();
        ResultScreen.GetInstance().SetResult("Teachers win!");
    }

    public void Restart() {
        ResetGame();
    }

    public void ResetGame() {
        isRunning = false;
        isWon = false;
        turnCounter = 0;
//        currentActor = GetNextActorForTurn(turnCounter);
    }

    private void CalculateLayout() {
        // TODO: set up rooms
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isWon() {
        return isWon;
    }

    public void DropOutStudent(Student student) {
        students.remove(student);
    }

    public void EndTurn() {
        students.forEach(Student::TimePassed);
        rooms.forEach(Room::TimePassed);
        nonPlayerCharacters.forEach(Actor::TimePassed);

        turnCounter++;
        currentActor = GetNextActorForTurn(turnCounter);

        if (turnCounter % 2 == 0) {
            // only draw UI if it is the turn of a student
            GameScreen.GetInstance().UpdateUI(ActorsInOrder(),
                    currentActor.GetLocation(),
                    (Student)currentActor);

        } else {
            // TODO: implement AI logic
            EndTurn();
        }
    }

    private List<Actor> ActorsInOrder() {
        return IntStream.range(turnCounter, turnCounter + students.size() + nonPlayerCharacters.size())
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

//        entities.add(result);

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
