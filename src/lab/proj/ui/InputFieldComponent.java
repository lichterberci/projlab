package lab.proj.ui;

import lab.proj.controller.GameManager;

import javax.swing.*;
import java.awt.*;

public class InputFieldComponent implements UIComponent {

    @Override
    public void Draw(JFrame target) {
        int topX = (int) (0.15f * target.getContentPane().getWidth());
        int topY = (int) (0.1f * target.getContentPane().getHeight());
        int width = (int) (0.70f * target.getContentPane().getWidth());
        int height = (int) (0.05f * target.getContentPane().getHeight());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(topX, topY, width, height);

        JButton addButton = new JButton("+");
        JTextField nameField = new JTextField();

        addButton.addActionListener(actionEvent -> {
            String name = nameField.getText(); // TODO: Set name of Student
            GameManager.GetInstance().CreateStudent(name);
            nameField.setText("");
        });

        panel.add(addButton, BorderLayout.EAST);
        panel.add(nameField, BorderLayout.CENTER);
        panel.add(new JLabel("Student Name: "), BorderLayout.WEST);

        target.add(panel);
    }
}
