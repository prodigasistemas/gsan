package gcom.util;

import java.math.BigDecimal;
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
	
	@Test
	public void testaCalculaPercentual(){
	    BigDecimal valorBase  = new BigDecimal(100);
	    assertEquals(5, Util.calcularPercentual(valorBase, 5D).doubleValue(), 0.01);

	    valorBase  = new BigDecimal(80);
	    assertEquals(5.60, Util.calcularPercentual(valorBase, 7D).doubleValue(), 0.01);
	}
	
    @Test
    public void testaConversaoDecimalParaString(){
        BigDecimal valorBase  = new BigDecimal(999.65);
        assertEquals("999.65", Util.converterDecimalParaString(valorBase));
        valorBase  = new BigDecimal(9);
        assertEquals("9.00", Util.converterDecimalParaString(valorBase));
        valorBase  = new BigDecimal(9.68);
        assertEquals("9.68", Util.converterDecimalParaString(valorBase));
        valorBase  = new BigDecimal(99.500000);
        assertEquals("99.50", Util.converterDecimalParaString(valorBase));
        valorBase  = new BigDecimal(99999.55);
        assertEquals("99999.55", Util.converterDecimalParaString(valorBase));
        valorBase  = new BigDecimal(9999999.55);
        assertEquals("9999999.55", Util.converterDecimalParaString(valorBase));
        valorBase  = new BigDecimal(999.5);
        assertEquals("999.50", Util.converterDecimalParaString(valorBase));
    }
    
    @Test
    public void testaConversaoDecimalParaStringComZerosEsquerda(){
        BigDecimal valorBase  = new BigDecimal(999.65);
        assertEquals("0099965", Util.adicionarZerosEsquedaNumero(7, valorBase));
        valorBase  = new BigDecimal(100);
        assertEquals("0010000", Util.adicionarZerosEsquedaNumero(7, valorBase));        
    }
    
    @Test
    public void testaAdicionarZerosEsquedaNumero(){
        BigDecimal nulo = null;
        assertEquals("0000", Util.adicionarZerosEsquedaNumero(4, nulo));
    }
    
    @Test
    public void testaSeNumeroEhDecimal(){
        assertTrue(Util.isBigDecimal("0"));
        assertTrue(Util.isBigDecimal("190.00"));
        assertTrue(Util.isBigDecimal("-40.67"));
        assertTrue(Util.isBigDecimal("0.67"));
        assertFalse(Util.isBigDecimal(""));
        assertFalse(Util.isBigDecimal(null));
    }
    
    @Test
    public void testaSeNumeroEhPositivo(){
        assertTrue(Util.isPositivo("1"));
        assertFalse(Util.isPositivo("0"));
        assertFalse(Util.isPositivo("-1"));
    }
    
    @Test
    public void testaCompletaString(){
        assertEquals("dez       ", Util.completaString("dez", 10));
        assertEquals("          ", Util.completaString("", 10));
        assertEquals("          ", Util.completaString(null, 10));
    }
}
