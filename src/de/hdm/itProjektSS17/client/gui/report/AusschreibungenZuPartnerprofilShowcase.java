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

public class AusschreibungenZuPartnerprofilShowcase extends Showcase{

	private IdentityChoiceReport identityChoiceReport = null;
	ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
	private CellTable<Ausschreibung> dataGrid = new CellTable<Ausschreibung>();
	private VerticalPanel vp = new VerticalPanel();
	
	public AusschreibungenZuPartnerprofilShowcase(IdentityChoiceReport identityChoiceReport){
		this.identityChoiceReport=identityChoiceReport;		
	}
	
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Alle Ausschreibungen, die exakt zu meinem Profil passen.";
	}

	@Override
	protected void run() {
		//append("Kleiner Drecksack");
		Organisationseinheit o = identityChoiceReport.getSelectedIdentityAsObject();
		reportGenerator.getAusschreibungByMatchingPartnerprofilOfOrganisationseinheit(o, new GetMatchingAusschreibungen());
		dataGrid.setWidth("100%");
		
		TextColumn<Ausschreibung> bezeichnungColumn = new TextColumn<Ausschreibung>(){

			@Override
			public String getValue(Ausschreibung object) {
				return object.getBezeichnung();
			}
		};
		

		TextColumn<Ausschreibung> bewerbungsfristColumn = new TextColumn<Ausschreibung>(){

			@Override
			public String getValue(Ausschreibung object) {
				return object.getBewerbungsfrist().toString();
			}
		};
		
		dataGrid.addColumn(bezeichnungColumn, "Bezeichnung");
		dataGrid.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
		this.add(dataGrid);
	}
		
	private class GetMatchingAusschreibungen implements AsyncCallback<Vector<Ausschreibung>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Ausschreibung> result) {
			if(result != null){
				//Anpassen der CellTable
				dataGrid.setRowCount(result.size(), true);
				dataGrid.setRowData(0, result);
			}
		}
		
	}

}
