package de.hdm.itProjektSS17.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.thies.bankProjektSS17.shared.report.AlleAusschreibungenReport;
import de.hdm.thies.bankProjektSS17.shared.report.AlleAusschreibungenZuPartnerprofilReport;
import de.hdm.thies.bankProjektSS17.shared.report.AlleBewerbungenAufEigeneAusschreibungenReport;
import de.hdm.thies.bankProjektSS17.shared.report.AlleBewerbungenMitAusschreibungenReport;
import de.hdm.thies.bankProjektSS17.shared.report.FanInFanOutReport;
import de.hdm.thies.bankProjektSS17.shared.report.ProjektverflechtungenReport;

public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void createAlleAusschreibungeZuPartnerprofilReport(Partnerprofil p,
			AsyncCallback<AlleAusschreibungenZuPartnerprofilReport> callback);

	void createAlleAusschreibungenAufEigeneAusschreibungenReport(Organisationseinheit o,
			AsyncCallback<AlleBewerbungenAufEigeneAusschreibungenReport> callback);

	void createAlleAusschreibungenReport(AsyncCallback<AlleAusschreibungenReport> callback);

	void createAlleBewerbungenMitAusschreibungenReport(Organisationseinheit o,
			AsyncCallback<AlleBewerbungenMitAusschreibungenReport> callback);

	void createFanInFanOutReport(AsyncCallback<FanInFanOutReport> callback);

	void createProjektverflechtungenReport(Organisationseinheit o, AsyncCallback<ProjektverflechtungenReport> callback);

	void setPerson(AsyncCallback<Void> callback);

}
