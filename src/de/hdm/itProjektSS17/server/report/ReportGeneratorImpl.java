package de.hdm.itProjektSS17.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ReportGenerator;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.thies.bankProjektSS17.shared.report.AlleAusschreibungenReport;
import de.hdm.thies.bankProjektSS17.shared.report.AlleAusschreibungenZuPartnerprofilReport;
import de.hdm.thies.bankProjektSS17.shared.report.AlleBewerbungenAufEigeneAusschreibungenReport;
import de.hdm.thies.bankProjektSS17.shared.report.AlleBewerbungenMitAusschreibungenReport;
import de.hdm.thies.bankProjektSS17.shared.report.FanInFanOutReport;
import de.hdm.thies.bankProjektSS17.shared.report.ProjektverflechtungenReport;

public class ReportGeneratorImpl extends RemoteServiceServlet
implements ReportGenerator{

	
	/**
	 * Der ReportGenerator benötigt Zugriff auf die ProjektmarktplatzVerwaltung,
	 * da dort wichtige Methoden für die Koexistenz von Datenobjekten enthalten sind.
	 */
	private ProjektmarktplatzVerwaltung projektmarktplatzverwaltung = null;
	
	/**
	 * TODO
	 * @throws IllegalArgumentException
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {
	}
	
	
	public void init() throws IllegalArgumentException{
		/**
		 * Hier wird eine ProjektmarktplatzVerwaltungImpl-Instanz instantiiert
		 * um auf dessen Methoden zugreifen zu können.
		 */
		
		ProjektmarktplatzVerwaltungImpl pm = new ProjektmarktplatzVerwaltungImpl();
		pm.init();
		this.projektmarktplatzverwaltung = pm;
	}
	
	/**
	 * Auslesen der zugehörigen ProjektmarktplatzVerwaltung für den internen Gebrauch.
	 * @return das ProjektmarktplatzVerwaltung-Objekt.
	 */
	protected ProjektmarktplatzVerwaltung getProjektmarktplatzVerwaltung(){
		return this.projektmarktplatzverwaltung;
	}
	
	@Override
	public AlleAusschreibungenZuPartnerprofilReport createAlleAusschreibungeZuPartnerprofilReport(Partnerprofil p)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlleAusschreibungenReport createAlleAusschreibungenReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlleBewerbungenMitAusschreibungenReport createAlleBewerbungenMitAusschreibungenReport(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlleBewerbungenAufEigeneAusschreibungenReport createAlleAusschreibungenAufEigeneAusschreibungenReport(
			Organisationseinheit o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjektverflechtungenReport createProjektverflechtungenReport(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
