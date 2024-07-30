package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Data {

	public static String getDiaSemana(Date data){ 
	    String diaSemana = "---";
	    GregorianCalendar gc = new GregorianCalendar();
	    gc.setTime(data);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		    case Calendar.SUNDAY:
		        diaSemana = "DOM";
		        break;
		    case Calendar.MONDAY:
		        diaSemana = "SEG";
		        break;
		    case Calendar.TUESDAY:
		        diaSemana = "TER";
		    break;
		    case Calendar.WEDNESDAY:
		        diaSemana = "QUA";
		        break;
		    case Calendar.THURSDAY:
		        diaSemana = "QUI";
		        break;
		    case Calendar.FRIDAY:
		        diaSemana = "SEX";
		        break;
		    case Calendar.SATURDAY:
		        diaSemana = "SAB";

		}
		return diaSemana;
	}
	
	public static Date addDias(Date data, int dias) {
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(data); 
		cal.add(Calendar.DATE, dias);
		data = cal.getTime();
		
		return data;
	}
}
