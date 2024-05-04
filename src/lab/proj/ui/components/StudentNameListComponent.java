package lab.proj.ui.components;

import lab.proj.ui.Application;
import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentNameListComponent implements Component{
    private List<Drawable> students;

    @Override
    public void Draw(JComponent target) {
        int topX = (int) (0.05f * target.getWidth());
        int topY = (int) (0.15f * target.getHeight());
        int width = (int) (0.9f * target.getWidth());
        int height = (int) (0.7f * target.getHeight());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Application.Dark);

        for (Drawable student : students) {
            JPanel studentPanel = new JPanel();
            studentPanel.setOpaque(false);
            studentPanel.setMinimumSize(new Dimension((int) (width * 0.9), (int) (height * 0.15)));
            studentPanel.setMaximumSize(new Dimension((int) (width * 0.9), (int) (height * 0.15)));
            student.Draw(studentPanel);

            panel.add(studentPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(topX, topY, width, height);
        scrollPane.setBorder(BorderFactory.createLineBorder(Application.Border));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);

        target.add(scrollPane);
    }

    public void SetStudents(List<Drawable> students) {
        this.students = students;
    }
}
