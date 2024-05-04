package lab.proj.ui.components;

import lab.proj.controller.GameManager;
import lab.proj.ui.screens.MenuScreen;

import javax.swing.*;
import java.awt.*;

public class InputFieldComponent implements UIComponent {

    @Override
    public void Draw(JComponent target) {
        int topX = (int) (0.15f * target.getWidth());
        int topY = (int) (0.1f * target.getHeight());
        int width = (int) (0.70f * target.getWidth());
        int height = (int) (0.05f * target.getHeight());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(topX, topY, width, height);

        JButton addButton = new JButton("+");
        JTextField nameField = new JTextField();

        addButton.addActionListener(actionEvent -> {
            String name = nameField.getText();
            GameManager.GetInstance().CreateStudent(name);
            MenuScreen.GetInstance().UpdateUI(GameManager.GetInstance().GetStudents());
            nameField.setText("");
        });

        panel.add(addButton, BorderLayout.EAST);
        panel.add(nameField, BorderLayout.CENTER);
        panel.add(new JLabel("Student Name: "), BorderLayout.WEST);

        target.add(panel);
    }
}
