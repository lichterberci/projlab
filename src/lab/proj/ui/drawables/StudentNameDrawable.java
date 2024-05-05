package lab.proj.ui.drawables;

import lab.proj.model.Student;
import lab.proj.controller.Application;

import javax.swing.*;
import java.awt.*;

public class StudentNameDrawable implements Drawable {
    private final JLabel label;
    private final Student student;

    public StudentNameDrawable(Student student) {
        this.student = student;
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setForeground(Application.DarkText);
        label.setBackground(Application.Light);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
    }

    @Override
    public void Draw(JComponent target) {
        label.setText(student != null ? student.GetName() : "");
        label.setPreferredSize(new Dimension(
                (int) (target.getMinimumSize().getWidth() * 0.9f),
                (int) (target.getMinimumSize().getHeight() * 0.8f)));
        label.setFont(label.getFont().deriveFont((float) (target.getMinimumSize().getHeight() * 0.3f)));
        target.add(label);
    }
}
