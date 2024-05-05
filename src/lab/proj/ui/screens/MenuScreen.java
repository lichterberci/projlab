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
        inputPanel.setLayout(new BorderLayout());
        addButton.setFont(addButton.getFont().deriveFont(inputPanel.getHeight() * 0.8f));
        nameField.setFont(nameField.getFont().deriveFont(inputPanel.getHeight() * 0.5f));
        label.setFont(label.getFont().deriveFont(inputPanel.getHeight() * 0.5f));
        inputPanel.add(addButton, BorderLayout.EAST);
        inputPanel.add(nameField, BorderLayout.CENTER);
        inputPanel.add(label, BorderLayout.WEST);
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
        playButton.setBounds((int) (0.05f * canvas.getWidth()),
                (int) (0.9f * canvas.getHeight()),
                (int) (0.9f * canvas.getWidth()),
                (int) (0.05f * canvas.getHeight()));
        playButton.setFont(playButton.getFont().deriveFont(playButton.getHeight() * 0.4f));
        canvas.add(playButton);
    }
}
