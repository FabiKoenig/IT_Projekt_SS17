package de.hdm.itProjektSS17.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;
import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenZuPartnerprofilReport;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenAufEigeneAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenMitAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.FanIn;
import de.hdm.itProjektSS17.shared.report.FanInFanOutReport;
import de.hdm.itProjektSS17.shared.report.ProjektverflechtungenReport;
import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenReport;

public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void createAlleAusschreibungeZuPartnerprofilReport(Organisationseinheit o,
			AsyncCallback<AlleAusschreibungenZuPartnerprofilReport> callback);

	void createAlleAusschreibungenReport(AsyncCallback<AlleAusschreibungenReport> callback);

	void createAlleBewerbungenMitAusschreibungenReport(Organisationseinheit o,
			AsyncCallback<AlleBewerbungenMitAusschreibungenReport> callback);

	void createFanInFanOutReport(AsyncCallback<FanInFanOutReport> callback);

	void createProjektverflechtungenReport(int id, AsyncCallback<ProjektverflechtungenReport> callback);

	void setPerson(AsyncCallback<Void> callback);

	void fanInAnalyse(AsyncCallback<FanIn> callback);

	void createAlleBewerbungenAufEigeneAusschreibungenReport(Organisationseinheit o,
			AsyncCallback<AlleBewerbungenAufEigeneAusschreibungenReport> callback);

	void getPersonById(int id, AsyncCallback<Person> callback);

	void getTeamById(int id, AsyncCallback<Team> callback);

	void getUnternehmenById(int id, AsyncCallback<Unternehmen> callback);

	void getBewerberAufEigeneAusschreibungen(Organisationseinheit o,
			AsyncCallback<Vector<Organisationseinheit>> callback);

	void getAllPersonen(AsyncCallback<Vector<Person>> callback);

	void getAusschreibungByMatchingPartnerprofilOfOrganisationseinheit(Organisationseinheit o, AsyncCallback<Vector<Ausschreibung>> callback);

}
