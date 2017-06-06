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

public class FanInFanOutShowcase extends Showcase{

	@Override
	protected String getHeadlineText() {
		return "Report für die FanIn/FanOut-Analyse";
	}

	@Override
	protected void run() {
		final Showcase showcase = this;
		
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
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
