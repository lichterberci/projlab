package lab.proj.ui.drawables;

import lab.proj.controller.Application;
import lab.proj.model.Student;

import javax.swing.*;

/**
 * Represents the student name drawable
 */
public class StudentNameDrawable extends Drawable {
    /**
     * The label
     */
    private final JLabel label = new JLabel();
    /**
     * The student
     */
    private final Student student;

    /**
     * Creates a new student name drawable
     *
     * @param student The student
     */
    public StudentNameDrawable(Student student) {
        this.student = student;
        SetDefaults(label, Application.DarkText, Application.Light);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Application.Border));
    }

    /**
     * Creates a new student name drawable
     */
    @Override
    public void Draw(JComponent target) {
        if (student != null) {
            label.setText(student.GetName());
        }
        SetRelativeSizes(label, target, 0.3);
        target.add(label);
    }
}
