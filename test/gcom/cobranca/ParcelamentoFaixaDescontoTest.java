package gcom.cobranca;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyInt;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoFaixaDescontoBO;
import gcom.faturamento.conta.Conta;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ParcelamentoFaixaDescontoTest {

	@InjectMocks
	private ParcelamentoFaixaDescontoBO bo;

	@Mock
	private RepositorioCobrancaHBM repositorio;
	
	private Conta conta;

	@Before
	public void setup() {
		bo = new ParcelamentoFaixaDescontoBO(repositorio);
		
		conta = new Conta();
		conta.setValorAgua(new BigDecimal(100.00));
		conta.setValorEsgoto(new BigDecimal(0.00));
		conta.setValorDebitos(new BigDecimal(5.00));
		conta.setValorCreditos(new BigDecimal(2.00));
		conta.setValorImposto(new BigDecimal(0.00));
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFaixa90() throws ErroRepositorioException {
		conta.setReferencia(200610);
		mockPercentual(new BigDecimal(90.00));
		
		BigDecimal valorDesconto = bo.calcularValorDescontoConta(conta);
		
		assertEquals(new BigDecimal(92.70).setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO), valorDesconto);
	}
	
	@Test
	public void testFaixa75() throws ErroRepositorioException {
		conta.setReferencia(200805);
		mockPercentual(new BigDecimal(75.00));
		
		BigDecimal valorDesconto = bo.calcularValorDescontoConta(conta);
		
		assertEquals(new BigDecimal(77.25).setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO), valorDesconto);
	}
	
	@Test
	public void testFaixa60() throws ErroRepositorioException {
		conta.setReferencia(201001);
		mockPercentual(new BigDecimal(60.00));
		
		BigDecimal valorDesconto = bo.calcularValorDescontoConta(conta);
		
		assertEquals(new BigDecimal(61.80).setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO), valorDesconto);
	}
	
	@Test
	public void testFaixa45() throws ErroRepositorioException {
		conta.setReferencia(201111);
		mockPercentual(new BigDecimal(45.00));
		
		BigDecimal valorDesconto = bo.calcularValorDescontoConta(conta);
		
		assertEquals(new BigDecimal(46.35).setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO), valorDesconto);
	}

	@Test
	public void testFaixa30() throws ErroRepositorioException {
		conta.setReferencia(201308);
		mockPercentual(new BigDecimal(30.00));
		
		BigDecimal valorDesconto = bo.calcularValorDescontoConta(conta);
		
		assertEquals(new BigDecimal(30.90).setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO), valorDesconto);
	}
	
	@Test
	public void testFaixa0() throws ErroRepositorioException {
		conta.setReferencia(201601);
		mockPercentual(new BigDecimal(0.00));
		
		BigDecimal valorDesconto = bo.calcularValorDescontoConta(conta);
		
		assertEquals(new BigDecimal(0.00).setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO), valorDesconto);
	}
	
	private void mockPercentual(BigDecimal retorno) throws ErroRepositorioException {
		when(repositorio.getPercentualDescontoPorFaixa(anyInt())).thenReturn(retorno);
	}

}
