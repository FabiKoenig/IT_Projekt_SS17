package de.hdm.itProjektSS17.server.db;

public class PersonMapper {

	
	
	private static PersonMapper personMapper = null;
	
	protected PersonMapper(){
	}
	
	  public static PersonMapper personMapper() {
		    if (personMapper == null) {
		      personMapper = new PersonMapper();
		    }

		    return personMapper;
		  }
}
