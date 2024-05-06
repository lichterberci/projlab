package lab.proj.ui.components;

import lab.proj.controller.Application;
import javax.swing.*;

public class StudentNameListComponent extends Component{
    @Override
    public void Draw(JComponent target) {
        target.setBackground(Application.Dark);
        target.setLayout(new BoxLayout(target, BoxLayout.Y_AXIS));

        AddDrawables(0.9, 0.15, target);
    }
}
