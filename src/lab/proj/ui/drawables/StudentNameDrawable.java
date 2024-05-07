package lab.proj.ui.drawables;

import lab.proj.model.Student;
import lab.proj.controller.Application;

import javax.swing.*;

public class StudentNameDrawable extends Drawable {
    private final JLabel label = new JLabel();
    private final Student student;

    public StudentNameDrawable(Student student) {
        this.student = student;
        SetDefaults(label, Application.DarkText, Application.Light);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
    }

    @Override
    public void Draw(JComponent target) {
        if (student != null) {
            label.setText(student.GetName());
        }
        SetRelativeSizes(label, target, 0.3);
        target.add(label);
    }
}
