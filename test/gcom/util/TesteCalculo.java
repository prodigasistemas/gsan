package gcom.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;

import gcom.faturamento.conta.Conta;

public class TesteCalculo {

	@Test
	public void testObterPercentualVariacaoConsumoFaturado() {
		String resultado = Calculos.obterPercentualVariacaoConsumoFaturado(100, 60);
		
		assertEquals("66,67%", resultado);
	}

	@Test
	public void testValorConta() {
		Conta conta = mock(Conta.class);
		when(conta.getValorTotal()).thenReturn(new BigDecimal(10.0));
		when(conta.getValorImposto()).thenReturn(new BigDecimal(2.0));
		
		BigDecimal resultado = Calculos.valorConta(conta);
		
		assertEquals(8.0, resultado.doubleValue(), 0.1);
	}
}
