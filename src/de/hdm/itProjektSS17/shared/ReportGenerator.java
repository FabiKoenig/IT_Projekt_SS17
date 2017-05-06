package de.hdm.itProjektSS17.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenZuPartnerprofilReport;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenAufEigeneAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenMitAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.FanInFanOutReport;
import de.hdm.itProjektSS17.shared.report.ProjektverflechtungenReport;
import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenReport;

public interface ReportGenerator extends RemoteService{

	public void init() throws IllegalArgumentException;
	
	public void setPerson() throws IllegalArgumentException;
	
	public abstract AlleAusschreibungenZuPartnerprofilReport createAlleAusschreibungeZuPartnerprofilReport(Partnerprofil p) throws IllegalArgumentException;
	
	public abstract AlleAusschreibungenReport createAlleAusschreibungenReport() throws IllegalArgumentException;
	
	public abstract AlleBewerbungenMitAusschreibungenReport createAlleBewerbungenMitAusschreibungenReport(Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract AlleBewerbungenAufEigeneAusschreibungenReport createAlleAusschreibungenAufEigeneAusschreibungenReport(Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract ProjektverflechtungenReport createProjektverflechtungenReport(Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException;
	
}
