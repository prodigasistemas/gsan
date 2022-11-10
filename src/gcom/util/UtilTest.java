package gcom.util;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class UtilTest {

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(FormatoData.AMERICANO_COM_TRACO.getFormato());

	@Test
	public void cpfOuCnpjDeveSerValido() {
		
		String cpf = "75132834272";
		boolean cpfCnpjInvalido = Util.cpfCnpjInvalido(cpf);
		
		assertFalse(cpfCnpjInvalido);
	}
	
	@Test
	public void cpfOuCnpjNaoDeveSerValido() {
		
		String cpf = "01762878258";
		boolean cpfCnpjInvalido = Util.cpfCnpjInvalido(cpf);
		assertTrue(cpfCnpjInvalido);
	}
	
	@Test 
	public void seData2ForMaiorRetorna1() throws Exception {
		
		Date data = SIMPLE_DATE_FORMAT.parse("2022-09-20");
		int compararData = Util.compararData(new Date(), data);
		
		assertEquals(1, compararData);
	}
	
	@Test 
	public void FormataNewDateComPonto() throws Exception {
		
		String dataComPontoDDMMAAAA = Util.formatarDataComPontoDDMMAAAA(new Date());
		
		assertEquals("28.09.2022", dataComPontoDDMMAAAA);
	}

}
