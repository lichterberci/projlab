package lab.proj.ui.components;

import lab.proj.controller.Application;
import lab.proj.ui.drawables.ActorTurnIndicatorDrawable;
import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ActorTurnIndicatorComponent implements  Component {
	private List<ActorTurnIndicatorDrawable> actors;
	@Override
	public void Draw(JComponent target) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Application.Dark);

		int topX = (int) (0.8f * target.getWidth());
		int topY = (int) (0.0f * target.getHeight());
		int width = (int) (0.2f * target.getWidth());
		int height = (int) (1.0f * target.getHeight());

		for (Drawable actor : actors) {
			JPanel actorPanel = new JPanel();
			actorPanel.setOpaque(false);
			actorPanel.setMinimumSize(new Dimension((int) (width * 0.9), (int) (height * 0.1)));
			actorPanel.setMinimumSize(new Dimension((int) (width * 0.9), (int) (height * 0.1)));
			actor.Draw(actorPanel);

			panel.add(actorPanel);
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(topX, topY, width, height);
		scrollPane.setBorder(BorderFactory.createLineBorder(Application.Border));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);

		target.add(scrollPane);
	}
	
	public void SetActors(List<ActorTurnIndicatorDrawable> actors) {
		this.actors = actors;
	}
}
