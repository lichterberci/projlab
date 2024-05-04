package lab.proj.ui.components;

import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentNameListComponent implements UIComponent {

    private List<Drawable> students;
    @Override
    public void Draw(JComponent target) {
        int topX = (int) (0.15f * target.getWidth());
        int topY = (int) (0.2f * target.getHeight());
        int width = (int) (0.7f * target.getWidth());
        int height = (int) (0.6f * target.getHeight());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Drawable student : students) {
            JPanel studentPanel = new JPanel();

            studentPanel.setMinimumSize(new Dimension((int) (width * 0.9), (int) (height * 0.15)));
            student.Draw(studentPanel);

            studentPanel.setBackground(Color.decode("#808080"));

            panel.add(studentPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(topX, topY, width, height);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        target.add(scrollPane);
    }

    public void SetStudents(List<Drawable> students) {
        this.students = students;
    }
}
