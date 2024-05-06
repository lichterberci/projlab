package lab.proj.ui.components;

import lab.proj.controller.Application;
import javax.swing.*;

public class DoorsComponent extends Component {
    @Override
    public void Draw(JComponent target) {
        target.setLayout(new BoxLayout(target, BoxLayout.X_AXIS));
        target.setBackground(Application.Dark);

        AddDrawables(0.25, 0.9, target);
    }
}
