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

public class AlleAusschreibungenShowcase extends Showcase{

	@Override
	protected String getHeadlineText() {
		return "Alle Ausschreibungen Report";
	}

	@Override
	protected void run() {
		
		final Showcase showcase = this;
		
		this.append("Auslesen aller Ausschreibungen auf dem Projektmarktplatz");
		
		
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		
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
		
		
	
		
//		projektmarktplatzVerwaltung.getOrganisationseinheitById(IdentityMarketChoice.getSelectedIdentityId(), new GetOrganisationseinheitCallback(this));
		
		
		
	}
	
	
//	private class GetOrganisationseinheitCallback implements AsyncCallback<Organisationseinheit> {
//
//		private Showcase showcase = null;
//		
//		public GetOrganisationseinheitCallback(Showcase s){
//			this.showcase = s;
//		}
//		
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler: " + caught.getMessage());
//		}
//
//		@Override
//		public void onSuccess(Organisationseinheit result) {
//			if(result != null){
//				ReportGeneratorAsync reportGenerator = ClientsideSettings
//			            .getReportGenerator();
//				
//				reportGenerator.init(new AsyncCallback<Void>() {
//					public void onFailure(Throwable caught) {
//						Window.alert("Der Report Generator konnte nicht initialisiert werden.");
//					}
//					public void onSuccess(Void result) {
//					}
//				});
//				reportGenerator.createAlleAusschreibungenReport(new AlleAusschreibungenReportCallback(this.showcase));
//			}		
//		}	
	
	
	private class AlleAusschreibungenReportCallback implements AsyncCallback<AlleAusschreibungenReport>{

		private Showcase showcase = null;
		
		public AlleAusschreibungenReportCallback(Showcase s) {
			this.showcase = s;
		}
		@Override
		public void onFailure(Throwable caught) {
//			Window.alert("Fehler beim AlleAusschreibungenCallback: " + caught.toString());
		}

		@Override
		public void onSuccess(AlleAusschreibungenReport report) {
//			Window.alert(report.getTitel());
			if(report!= null){
				HTMLReportWriter writer = new HTMLReportWriter();
				
				writer.process(report);
				
				this.showcase.append(writer.getReportText());
				}
			
			}
		
		}

	}
//}