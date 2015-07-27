package gcom.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class TestaUtil extends TestCase {

	public void testaValidaNome01(){
		assertEquals(true, Util.nomeInvalido("João"));
	}
	
	public void testaValidaNome02(){
		assertEquals(false, Util.nomeInvalido("E S"));
	}
	
	public void testaValidaNome03(){
		assertEquals(false, Util.nomeInvalido("Pedro Henrique"));
	}
	
	public void testaCpfCnpjInvalido01(){
		assertEquals(false, Util.cpfCnpjInvalido("00065069684272"));
	}
	
	public void testaCpfCnpjInvalido02(){
		assertEquals(false, Util.cpfCnpjInvalido("   65069684272"));
	}
	
	public void testaCpfCnpjInvalido03(){
		assertEquals(false, Util.cpfCnpjInvalido("10865358000130"));
	}
	
	public void testaCpfCnpjInvalido04(){
		assertEquals(true, Util.cpfCnpjInvalido("6506968572"));
	}
	public void testaCpfCnpjInvalido05(){
		assertEquals(true, Util.cpfCnpjInvalido("10865358000134"));
	}
	
	public void testaConverteStringParaDateAmericana() {
		GregorianCalendar calendar = new GregorianCalendar();
		
		Date data = Util.converteStringParaDateAmericana("2015-05-19");
		calendar.setTime(data);
		
		assertEquals(2015, calendar.get(Calendar.YEAR));
		assertEquals(4, calendar.get(Calendar.MONTH));
		assertEquals(19, calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	public void testaFormatarDataAmericano(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, 4, 19);
		
		assertEquals("2015-05-19", Util.formatarData(calendar.getTime(), FormatoData.AMERICANO_COM_TRACO));
	}
}
