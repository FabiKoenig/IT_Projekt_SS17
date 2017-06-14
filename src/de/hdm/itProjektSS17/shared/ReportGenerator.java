package de.hdm.itProjektSS17.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenZuPartnerprofilReport;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenAufEigeneAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenMitAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.FanIn;
import de.hdm.itProjektSS17.shared.report.FanInFanOutReport;
import de.hdm.itProjektSS17.shared.report.ProjektverflechtungenReport;
import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenReport;
import de.hdm.itProjektSS17.shared.bo.*;
/**
 * Der ReportGenerator dient dazu, verschiedene
 * Reports zu erstellen, die eine bestimmte Anzahl von Daten des
 * Systems zweckspezifisch darstellen.
**/

@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService{
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#init();
	**/
	public void init() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#setPerson();
	**/
	public void setPerson() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createAlleAusschreibungenReport();
	**/
	public abstract AlleAusschreibungenReport createAlleAusschreibungenReport() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createAlleBewerbungenAufEigeneAusschreibungenReport(Organisationseinheit);
	**/
	public abstract AlleBewerbungenAufEigeneAusschreibungenReport createAlleBewerbungenAufEigeneAusschreibungenReport(
			Organisationseinheit o) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#AlleBewerbungenMitAusschreibungenReport(Organisationseinheit);
	**/
	public abstract AlleBewerbungenMitAusschreibungenReport createAlleBewerbungenMitAusschreibungenReport(Organisationseinheit o) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createFanInFanOutReport();
	**/
	public abstract FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createProjektverflechtungenReport(int);
	**/
	ProjektverflechtungenReport createProjektverflechtungenReport(int id);
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#fanInAnlyse();
	**/
	public abstract FanIn fanInAnalyse() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getPersonById(int);
	**/
	public Person getPersonById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getTeamById(int);
	**/
	public Team getTeamById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getUnternehmenById(int);
	**/
	public Unternehmen getUnternehmenById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getBewerberAufEigeneAusschreibungen(Organisationseinheit);
	**/
	public Vector<Organisationseinheit> getBewerberAufEigeneAusschreibungen(Organisationseinheit o) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getAllPersonen();
	**/
	public Vector<Person> getAllPersonen() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#createAlleAusschreibungenZuPartnerprofilReport(Organisationseinheit);
	**/
	AlleAusschreibungenZuPartnerprofilReport createAlleAusschreibungeZuPartnerprofilReport(Organisationseinheit o)
			throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.report.ReportGeneratorImpl#getAusschreibungByMatchingPartnerprofilOfOrganisationseinheitReport(Organisationseinheit);
	**/
	public AlleAusschreibungenZuPartnerprofilReport getAusschreibungByMatchingPartnerprofilOfOrganisationseinheitReport(Organisationseinheit o) throws IllegalArgumentException;
}
