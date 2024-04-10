package lab.proj.controller;

import lab.proj.model.Actor;
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

    private GameManager() {
        ResetGame();
    }

    public static GameManager getInstance() {
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
    }

    private void CalculateLayout() {

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
}
