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

/**
 * Klasse welche einen Report mit passenden Ausschreibung zum eigenen(User) Partnerprofil
 * ausgiebt
 * @author Tim
 *
 */
public class AusschreibungenZuPartnerprofilShowcase extends Showcase{

	private IdentityChoiceReport identityChoiceReport = null;
	
	/**
	 * Konstruktor, dem eine Instanz der IdentityChoiceReport und der Navigation übergeben wird.
	 * @param identityChoiceReport
	 */
	public AusschreibungenZuPartnerprofilShowcase(IdentityChoiceReport identityChoiceReport) {
		this.identityChoiceReport = identityChoiceReport;
	}
	
	/**
	 * Setzen des Headline Texts
	 */
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Alle Ausschreibungen, die exakt zu meinem Partnerprofil passen";
	}

	
	/**
	 * Methode welche startet, sobald diese Klasse aufgerufen wird
	 */
	@Override
	protected void run() {
		
		final Showcase showcase = this;
		
		/**
		 * Auslesen der ReportGeneratorAsync Instanz
		 */
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		
		/**
		 * Bei erfolgreichem Callback wird ein Report mit allen Ausschreibung ausgegeben die zum Partnerprofil
		 * Users passen.
		 */
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
