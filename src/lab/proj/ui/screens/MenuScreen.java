package lab.proj.ui.screens;

import lab.proj.controller.GameManager;
import lab.proj.controller.Application;
import lab.proj.ui.components.StudentNameListComponent;
import lab.proj.ui.drawables.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuScreen implements Screen {
    private final JLabel label;
    private final JTextField nameField;
    private final JButton addButton;
    private final StudentNameListComponent studentNames;
    private final JButton playButton;

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
        studentNames = new StudentNameListComponent();
        playButton = new JButton("Play");
        playButton.addActionListener(actionEvent -> {
            GameManager.GetInstance().StartGame();
            Application.GetInstance().RenderGameScreen();
        });
        playButton.setBackground(Application.Dark);
        playButton.setForeground(Application.LightText);
    }

    public void SetStudents(List<Drawable> students) {
        studentNames.SetDrawables(students);
    }

    @Override
    public void Render() {
        JComponent canvas = Application.GetInstance().GetCanvas();
        canvas.removeAll();

        RenderInput(canvas);
        RenderComponent(studentNames, canvas, 0.05, 0.15, 0.9, 0.7);
        RenderPlay(canvas);

        canvas.revalidate();
        canvas.repaint();
    }

    private void RenderInput(JComponent canvas) {
        JPanel inputPanel = new JPanel();
        SizeJComponent(inputPanel, canvas, 0.05, 0.05, 0.9, 0.05);
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BorderLayout());
        addButton.setFont(addButton.getFont().deriveFont(inputPanel.getHeight() * 0.8f));
        nameField.setFont(nameField.getFont().deriveFont(inputPanel.getHeight() * 0.5f));
        label.setFont(label.getFont().deriveFont(inputPanel.getHeight() * 0.5f));
        inputPanel.add(addButton, BorderLayout.EAST);
        inputPanel.add(nameField, BorderLayout.CENTER);
        inputPanel.add(label, BorderLayout.WEST);
        canvas.add(inputPanel);
    }

    private void RenderPlay(JComponent canvas) {
        SizeJComponent(playButton, canvas, 0.05, 0.9, 0.9, 0.05);
        playButton.setFont(playButton.getFont().deriveFont(playButton.getHeight() * 0.4f));
        canvas.add(playButton);
    }
}
