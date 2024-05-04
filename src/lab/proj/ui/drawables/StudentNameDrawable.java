package lab.proj.ui.drawables;

import lab.proj.model.Student;
import lab.proj.ui.Application;

import javax.swing.*;
import java.awt.*;

public class StudentNameDrawable implements Drawable {
    private final Student student;
    public StudentNameDrawable(Student student) { this.student = student; }
    @Override
    public void Draw(JComponent target) {
        JLabel label = new JLabel(student.GetName());
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setForeground(Application.DarkText);
        label.setBackground(Application.Light);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        label.setPreferredSize(new Dimension((int) (target.getMinimumSize().getWidth() * 0.9f), (int) (target.getMinimumSize().getHeight() * 0.8f)));

        target.add(label);
    }
}
