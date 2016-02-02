package gcom.faturamento;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import gcom.faturamento.conta.Conta;

public class TesteConta {

	private Conta conta;
	
	@Before
	public void setup() {
		conta = new Conta();
		conta.setValorAgua(new BigDecimal(10.2));
		conta.setValorEsgoto(new BigDecimal(10.3));
		conta.setDebitos(new BigDecimal(10.4));
		conta.setValorCreditos(new BigDecimal(10.5));
		conta.setValorImposto(new BigDecimal(10.0));
		conta.setValorRateioAgua(new BigDecimal(10.0));
		conta.setValorRateioEsgoto(new BigDecimal(10.0));
	}
	
	@Test
	public void testValorTotalContaSemImpostos() {
		assertEquals(20.40, conta.getValorTotalContaSemImposto().doubleValue(), 0.1);		
	}
	
	@Test
	public void testValorTotalContaComRateioBigDecimal() {
		assertEquals(30.40, conta.getValorTotalContaComRateioBigDecimal().doubleValue(), 0.1);
	}
	
	@Test
	public void testValorTotalContaBigDecimal() {
		assertEquals(10.40, conta.getValorTotalContaBigDecimal().doubleValue(), 0.1);
	}

}
