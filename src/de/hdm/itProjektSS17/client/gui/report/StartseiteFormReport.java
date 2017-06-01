package de.hdm.itProjektSS17.client.gui.report;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ShowcaseReport;

public class StartseiteFormReport extends ShowcaseReport{

		@Override
		protected String getHeadlineText() {
			return "Herzlich Willkommen auf Prokeko";
		}

		@Override
		protected void run() {
			
			VerticalPanel homePanel = new VerticalPanel();
			
			Label halloLabel = new Label("Finden Sie schnell und problemslos ein passendes Projekt.");
			
			homePanel.add(halloLabel);
			this.add(homePanel);

		}
	}

