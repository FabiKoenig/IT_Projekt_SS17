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
/**
 * asynchroner Teil des Interface @link {@link ReportGenerator}
 */
public interface ReportGeneratorAsync {
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#init();
	**/
	void init(AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createAlleAusschreibungeZuPartnerprofilReport(Organisationseinheit);
	**/
	void createAlleAusschreibungeZuPartnerprofilReport(Organisationseinheit o,
			AsyncCallback<AlleAusschreibungenZuPartnerprofilReport> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createAlleAusschreibungenReport();
	**/
	void createAlleAusschreibungenReport(AsyncCallback<AlleAusschreibungenReport> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createAlleBewerbungenMitAusschreibungenReport(Organisationseinheit);
	**/
	void createAlleBewerbungenMitAusschreibungenReport(Organisationseinheit o,
			AsyncCallback<AlleBewerbungenMitAusschreibungenReport> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createFanInFanOutReport();
	**/
	void createFanInFanOutReport(AsyncCallback<FanInFanOutReport> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createProjektverflechtungenReport();
	**/
	void createProjektverflechtungenReport(int id, AsyncCallback<ProjektverflechtungenReport> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#setPerson();
	**/
	void setPerson(AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#fanInAnalyse();
	**/
	void fanInAnalyse(AsyncCallback<FanIn> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createAlleBewerbungenAufEigeneAusschreibungenReport(Organisationseinheit);
	**/
	void createAlleBewerbungenAufEigeneAusschreibungenReport(Organisationseinheit o,
			AsyncCallback<AlleBewerbungenAufEigeneAusschreibungenReport> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getPerson(int);
	**/
	void getPersonById(int id, AsyncCallback<Person> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getTeamById(int);
	**/
	void getTeamById(int id, AsyncCallback<Team> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getUnternehmenById(int);
	**/
	void getUnternehmenById(int id, AsyncCallback<Unternehmen> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getBewerberAufEigeneAusschreibungen(Organisationseinheit);
	**/
	void getBewerberAufEigeneAusschreibungen(Organisationseinheit o,
			AsyncCallback<Vector<Organisationseinheit>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getAllPersonen();
	**/
	void getAllPersonen(AsyncCallback<Vector<Person>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getAusschreibungenByMatchingPartnerprofilOfOrganisationeinheitReport(Organisationseinheit);
	**/
	void getAusschreibungByMatchingPartnerprofilOfOrganisationseinheitReport(Organisationseinheit o,
			AsyncCallback<AlleAusschreibungenZuPartnerprofilReport> callback);

}
