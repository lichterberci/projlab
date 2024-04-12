package lab.proj.controller;

import lab.proj.model.Actor;
import lab.proj.model.Entity;
import lab.proj.model.Room;
import lab.proj.model.Student;

import java.util.List;
import java.util.Set;

public class GameManager {

    private static GameManager instance;

    private boolean isRunning;
    private boolean isWon;

    private Set<Room> rooms;
    private List<Student> students;
    private List<Actor> nonPlayerCharacters;
    private Set<Entity> entities;
    private int turnCounter;
    private Actor currentActor;

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
    }

    public void Win() {
        isRunning = false;
        isWon = true;
    }

    public void Lose() {
        isRunning = false;
        isWon = false;
    }

    public void Restart() {
        ResetGame();
    }

    private void ResetGame() {
        isRunning = false;
        isWon = false;
        turnCounter = 0;
        currentActor = GetNextActorForTurn();
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

    public void DropoutStudent(Student student) {
        students.remove(student);
    }

    public void EndTurn() {
        students.forEach(Student::TimePassed);
        nonPlayerCharacters.forEach(Actor::TimePassed);
        rooms.forEach(Room::TimePassed);

        turnCounter++;
        currentActor = GetNextActorForTurn();
    }

    private Actor GetNextActorForTurn() {
        return turnCounter % 2 == 0 ? students.get(turnCounter / 2) : nonPlayerCharacters.get(turnCounter / 2);
    }
}
