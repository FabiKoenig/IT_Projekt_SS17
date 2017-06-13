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
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Erstellung von
 * Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
 * das Paar {@link BankAdministration} und {@lBankAdministrationImplImpl}. Zu
 * technischen Erläuterung etwa bzgl. GWT RPC bzw. {@link RemoteServiceServlet}
 * siehe {@link BankAdministration} undBankAdministrationImpltungImpl}.
 * </p>
 * <p>
 * Ein ReportGenerator bietet die Möglichkeit, eine Menge von Berichten
 * (Reports) zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des
 * Systems zweckspezifisch darstellen.
 * </p>
 * <p>
 * Die Klasse bietet eine Reihe von <code>create...</code>-Methoden, mit deren
 * Hilfe die Reports erstellt werden können. Jede dieser Methoden besitzt eine
 * dem Anwendungsfall entsprechende Parameterliste. Diese Parameter benötigt der
 * der Generator, um den Report erstellen zu können.
 * </p>
 * <p> 
 * Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache
 * Weise erweitert werden. Hierzu können zusätzliche <code>create...</code>
 * -Methoden implementiert werden. Die bestehenden Methoden bleiben davon
 * unbeeinflusst, so dass bestehende Programmlogik nicht verändert werden muss.
 * </p>
 * 
 * @author thies
 */
@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService{

	public void init() throws IllegalArgumentException;
	
	public void setPerson() throws IllegalArgumentException;
	
	public abstract AlleAusschreibungenReport createAlleAusschreibungenReport() throws IllegalArgumentException;

	public abstract AlleBewerbungenAufEigeneAusschreibungenReport createAlleBewerbungenAufEigeneAusschreibungenReport(
			Organisationseinheit o) throws IllegalArgumentException;

	public abstract AlleBewerbungenMitAusschreibungenReport createAlleBewerbungenMitAusschreibungenReport(Organisationseinheit o) throws IllegalArgumentException;

	public abstract FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException;

	ProjektverflechtungenReport createProjektverflechtungenReport(int id);
	
	public abstract FanIn fanInAnalyse() throws IllegalArgumentException;
	
	public Person getPersonById(int id) throws IllegalArgumentException;
	
	public Team getTeamById(int id) throws IllegalArgumentException;
	
	public Unternehmen getUnternehmenById(int id) throws IllegalArgumentException;
	
	public Vector<Organisationseinheit> getBewerberAufEigeneAusschreibungen(Organisationseinheit o) throws IllegalArgumentException;
	
	public Vector<Person> getAllPersonen() throws IllegalArgumentException;

	AlleAusschreibungenZuPartnerprofilReport createAlleAusschreibungeZuPartnerprofilReport(Organisationseinheit o)
			throws IllegalArgumentException;

	public AlleAusschreibungenZuPartnerprofilReport getAusschreibungByMatchingPartnerprofilOfOrganisationseinheitReport(Organisationseinheit o) throws IllegalArgumentException;
}
