package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DialogBoxTeamErstellen extends DialogBox {

	public DialogBoxTeamErstellen() {

		VerticalPanel vp = new VerticalPanel();
		this.setText("Team erstellen...");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		vp.setPixelSize(400, 600);
		vp.setSpacing(10);
		vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Button ok = new Button("OK");
		Button abbrechen = new Button("Abbrechen");
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//TODO Auto-generated method stub
				hide();
			}
		});
		
		
		HorizontalPanel hp1 = new HorizontalPanel();
		Label lbl_teamname = new Label("Team: ");
		TextBox txt_teamname = new TextBox();
		txt_teamname.getElement().setPropertyString("placeholder", "Team");
		hp1.add(lbl_teamname);
		hp1.add(txt_teamname);
		vp.add(hp1);

		HorizontalPanel hp2 = new HorizontalPanel();
		Label lbl_strasse = new Label("Straße: ");
		TextBox txt_strasse = new TextBox();
		txt_strasse.getElement().setPropertyString("placeholder", "Straße");
		hp2.add(lbl_strasse);
		hp2.add(txt_strasse);
		vp.add(hp2);
		
		HorizontalPanel hp3 = new HorizontalPanel();
		Label lbl_hausnummer = new Label("Hausnummer: ");
		TextBox txt_hausnummer = new TextBox();
		txt_hausnummer.getElement().setPropertyString("placeholder", "Hausnummer");
		hp3.add(lbl_hausnummer);
		hp3.add(txt_hausnummer);
		vp.add(hp3);
		
		HorizontalPanel hp4 = new HorizontalPanel();
		Label lbl_ort = new Label("Ort: ");
		TextBox txt_ort = new TextBox();
		txt_ort.getElement().setPropertyString("placeholder", "Ort");
		hp4.add(lbl_ort);
		hp4.add(txt_ort);
		vp.add(hp4);
		
		HorizontalPanel hp5 = new HorizontalPanel();
		Label lbl_unternehmen = new Label("Unternehmen: ");
		TextBox txt_unternehmen = new TextBox();
		txt_unternehmen.getElement().setPropertyString("placeholder", "Unternehmen");
				
		vp.add(ok);
		vp.add(abbrechen);
		setWidget(vp);
		
		}
		
}
