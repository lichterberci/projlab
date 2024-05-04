package lab.proj.ui.screens;

import lab.proj.controller.GameManager;
import lab.proj.model.Student;
import lab.proj.ui.Application;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.components.InputFieldComponent;
import lab.proj.ui.components.StudentNameListComponent;
import lab.proj.ui.drawables.*;

import javax.swing.*;
import java.util.List;

public class MenuScreen {
    private final JComponent canvas;
    private final InputFieldComponent input;
    private final StudentNameListComponent studentNames;

    private static MenuScreen instance;
    public static MenuScreen GetInstance() {
        if (instance == null)
            instance = new MenuScreen();
        return instance;
    }

    private MenuScreen() {
        this.canvas = Application.GetInstance().GetCanvas();
        input = new InputFieldComponent();
        studentNames = new StudentNameListComponent();
    }

    public void UpdateUI(List<Student> studentList) {
        canvas.removeAll();

        input.Draw(canvas);

        List<Drawable> studentDrawables = studentList.stream()
                .map(student -> (Drawable) new StudentNameDrawable(student))
                .toList();
        studentNames.SetStudents(studentDrawables);
        studentNames.Draw(canvas);

        JButton playButton = new JButton("Play");

        playButton.addActionListener(actionEvent -> {
	        GameManager.GetInstance().StartGame();
            Application.GetInstance().NavigateToGame();
        });

        int topX = (int) (0.15f * canvas.getWidth());
        int topY = (int) (0.85f * canvas.getHeight());
        int width = (int) (0.7f * canvas.getWidth());
        int height = (int) (0.1f * canvas.getHeight());

        playButton.setBounds(topX, topY, width, height);

        canvas.add(playButton);
        canvas.revalidate();
        canvas.repaint();
    }
}
