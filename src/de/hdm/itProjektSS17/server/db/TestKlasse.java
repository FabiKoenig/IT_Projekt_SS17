package de.hdm.itProjektSS17.server.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdm.itProjektSS17.shared.bo.Beteiligung;

public class TestKlasse {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		Date sdate = new Date();
		String date = dateFormat.format(sdate);
	
		
		
//		System.out.println("Datum: " + sdate + ", " + edate);
				
		Beteiligung b = new Beteiligung();
		
		b.setId(1);
//		b.setEndDatum(date);
//		b.setProjektId(1);
//		b.setStartDatum(date);
//		b.setUmfang(10);
//		b.setBewertungId(1);
//		b.setBeteiligterId(1);
//		
		
		BeteiligungMapper.beteiligungMapper().findById(1);
		
		System.out.println(BeteiligungMapper.beteiligungMapper().findByObject(b).getStartDatum());
	}

}
