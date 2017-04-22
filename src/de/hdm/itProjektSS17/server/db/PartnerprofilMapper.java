package de.hdm.itProjektSS17.server.db;

public class PartnerprofilMapper {

	
	
	private static PartnerprofilMapper partnerprofilMapper = null;
	
	protected PartnerprofilMapper(){
	}
	
	  public static PartnerprofilMapper partnerprofilMapper() {
		    if (partnerprofilMapper == null) {
		      partnerprofilMapper = new PartnerprofilMapper();
		    }

		    return partnerprofilMapper;
		  }
}
