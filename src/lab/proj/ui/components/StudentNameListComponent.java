package lab.proj.ui.components;

import lab.proj.controller.Application;
import lab.proj.ui.drawables.Drawable;
import lab.proj.ui.drawables.StudentNameDrawable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentNameListComponent implements Component{
    private List<StudentNameDrawable> students;

    @Override
    public void Draw(JComponent target) {
        target.setBackground(Application.Dark);
        target.setLayout(new BoxLayout(target, BoxLayout.Y_AXIS));

        for (StudentNameDrawable student : students) {
            Dimension size = new Dimension(
                    (int) (target.getWidth() * 0.9),
                    (int) (target.getHeight() * 0.15));
            JPanel studentPanel = new JPanel();
            studentPanel.setMinimumSize(size);
            studentPanel.setMaximumSize(size);
            studentPanel.setOpaque(false);
            student.Draw(studentPanel);

            target.add(studentPanel);
        }
    }

    public void SetStudents(List<StudentNameDrawable> students) {
        this.students = students;
    }
}
