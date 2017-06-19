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
import de.hdm.itProjektSS17.shared.report.FanInFanOutReport;
import de.hdm.itProjektSS17.shared.report.HTMLReportWriter;
import de.hdm.itProjektSS17.shared.report.ProjektverflechtungenReport;

/**
 * Klasse die einen Report zu einer Fan-in/ Fan-out Analyse ausgibt
 * @author Tim
 *
 */
public class FanInFanOutShowcase extends Showcase{

	/**
	 * Setzen des Headline Texts
	 */
	@Override
	protected String getHeadlineText() {
		return "Report für die FanIn/FanOut-Analyse";
	}

	/**
	 * Methode welche startet sobald die Klasse aufgerufen wird
	 */
	@Override
	protected void run() {
		
		final Showcase showcase = this;
		
		/**
		 * Auslesen der ProjektmarktplatzAsync Instanz
		 */
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		
		/**
		 * Bei erfolgreichem Callback wird ein Report mit einer Fan-in/ Fan-out Analyse ausgegeben.
		 */
		reportGenerator.createFanInFanOutReport(new AsyncCallback<FanInFanOutReport>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei FanInFanOut-Report "+caught.getMessage());
						
					}

					@Override
					public void onSuccess(FanInFanOutReport result) {
						
						if(result!= null){
							
							HTMLReportWriter writer = new HTMLReportWriter();
						
							writer.process(result);
							
							showcase.append(writer.getReportText());
						}
					}
		});
		
	}

}
