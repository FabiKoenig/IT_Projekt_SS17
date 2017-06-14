package de.hdm.itProjektSS17.client.gui.report;

import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenZuPartnerprofilReport;
import de.hdm.itProjektSS17.shared.report.HTMLReportWriter;

public class AusschreibungenZuPartnerprofilShowcase extends Showcase{

	private IdentityChoiceReport identityChoiceReport = null;
	
	public AusschreibungenZuPartnerprofilShowcase(IdentityChoiceReport identityChoiceReport) {
		this.identityChoiceReport = identityChoiceReport;
	}
	
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Alle Ausschreibungen, die exakt zu meinem Partnerprofil passen";
	}

	@Override
	protected void run() {
		
		final Showcase showcase = this;
		
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		reportGenerator.getAusschreibungByMatchingPartnerprofilOfOrganisationseinheitReport(identityChoiceReport.getSelectedIdentityAsObject(), new AsyncCallback<AlleAusschreibungenZuPartnerprofilReport>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.toString());
				
			}

			@Override
			public void onSuccess(AlleAusschreibungenZuPartnerprofilReport result) {
				
				if(result!= null){
					
				HTMLReportWriter writer = new HTMLReportWriter();
				
				writer.process(result);
				
				showcase.append(writer.getReportText());
				
				}
			}
		});
		
	}


}
