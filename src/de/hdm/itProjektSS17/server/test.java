package de.hdm.itProjektSS17.server;

import java.util.Date;

import de.hdm.itProjektSS17.server.db.PartnerprofilMapper;
import de.hdm.itProjektSS17.server.db.PersonMapper;
import de.hdm.itProjektSS17.server.db.TeamMapper;
import de.hdm.itProjektSS17.server.db.UnternehmenMapper;

public class test {

	public static void main(String[] args) {
		
	Date d = new Date();
	ProjektmarktplatzVerwaltungImpl pimpl = new ProjektmarktplatzVerwaltungImpl();
	pimpl.init();
	
	pimpl.createPartnerprofil_Person(d, d, 101);
		
		//System.out.println(TeamMapper.teamMapper().findAllTeam());
		//System.out.println(PersonMapper.personMapper().findAllPerson());
		
	}
}