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
	
	public static Date primeiroDiaSemana(Date data) {
		
		switch (getDiaSemana(data)) {
		case "DOM":
			break;
		case "SEG":
			data = Data.addDias(data, -1);
			break;
		case "TER":
			data = Data.addDias(data, -2);
			break;
		case "QUA":
			data = Data.addDias(data, -3);
			break;
		case "QUI":
			data = Data.addDias(data, -4);
			break;
		case "SEX":
			data = Data.addDias(data, -5);
			break;
		case "SAB":
			data = Data.addDias(data, -6);
			break;	
		}
		
		return data;
	}
	
	public static Date ultimoDiaSemana(Date data) {
		
		switch (getDiaSemana(data)) {
		case "DOM":
			data = Data.addDias(data, 6);
			break;
		case "SEG":
			data = Data.addDias(data, 5);
			break;
		case "TER":
			data = Data.addDias(data, 4);
			break;
		case "QUA":
			data = Data.addDias(data, 3);
			break;
		case "QUI":
			data = Data.addDias(data, 2);
			break;
		case "SEX":
			data = Data.addDias(data, 1);
			break;
		case "SAB":
			break;	
		}
		
		return data;
	}
	
	public static Date diaProximaSemana(Date data) {
		
		data = primeiroDiaSemana(data);
		data = addDias(data, 7);
		
		return data;
	}
	
	public static Date dataMaisRecente(Date data1, Date data2) {
		Date dataRecente = null;
		
		if(data1.after(data2)) {
			dataRecente = data2;
		}
		else if(data2.after(data1)) {
			dataRecente = data1;
		}
		else {
			dataRecente = data1;
		}
		
		return dataRecente;
	}
}
