package lab.proj.controller;

import lab.proj.model.*;

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
        System.out.println("Win!");
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
        //currentActor = GetNextActorForTurn();
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
        currentActor = GetNextActorForTurn();
    }

    private Actor GetNextActorForTurn() {
        return turnCounter % 2 == 0 ? students.get(turnCounter / 2) : nonPlayerCharacters.get(turnCounter / 2);
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

        entities.add(result);

        return result;
    }
}
