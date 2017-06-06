package de.hdm.itProjektSS17.client.gui.report;


import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenMitAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.Column;
import de.hdm.itProjektSS17.shared.report.HTMLReportWriter;

public class MeineBewerbungenShowcase extends Showcase{

	@Override
	protected String getHeadlineText() {
		return "Report für meine Bewerbungen";
	}

	@Override
	protected void run() {
		
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		
		final Showcase showcase = this;
			
		

		reportGenerator.createAlleBewerbungenMitAusschreibungenReport(IdentityChoiceReport.getSelectedIdentityAsObject(), 
				new AsyncCallback<AlleBewerbungenMitAusschreibungenReport>() {
			
			@Override
			public void onSuccess(AlleBewerbungenMitAusschreibungenReport result) {

				if(result!= null){
					
					HTMLReportWriter writer = new HTMLReportWriter();
				
					writer.process(result);
					
					showcase.append(writer.getReportText());
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.getMessage());
			}
		});
		
	}

}
