package de.hdm.itProjektSS17.server;

import de.hdm.itProjektSS17.server.db.PartnerprofilMapper;
import de.hdm.itProjektSS17.server.db.PersonMapper;
import de.hdm.itProjektSS17.server.db.TeamMapper;
import de.hdm.itProjektSS17.server.db.UnternehmenMapper;

public class test {

	public static void main(String[] args) {
		
	
	//ProjektmarktplatzVerwaltungImpl pimpl = new ProjektmarktplatzVerwaltungImpl();
	//pimpl.init();
	
	//pimpl.deletePartnerprofil_Person(PartnerprofilMapper.partnerprofilMapper().findById(12));
		
		//System.out.println(TeamMapper.teamMapper().findAllTeam());
		//System.out.println(PersonMapper.personMapper().findAllPerson());
		System.out.println(UnternehmenMapper.unternehmenMapper().findAllUnternehmen());
	}
}