package de.hdm.itProjektSS17.server;

import java.util.Date;

import de.hdm.itProjektSS17.server.db.ProjektMapper;
import de.hdm.itProjektSS17.server.db.ProjektmarktplatzMapper;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	ProjektmarktplatzVerwaltungImpl pimpl = new ProjektmarktplatzVerwaltungImpl();
	
	Date d = new Date();
	pimpl.init();
	
	pimpl.createTeam("Kekteam", "terasse", "hlol", 3333, "kek", 2);
	}

}
