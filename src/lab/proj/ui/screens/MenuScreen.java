package lab.proj.ui.screens;

import lab.proj.controller.Application;
import lab.proj.controller.GameManager;
import lab.proj.ui.components.StudentListComponent;
import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents the menu screen
 */
public class MenuScreen implements Screen {

    /**
     * The label
     */
    private final JLabel label;

    /**
     * The name field
     */
    private final JTextField nameField;


    /**
     * The add button
     */
    private final JButton addButton;

    /**
     * The student list
     */
    private final StudentListComponent studentList;

    /**
     * The play button
     */
    private final JButton playButton;

    /**
     * Creates a new menu screen
     */
    public MenuScreen() {
        label = new JLabel("Student Name: ");
        label.setForeground(Application.DarkText);
        label.setOpaque(false);
        nameField = new JTextField();
        nameField.setForeground(Application.DarkText);
        nameField.setBackground(Application.Light);
        addButton = new JButton("+");
        addButton.setForeground(Application.LightText);
        addButton.setBackground(Application.Dark);
        addButton.addActionListener(actionEvent -> {
            String name = nameField.getText();
            GameManager.GetInstance().CreateStudent(name);
            Application.GetInstance().RenderMenuScreen();
            nameField.setText("");
        });
        studentList = new StudentListComponent();
        playButton = new JButton("Play");
        playButton.addActionListener(actionEvent -> {
            GameManager.GetInstance().StartGame();
            Application.GetInstance().RenderGameScreen();
        });
        playButton.setBackground(Application.Dark);
        playButton.setForeground(Application.LightText);
    }

    /**
     * Sets the students
     *
     * @param students The students
     */
    public void SetStudents(List<Drawable> students) {
        studentList.SetDrawables(students);
    }

    /**
     * Renders the screen
     */
    @Override
    public void Render() {
        RenderInput();
        RenderComponent(studentList, 0.05, 0.15, 0.9, 0.7);
        RenderPlay();
    }

    /**
     * Renders the inputfield
     */
    private void RenderInput() {
        JPanel inputPanel = new JPanel();
        SizeJComponent(inputPanel, 0.05, 0.05, 0.9, 0.05);
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BorderLayout());
        addButton.setFont(addButton.getFont().deriveFont(inputPanel.getHeight() * 0.8f));
        nameField.setFont(nameField.getFont().deriveFont(inputPanel.getHeight() * 0.5f));
        label.setFont(label.getFont().deriveFont(inputPanel.getHeight() * 0.5f));
        inputPanel.add(addButton, BorderLayout.EAST);
        inputPanel.add(nameField, BorderLayout.CENTER);
        inputPanel.add(label, BorderLayout.WEST);
        Application.GetInstance().GetCanvas().add(inputPanel);
    }

    /**
     * Renders the play btn
     */
    private void RenderPlay() {
        SizeJComponent(playButton, 0.05, 0.9, 0.9, 0.05);
        playButton.setFont(playButton.getFont().deriveFont(playButton.getHeight() * 0.4f));
        Application.GetInstance().GetCanvas().add(playButton);
    }
}
