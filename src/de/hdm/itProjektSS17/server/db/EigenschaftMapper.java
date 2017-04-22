package de.hdm.itProjektSS17.server.db;

public class EigenschaftMapper {

	
	private static EigenschaftMapper eigenschaftMapper = null;
	
	protected EigenschaftMapper(){
	}
	
	  public static EigenschaftMapper eigenschaftMapper() {
		    if (eigenschaftMapper == null) {
		      eigenschaftMapper = new EigenschaftMapper();
		    }

		    return eigenschaftMapper;
		  }
}
