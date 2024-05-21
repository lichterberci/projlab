package lab.proj.ui.components;

import javax.swing.*;

/**
 * Represents the status component
 */
public class StatusComponent extends Component {
    /**
     *  Creates a new status component
     * @param target The target component to draw on
     */
    @Override
    public void Draw(JComponent target) {
        JPanel panel = MakePanelInScroll(target, BorderFactory.createEmptyBorder(),
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(false);

        AddDrawables(0.4, 0.9, panel);
    }
}