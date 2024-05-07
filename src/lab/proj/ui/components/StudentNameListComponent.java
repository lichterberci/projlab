package lab.proj.ui.components;

import lab.proj.controller.Application;
import javax.swing.*;

public class StudentNameListComponent extends Component{
    @Override
    public void Draw(JComponent target) {
        JPanel panel = MakePanelInScroll(target, BorderFactory.createLineBorder(Application.Border),
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.setBackground(Application.Dark);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        AddDrawables(0.9, 0.15, panel);
    }
}
