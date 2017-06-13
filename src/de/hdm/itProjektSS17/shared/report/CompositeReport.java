package de.hdm.itProjektSS17.shared.report;

import java.util.Vector;

/**
 * Zusammengesetzter Report, welcher aus mehreren Teil-/Sub-Reports bestehen kann.
 *
 */

public class CompositeReport extends Report{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * In dem Vector-Obejekt werden die Teil-Reports gespeichert.
	 */
	private Vector<Report> subReports = new Vector<Report>();
	
	
	/**
	 * Hinzufügen eines Teil-Reports.
	 * @param r der hinzuzufügende Teil-Report.
	 */
	public void addSubReport(Report r){
		this.subReports.addElement(r);
	}
	
	/**
	 * Löschen eines Teil-Reports.
	 * @param r der zu löschende Report.
	 */
	public void removeSubReport(Report r) {
		this.subReports.removeElement(r);
	}
	
	/**
	 * Auslesen der Anzahl von Teil-Reports.
	 * @return
	 */
	public int getSubReportsSize() {
		return this.subReports.size();
	}
	
	/**
	 * Auslesen eines bestimmten Teil-Reports.
	 * @param i Index des auszulesenden Teil-Reports.
	 * @return das Report Objekt des auszulesenden Teil-Reports.
	 */
	public Report getSubReportByIndex(int i) {
		return this.subReports.elementAt(i);
	}
}
