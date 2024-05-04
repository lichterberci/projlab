package lab.proj.ui.components;

import lab.proj.controller.GameManager;
import lab.proj.controller.Application;
import lab.proj.ui.screens.MenuScreen;

import javax.swing.*;
import java.awt.*;

public class InputFieldComponent implements Component {
    private final JLabel label;
    private final JTextField nameField;
    private final JButton addButton;

    public InputFieldComponent() {
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
    }

    @Override
    public void Draw(JComponent target) {
        target.setLayout(new BorderLayout());
        addButton.setFont(addButton.getFont().deriveFont(target.getHeight() * 0.8f));
        nameField.setFont(nameField.getFont().deriveFont(target.getHeight() * 0.5f));
        label.setFont(label.getFont().deriveFont(target.getHeight() * 0.5f));
        target.add(addButton, BorderLayout.EAST);
        target.add(nameField, BorderLayout.CENTER);
        target.add(label, BorderLayout.WEST);
    }
}
