package lab.proj.ui.components;

import lab.proj.controller.GameManager;
import lab.proj.ui.Application;
import lab.proj.ui.screens.MenuScreen;

import javax.swing.*;
import java.awt.*;

public class InputFieldComponent implements Component {
    @Override
    public void Draw(JComponent target) {
        int topX = (int) (0.05f * target.getWidth());
        int topY = (int) (0.05f * target.getHeight());
        int width = (int) (0.9f * target.getWidth());
        int height = (int) (0.05f * target.getHeight());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(topX, topY, width, height);
        panel.setOpaque(false);

        JButton addButton = new JButton("+");
        JTextField nameField = new JTextField();
        JLabel label = new JLabel("Student Name: ");
        addButton.addActionListener(actionEvent -> {
            String name = nameField.getText();
            GameManager.GetInstance().CreateStudent(name);
            MenuScreen.GetInstance().UpdateUI(GameManager.GetInstance().GetStudents());
            nameField.setText("");
        });
        addButton.setBackground(Application.Dark);
        addButton.setForeground(Application.LightText);

        nameField.setBackground(Application.Light);
        nameField.setForeground(Application.DarkText);

        label.setOpaque(false);
        label.setForeground(Application.DarkText);

        panel.add(addButton, BorderLayout.EAST);
        panel.add(nameField, BorderLayout.CENTER);
        panel.add(label, BorderLayout.WEST);

        target.add(panel);
    }
}
