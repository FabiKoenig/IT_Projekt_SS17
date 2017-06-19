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

/**
 * Klasse die einen Report mit den eigenen Bewerbungen ausgibt
 * @author Tim
 *
 */
public class MeineBewerbungenShowcase extends Showcase{

	private IdentityChoiceReport identityChoiceReport = null;
	
	/**
	 * Konstruktor, dem eine Instanz der IdentityChoiceReport und der Navigation übergeben wird.
	 * @param identityChoiceReport
	 */
	public MeineBewerbungenShowcase(IdentityChoiceReport identityChoiceReport) {
		this.identityChoiceReport=identityChoiceReport;
	}
	
	/**
	 * Setzen des Headline Texts
	 */
	@Override
	protected String getHeadlineText() {
		return "Report für meine Bewerbungen";
	}

	/**
	 * Methode die startet sobald der Klasse aufgerufen wird
	 */
	@Override
	protected void run() {
		
		/**
		 * Auslesen der ReportGeneratorAsync Instanz
		 */
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		
		final Showcase showcase = this;
			
		
		/**
		 * Bei erfolgreichem Callback werden alle Bewerbungen mit den dazugehörigen Ausschreibungen 
		 * als Report ausgegeben.
		 */
		reportGenerator.createAlleBewerbungenMitAusschreibungenReport(identityChoiceReport.getSelectedIdentityAsObject(), 
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
