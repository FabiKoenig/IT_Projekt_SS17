package de.hdm.itProjektSS17.client.gui.report;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.gui.IdentityMarketChoice;
import de.hdm.itProjektSS17.client.gui.Navigation;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class IdentityChoiceReport extends FlexTable{

	private static ListBox ownOrgUnits = new ListBox();
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
	private static ReportGeneratorAsync reportgenerator = ClientsideSettings.getReportGenerator();
	private static Person person;
	private static Team team;
	private static Unternehmen unternehmen;
	
	public IdentityChoiceReport(int id){
		
		this.setWidget(1, 0, new Label("Nutze Identität von: "));		
		this.setWidget(1, 1, ownOrgUnits);
		this.setStylePrimaryName("IdentityPanel");
		
		cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		ownOrgUnits.setWidth("250px");
		reportgenerator.getPersonById(id, new getUser());
		
		ownOrgUnits.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				NavigationReport.reload();
			}
		});
		
	}
	
	//Gibt die Id einer Person, eines Teams oder eines Unternehmens zurück
		public static int getSelectedIdentityId(){		
			if(person.getTeamId()!=null){
				if(ownOrgUnits.getSelectedIndex()==0){
					return person.getId();
				}else if(ownOrgUnits.getSelectedIndex()==1){
					return team.getId();
				}else if(ownOrgUnits.getSelectedIndex()==2){
					return unternehmen.getId();
				}
			}else if(person.getTeamId()==null){
				if(ownOrgUnits.getSelectedIndex()==0){
					return person.getId();
				}else if(ownOrgUnits.getSelectedIndex()==1){
					return unternehmen.getId();
				}

			}
			return 0;
		}
	
		
		public Organisationseinheit getSelectedIdentityAsObject(){
			Window.alert("triggered");
			if(person.getTeamId()!=null){
				if(ownOrgUnits.getSelectedIndex()==0){
					return person;
				}else if(ownOrgUnits.getSelectedIndex()==1){
					return team;
				}else if(ownOrgUnits.getSelectedIndex()==2){
					return unternehmen;
				}
			}else if(person.getTeamId()==null){
				if(ownOrgUnits.getSelectedIndex()==0){
					Window.alert("triggered2");
					return person;
				}else if(ownOrgUnits.getSelectedIndex()==1){
					return unternehmen;
				}

			}
			return null;
		}
		
		
		
	private class getUser implements AsyncCallback<Person>{

		public void onFailure(Throwable caught) {
			Window.alert("User konnte nicht für die Identitätsleiste geladen werden");		
		}

		@Override
		public void onSuccess(Person result) {
			ownOrgUnits.clear();
			person=result;
			Integer idOfPerson=result.getId();
			ownOrgUnits.addItem("Person: "+result.getVorname()+" "+result.getNachname(), idOfPerson.toString());
			
			if(person.getTeamId()!=null){
				reportgenerator.getTeamById(result.getTeamId(), new getTeam());
			}else if(person.getUnternehmenId()!=null){
				reportgenerator.getUnternehmenById(result.getUnternehmenId(), new getUnternehmen());
			}
		}			
	}
		
	private class getTeam implements AsyncCallback<Team>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Team des Users konnte nicht f�r die Identit�tsleiste geladen werden");			
		}

		@Override
		public void onSuccess(Team result) {
			Integer idOfTeam=result.getId();
			ownOrgUnits.addItem("Team: "+result.getName(),idOfTeam.toString());	
			team=result;
			if(person.getUnternehmenId()!=null){
				reportgenerator.getUnternehmenById(person.getUnternehmenId(), new getUnternehmen());
			}	
		}	
	}
	
	private class getUnternehmen implements AsyncCallback<Unternehmen>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Unternehmen des Users konnte nicht für die Identitätsleiste geladen werden");
			
		}

		@Override
		public void onSuccess(Unternehmen result) {
			Integer idOfUnternehmen=result.getId();
			ownOrgUnits.addItem("Unternehmen: "+result.getName(),idOfUnternehmen.toString());
			unternehmen=result;
			
		}
		
	}
		
	
	
}
