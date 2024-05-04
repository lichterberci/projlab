package lab.proj.ui.drawables;

import lab.proj.model.Student;
import lab.proj.ui.Application;

import javax.swing.*;
import java.awt.*;

public class StudentNameDrawable implements Drawable {
    private final Student student;
    public StudentNameDrawable(Student student) { this.student = student; }
    @Override
    public void Draw(JComponent drawTarget) {
        JLabel label = new JLabel(student.GetName());
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setForeground(Application.DarkText);
        label.setBackground(Application.Light);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
        label.setAlignmentX(0.5f);
        label.setAlignmentY(0.5f);
        label.setPreferredSize(new Dimension((int) (drawTarget.getMinimumSize().getWidth() * 0.9f), (int) (drawTarget.getMinimumSize().getHeight() * 0.8f)));

        drawTarget.add(label);
    }
}
