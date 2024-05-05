package lab.proj.ui.screens;

import lab.proj.controller.GameManager;
import lab.proj.model.Student;
import lab.proj.controller.Application;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.components.InputFieldComponent;
import lab.proj.ui.components.StudentNameListComponent;
import lab.proj.ui.drawables.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuScreen implements Screen {
    private final InputFieldComponent input;
    private final StudentNameListComponent studentNames;

    public MenuScreen() {
        input = new InputFieldComponent();
        studentNames = new StudentNameListComponent();
    }

    public void SetStudents(List<StudentNameDrawable> students) {
        studentNames.SetStudents(students);
    }

    @Override
    public void Render() {
        JComponent canvas = Application.GetInstance().GetCanvas();
        canvas.removeAll();

        RenderInput(canvas);
        RenderStudents(canvas);
        RenderPlay(canvas);

        canvas.revalidate();
        canvas.repaint();
    }

    private void RenderInput(JComponent canvas) {
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds((int) (0.05f * canvas.getWidth()),
                (int) (0.05f * canvas.getHeight()),
                (int) (0.9f * canvas.getWidth()),
                (int) (0.05f * canvas.getHeight()));
        inputPanel.setOpaque(false);
        input.Draw(inputPanel);
        canvas.add(inputPanel);
    }

    private void RenderStudents(JComponent canvas) {
        JPanel studentsPanel = new JPanel();
        studentsPanel.setBounds((int) (0.05f * canvas.getWidth()),
                (int) (0.15f * canvas.getHeight()),
                (int) (0.9f * canvas.getWidth()),
                (int) (0.7f * canvas.getHeight()));
        studentNames.Draw(studentsPanel);

        JScrollPane studentsScrollPane = new JScrollPane(studentsPanel);
        studentsScrollPane.setBounds((int) (0.05f * canvas.getWidth()),
                (int) (0.15f * canvas.getHeight()),
                (int) (0.9f * canvas.getWidth()),
                (int) (0.7f * canvas.getHeight()));
        studentsScrollPane.setBorder(BorderFactory.createLineBorder(Application.Border));
        studentsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        studentsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        studentsScrollPane.setOpaque(false);
        canvas.add(studentsScrollPane);
    }

    private void RenderPlay(JComponent canvas) {
        JButton playButton = new JButton("Play");
        playButton.setBounds((int) (0.05f * canvas.getWidth()),
                (int) (0.9f * canvas.getHeight()),
                (int) (0.9f * canvas.getWidth()),
                (int) (0.05f * canvas.getHeight()));
        playButton.addActionListener(actionEvent -> {
            GameManager.GetInstance().StartGame();
            Application.GetInstance().RenderGameScreen();
        });
        playButton.setBackground(Application.Dark);
        playButton.setForeground(Application.LightText);
        playButton.setFont(playButton.getFont().deriveFont(playButton.getHeight() * 0.4f));
        canvas.add(playButton);
    }
}
