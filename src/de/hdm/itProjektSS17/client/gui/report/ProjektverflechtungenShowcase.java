package de.hdm.itProjektSS17.client.gui.report;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenMitAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.HTMLReportWriter;
import de.hdm.itProjektSS17.shared.report.ProjektverflechtungenReport;

public class ProjektverflechtungenShowcase extends Showcase{

	@Override
	protected String getHeadlineText() {
		return "Report für Projektverflechtungen";
	}
		
	
	@Override
	protected void run() {
	
		
			VerticalPanel inputPanel = new VerticalPanel();
			final HTMLResultPanel resultPanel = new HTMLResultPanel();
			final Showcase showcase = this;
				
			ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
			
			
			final ListBox bewerberBox = new ListBox();
			bewerberBox.addItem("Bitte wähle einen Bewerber aus");
			
			reportGenerator.getBewerberAufEigeneAusschreibungen(IdentityChoiceReport.getSelectedIdentityAsObject(), 
					new AsyncCallback<Vector<Organisationseinheit>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: "+caught.getMessage());
							
						}

						@Override
						public void onSuccess(Vector<Organisationseinheit> result) {
							
							for (Organisationseinheit bewerber : result) {
								
								  
							      if(bewerber instanceof Person){
										bewerberBox.addItem(((Person)bewerber).getVorname() + " "
							      + ((Person)bewerber).getNachname()+", ID:"+bewerber.getId());
									
									} else if(bewerber instanceof Team){
										bewerberBox.addItem(((Team)bewerber).getName() + ", ID:" +bewerber.getId());
									
									} else if(bewerber instanceof Unternehmen){
										bewerberBox.addItem(((Unternehmen)bewerber).getName() + ", ID:" +bewerber.getId());
										
									}	
							}	
					}
			});
			
			inputPanel.add(bewerberBox);
			inputPanel.add(resultPanel);
			this.add(inputPanel);
			
			
			bewerberBox.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
					
					
					ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
					
					resultPanel.clear();
					String s= bewerberBox.getValue(bewerberBox.getSelectedIndex());	
					String last = s.substring(s.indexOf(':')+1, s.length());
					int selectedId = Integer.valueOf(last);	
				
					
					reportGenerator.createProjektverflechtungenReport(selectedId, 
							new AsyncCallback<ProjektverflechtungenReport>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Fehler bei Projektverflechtungen Report: "+caught.getMessage());
									
								}

								@Override
								public void onSuccess(ProjektverflechtungenReport result) {
									
									if(result!= null){

										HTMLReportWriter writer = new HTMLReportWriter();
									
										writer.process(result);
						
										resultPanel.append(writer.getReportText());
									}
								}
					});
					
				}
			});
	}
}
