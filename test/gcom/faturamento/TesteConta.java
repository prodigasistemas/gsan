package gcom.faturamento;

import java.math.BigDecimal;

import org.junit.Test;
import static org.junit.Assert.*;

import gcom.faturamento.conta.Conta;

public class TesteConta {

	@Test
	public void testValorTotalContaSemImpostos() {
		Conta conta = new Conta();
		conta.setValorAgua(new BigDecimal(10.2));
		conta.setValorEsgoto(new BigDecimal(10.3));
		conta.setDebitos(new BigDecimal(10.4));
		conta.setValorCreditos(new BigDecimal(10.5));
		
		assertEquals(20.40, conta.getValorTotalContaSemImposto().doubleValue(), 0.1);		
	}

}
