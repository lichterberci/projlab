package lab.proj.ui.components;

import lab.proj.controller.Application;
import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;

public class StudentNameListComponent extends Component{
    @Override
    public void Draw(JComponent target) {
        target.setBackground(Application.Dark);
        target.setLayout(new BoxLayout(target, BoxLayout.Y_AXIS));

        for (Drawable student : drawables) {
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
}
