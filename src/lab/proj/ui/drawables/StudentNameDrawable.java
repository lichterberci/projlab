package lab.proj.ui.drawables;

import lab.proj.model.Student;
import lab.proj.ui.Drawable;
import lab.proj.ui.StudentNameListComponent;

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
        label.setMinimumSize(new Dimension(30, 20));

        JPanel actorPanel = new JPanel();
        actorPanel.add(label);
        actorPanel.setBackground(Color.decode("#d0d0d0"));
        actorPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
        actorPanel.setAlignmentX(0.5f);
        actorPanel.setAlignmentY(0.5f);
        actorPanel.setPreferredSize(new Dimension((int) (drawTarget.getMinimumSize().getWidth() * 0.9f), (int) (drawTarget.getMinimumSize().getHeight() * 0.8f)));

        drawTarget.add(actorPanel);
    }
}
