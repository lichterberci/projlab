package lab.proj.ui;

import lab.proj.controller.GameManager;
import lab.proj.model.Student;
import lab.proj.ui.drawables.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuUI {
    private final int width = 800;
    private final int height = 600;
    private final JFrame frame;
    private final InputFieldComponent input;
    private final StudentNameListComponent studentNames;
    public MenuUI() {
        frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLayout(null);
        frame.setBackground(Color.decode("#f0f0f0"));
        frame.setVisible(true);
        input = new InputFieldComponent();
        studentNames = new StudentNameListComponent();
    }

    public void UpdateUI(List<Student> studentList) {
        frame.getContentPane().removeAll();
        input.Draw(frame);
        List<Drawable> studentDrawables = studentList.stream()
                .map(student -> (Drawable) new StudentNameDrawable(student))
                .toList();
        studentNames.SetStudents(studentDrawables);
        studentNames.Draw(frame);
        JButton playButton = new JButton("Play");
        playButton.addActionListener(actionEvent -> GameManager.GetInstance().StartGame());
        int topX = (int) (0.15f * frame.getContentPane().getWidth());
        int topY = (int) (0.85f * frame.getContentPane().getHeight());
        int width = (int) (0.7f * frame.getContentPane().getWidth());
        int height = (int) (0.1f * frame.getContentPane().getHeight());
        playButton.setBounds(topX, topY, width, height);
        frame.add(playButton);
        frame.revalidate();
        frame.repaint();
    }

    public void Close() {
        frame.setVisible(false);
        frame.dispose();
    }
}
