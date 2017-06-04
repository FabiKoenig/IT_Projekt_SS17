package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.Showcase;

public class StartseiteForm extends VerticalPanel{


	public StartseiteForm() {
		
		VerticalPanel homePanel = new VerticalPanel();
		VerticalPanel inputPanel = new VerticalPanel();
		
		Label welcomeLabel = new Label("Herzlich Willkommen auf Prokeko");
		Label input1Label = new Label("Es kommt nicht auf die Größe an!");
		Label input2Label = new Label("- Wir unterstützen Sie auch bei kleinen Projekten.");
		Label input3Label = new Label("Über den Projekt Locator finden Sie schnell und bequem auf Sie passende Stellenausschreibungen.");
		Label input4Label = new Label("Zudem können Sie dort den Projektmarktplatz auswählen, auf dem Sie agieren möchten.");
		Label input5Label = new Label("Über den Menüpunkt 'Meine Aktivitäten' finden Sie eigene Projekte, Bewerbungen und Ausschreibungen.");
		Label input6Label = new Label("Legen Sie nun los, es warten unzählige neue Herausforderungen auf Sie!");
		
		welcomeLabel.setStylePrimaryName("willkommen_label");
		input1Label.setStylePrimaryName("startseite_label");
		
		homePanel.add(input1Label);
		homePanel.add(input2Label);
		inputPanel.add(input3Label);
		inputPanel.add(input4Label);
		inputPanel.add(input5Label);
		inputPanel.add(input6Label);
		
		inputPanel.setSpacing(8);
		homePanel.setSpacing(10);
		this.setSpacing(8);
		this.add(welcomeLabel);
		this.add(homePanel);
		this.add(inputPanel);

	}
}
