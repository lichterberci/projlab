package lab.proj.ui.components;

import lab.proj.controller.Application;
import javax.swing.*;

public class DoorsComponent extends Component {
    @Override
    public void Draw(JComponent target) {
        JPanel panel = MakePanelInScroll(target, BorderFactory.createLineBorder(Application.Border),
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(Application.Dark);

        AddDrawables(0.25, 0.9, panel);
    }
}
