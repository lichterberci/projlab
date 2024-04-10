package lab.proj.controller;

import lab.proj.model.Entity;

import java.util.Collections;
import java.util.Set;

public class GameManager {

	private static GameManager instance;

	private boolean isRunning;
	private boolean isWon;
	private Set<Entity> entities;

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
		entities = Collections.emptySet();
	}
}
