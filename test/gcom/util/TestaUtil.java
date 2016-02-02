package gcom.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestaUtil {

	@Test
	public void testaValidaNome01(){
		assertEquals(true, Util.nomeInvalido("João"));
	}
	
	@Test
	public void testaValidaNome02(){
		assertEquals(false, Util.nomeInvalido("E S"));
	}
	
	@Test
	public void testaValidaNome03(){
		assertEquals(false, Util.nomeInvalido("Pedro Henrique"));
	}
	
	@Test
	public void testaCpfCnpjInvalido01(){
		assertEquals(false, Util.cpfCnpjInvalido("00065069684272"));
	}
	
	@Test
	public void testaCpfCnpjInvalido02(){
		assertEquals(false, Util.cpfCnpjInvalido("   65069684272"));
	}
	
	@Test
	public void testaCpfCnpjInvalido03(){
		assertEquals(false, Util.cpfCnpjInvalido("10865358000130"));
	}
	
	@Test
	public void testaCpfCnpjInvalido04(){
		assertEquals(true, Util.cpfCnpjInvalido("6506968572"));
	}
	
	@Test
	public void testaCpfCnpjInvalido05(){
		assertEquals(true, Util.cpfCnpjInvalido("10865358000134"));
	}
	
	@Test
	public void testaConverteStringParaDateAmericana() {
		GregorianCalendar calendar = new GregorianCalendar();
		
		Date data = Util.converteStringParaDateAmericana("2015-05-19");
		calendar.setTime(data);
		
		assertEquals(2015, calendar.get(Calendar.YEAR));
		assertEquals(4, calendar.get(Calendar.MONTH));
		assertEquals(19, calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testaFormatarDataAmericano(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, 4, 19);
		
		assertEquals("2015-05-19", Util.formatarData(calendar.getTime(), FormatoData.AMERICANO_COM_TRACO));
	}
}
