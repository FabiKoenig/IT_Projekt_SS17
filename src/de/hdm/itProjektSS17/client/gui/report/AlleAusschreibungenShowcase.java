package de.hdm.itProjektSS17.client.gui.report;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.HTMLReportWriter;
import de.hdm.itProjektSS17.shared.*;

/**
 * Klasse die einen Report mit allen Ausschreibung ausgibt
 * @author Tim
 *
 */
public class AlleAusschreibungenShowcase extends Showcase{

	/**
	 * Setzen des Headline Textes
	 */
	@Override
	protected String getHeadlineText() {
		return "Report für alle Ausschreibungen";
	}

	/**
	 * Methode welche startet, sobald diese Klasse aufgerufen wird.
	 */
	@Override
	protected void run() {
		
		final Showcase showcase = this;
		
		this.append("Auslesen aller Ausschreibungen auf dem Projektmarktplatz");
		
		/**
		 * Auslesen der ReportGeneratorAsync Instanz
		 */
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		/**
		 * Bei erfolgreichem Callback werden alle Ausschreibungen als Report ausgegeben.
		 */
		reportGenerator.createAlleAusschreibungenReport(new AsyncCallback<AlleAusschreibungenReport>() {
			
			
			
			public void onFailure(Throwable caught) {
				showcase.append("Fehler: " + caught.getMessage());;
				
			}

			@Override
			public void onSuccess(AlleAusschreibungenReport result) {
				if(result!= null){
					
					HTMLReportWriter writer = new HTMLReportWriter();
				
					writer.process(result);
					
					showcase.append(writer.getReportText());
				}	
			}
		});	
	}
}

